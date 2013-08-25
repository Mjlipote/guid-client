package tw.edu.ym.guid.client;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
import static org.apache.http.impl.auth.BasicScheme.authenticate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.NationalId;
import tw.edu.ym.guid.client.field.Sex;
import wmw.util.InputStreamUtil;

import com.google.common.base.Objects;
import com.google.gson.Gson;

/**
 * 
 * GuidClient can create and query GUIDs from a GUID server.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class GuidClient {

  private HttpClient httpClient;

  private final String username;
  private final String password;
  private final String prefix;
  private final URI uri;

  /**
   * Creates a GuidClient.
   * 
   * @param username
   *          to login server
   * @param password
   *          to login server
   * @param prefix
   *          of GUIDs
   * @param uri
   *          of the server host
   */
  public GuidClient(String username, String password, String prefix, URI uri) {
    this.username = username;
    this.password = password;
    this.prefix = prefix;
    this.uri = uri;
  }

  void setHttpClient(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  /**
   * Creates GUID by given PII.
   * 
   * @param pii
   *          a PII
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> create(PII pii) throws IOException {
    return resuest(new Gson().toJson(pii.getHashcodes()), "create");
  }

  /**
   * Creates GUID by given PIIs.
   * 
   * @param piis
   *          Array of PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> create(PII... piis) throws IOException {
    return create(Arrays.asList(piis));
  }

  /**
   * Creates GUID by given PIIs.
   * 
   * @param piis
   *          List of PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> create(List<PII> piis) throws IOException {
    List<List<String>> hashsets = newArrayList();
    for (PII pii : piis)
      hashsets.add(pii.getHashcodes());
    return resuest(new Gson().toJson(hashsets), "create");
  }

  /**
   * Queries GUID by given PII. No GUID created if GUID can not be found.
   * 
   * @param piis
   *          a PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> query(PII pii) throws IOException {
    return resuest(new Gson().toJson(pii.getHashcodes()), "show");
  }

  /**
   * Queries GUID by given PIIs. No GUID created if GUID can not be found.
   * 
   * @param piis
   *          Array of PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> query(PII... piis) throws IOException {
    return query(Arrays.asList(piis));
  }

  /**
   * Queries GUID by given PIIs. No GUID created if GUID can not be found.
   * 
   * @param piis
   *          List of PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> query(List<PII> piis) throws IOException {
    List<List<String>> hashsets = newArrayList();
    for (PII pii : piis)
      hashsets.add(pii.getHashcodes());
    return resuest(new Gson().toJson(hashsets), "show");
  }

  private List<String> resuest(String jsonHashes, String methed)
      throws IOException {
    if (httpClient == null)
      httpClient =
          new DefaultHttpClient(
              getSSLClientConnectionManager(uri.getPort() == -1 ? 443
                  : uri.getPort()));

    HttpPost httpost =
        new HttpPost("https://" + uri.getHost()
            + (uri.getPort() == -1 ? "" : ":" + uri.getPort()) + "/guid/"
            + methed);
    httpost.addHeader(authenticate(new UsernamePasswordCredentials(username,
        password), "US-ASCII", false));

    List<NameValuePair> nvps = newArrayList();
    nvps.add(new BasicNameValuePair("prefix", prefix));
    nvps.add(new BasicNameValuePair("hashes", jsonHashes));

    try {
      httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    HttpResponse response = httpClient.execute(httpost);
    HttpEntity entity = response.getEntity();

    String json = InputStreamUtil.toString(entity.getContent());
    @SuppressWarnings("unchecked")
    List<String> result = new Gson().fromJson(json, List.class);
    return result;
  }

  private ClientConnectionManager getSSLClientConnectionManager(int port) {
    SSLContext sslContext = null;
    try {
      sslContext = SSLContext.getInstance("SSL");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    try {
      sslContext.init(null, new TrustManager[] { new X509TrustManager() {

        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        public void
            checkClientTrusted(X509Certificate[] certs, String authType) {}

        public void
            checkServerTrusted(X509Certificate[] certs, String authType) {}

      } }, new SecureRandom());
    } catch (KeyManagementException e) {
      e.printStackTrace();
    }

    SSLSocketFactory sf =
        new SSLSocketFactory(sslContext, ALLOW_ALL_HOSTNAME_VERIFIER);
    Scheme httpsScheme = new Scheme("https", port, sf);
    SchemeRegistry schemeRegistry = new SchemeRegistry();
    schemeRegistry.register(httpsScheme);

    return new BasicClientConnectionManager(schemeRegistry);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this.getClass()).add("Username", username)
        .add("Password", password).add("Prefix", prefix).add("URI", uri)
        .toString();
  }

  public static void main(String[] args) throws URISyntaxException, IOException {
    GuidClient guidClient;

    PII pii;

    guidClient =
        new GuidClient("guid1", "12345", "TEST", new URI(
            "https://localhost:8443"));
    pii =
        new PII(new Name("mj", "li"), Sex.MALE, new Birthday(1979, 7, 21),
            new NationalId("E122371585"));
    System.out.println(guidClient.query(pii));

  }

}
