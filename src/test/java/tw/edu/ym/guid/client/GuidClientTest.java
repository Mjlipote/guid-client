package tw.edu.ym.guid.client;

import static com.google.common.collect.Lists.newArrayList;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.junit.Before;
import org.junit.Test;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.Sex;
import tw.edu.ym.guid.client.field.TWNationalId;

public class GuidClientTest {

  private GuidClient guidClient;
  private URI uri;

  private HttpClient mockClient;
  private HttpResponse mockResponse;
  private StatusLine mockStatusLine;
  private HttpEntity mockEntity;

  private PII pii;

  @Before
  public void setUp() throws Exception {
    uri = new URI("http://localhost:3000");
    guidClient = new GuidClient(uri, "test", "test", "TEST");

    mockClient = createMock(HttpClient.class);
    mockResponse = createMock(HttpResponse.class);
    mockEntity = createMock(HttpEntity.class);
    mockStatusLine = createMock(StatusLine.class);

    expect(mockClient.execute(anyObject(HttpPost.class))).andReturn(
        mockResponse);
    expect(mockResponse.getEntity()).andReturn(mockEntity);
    expect(mockResponse.getStatusLine()).andReturn(mockStatusLine);
    expect(mockStatusLine.getStatusCode()).andReturn(200);
    expect(mockEntity.getContent()).andReturn(
        new ByteArrayInputStream("[TEST-b94c05f3]".getBytes()));

    replay(mockClient);
    replay(mockResponse);
    replay(mockStatusLine);
    replay(mockEntity);

    guidClient.setHttpClient(mockClient);

    pii =
        new PII.Builder(new Name("mj", "li"), Sex.MALE, new Birthday(1979, 7,
            21), new TWNationalId("E122371585")).build();
  }

  @Test
  public void testConstructor() throws Exception {
    assertTrue(guidClient instanceof GuidClient);
  }

  @Test
  public void testConstructorWithoutPrefix() throws Exception {
    guidClient =
        new GuidClient(new URI("http://localhost:3000"), "test", "test");
    assertTrue(guidClient instanceof GuidClient);
  }

  @Test
  public void testConstructorWithNullArgument() throws Exception {
    try {
      new GuidClient(uri, null, "test", "TEST");
      fail();
    } catch (NullPointerException e) {}
    try {
      new GuidClient(uri, "test", null, "TEST");
      fail();
    } catch (NullPointerException e) {}
    try {
      new GuidClient(uri, "test", "test", null);
      fail();
    } catch (NullPointerException e) {}
    try {
      new GuidClient(null, "test", "test", "TEST");
      fail();
    } catch (NullPointerException e) {}
    try {
      new GuidClient(uri, null, "test");
      fail();
    } catch (NullPointerException e) {}
    try {
      new GuidClient(uri, "test", null);
      fail();
    } catch (NullPointerException e) {}
    try {
      new GuidClient(null, "test", "test");
      fail();
    } catch (NullPointerException e) {}
  }

  @Test
  public void testCreate() throws IOException {
    assertEquals("TEST-b94c05f3", guidClient.create(pii));
  }

  @Test
  public void testCreateWithList() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"),
        guidClient.create(newArrayList(pii)));
  }

  @Test
  public void testCreateWithArray() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"),
        guidClient.create(new PII[] { pii }));
  }

  @Test
  public void testQuery() throws IOException {
    assertEquals("TEST-b94c05f3", guidClient.query(pii));
  }

  @Test
  public void testQueryWithList() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"),
        guidClient.query(newArrayList(pii)));
  }

  @Test
  public void testQueryWithArray() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"),
        guidClient.query(new PII[] { pii }));
  }

  @Test
  public void testToString() {
    assertEquals(
        "GuidClient{Username=test, Password=test, Prefix=TEST, URI=http://localhost:3000}",
        guidClient.toString());
  }

  public static void main(String[] args) throws URISyntaxException, IOException {
    GuidClient guidClient =
        new GuidClient(new URI("https://localhost:8443"), "guid1", "12345",
            "TEST");

    PII pii =
        new PII.Builder(new Name("mj", "li"), Sex.MALE, new Birthday(1979, 7,
            21), new TWNationalId("E122371585")).build();

    System.out.println(guidClient.query(Arrays.asList(pii, pii)));
  }

}
