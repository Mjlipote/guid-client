package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkNotNull;
import static tw.edu.ym.guid.client.hashcode.Field.cnob;
import static tw.edu.ym.guid.client.hashcode.Field.giidc;
import tw.edu.ym.guid.client.annotation.Factor;
import wmw.i18n.Nation;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * 
 * Nationality is a optional field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class Nationality implements Comparable<Nationality> {

  private final Nation nationality;
  private final Nation nationalityOfBirth;

  /**
   * Returns a default nationality of TW(Taiwan).
   * 
   * @return a nationality of TW
   */
  public static Nationality getDefault() {
    return new Nationality(Nation.TW);
  }

  /**
   * Creates a Nationality.
   * 
   * @param nationality
   *          a Nation
   */
  public Nationality(Nation nationality) {
    this.nationality = checkNotNull(nationality, "Nationality can't be null.");
    this.nationalityOfBirth =
        checkNotNull(nationality, "Nationality of birth can't be null.");
  }

  /**
   * Creates a Nationality.
   * 
   * @param nationality
   *          a Nation
   * @param nationalityOfBirth
   *          a Nation
   */
  public Nationality(Nation nationality, Nation nationalityOfBirth) {
    this.nationality = checkNotNull(nationality, "Nationality can't be null.");
    this.nationalityOfBirth =
        checkNotNull(nationalityOfBirth, "Nationality of birth can't be null.");
  }

  /**
   * Returns the nation of nationality.
   * 
   * @return a Nation
   */
  @Factor(field = giidc)
  public Nation getNationality() {
    return nationality;
  }

  /**
   * Returns the nation of nationality of birth.
   * 
   * @return a Nation
   */
  @Factor(field = cnob)
  public Nation getNationalityOfBirth() {
    return nationalityOfBirth;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Nationality) {
      Nationality nt = (Nationality) o;
      return Objects.equal(nationality, nt.nationality)
          && Objects.equal(nationalityOfBirth, nt.nationalityOfBirth);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nationality, nationalityOfBirth);
  }

  @Override
  public String toString() {
    return "Nationality=" + nationality + ", " + "NationalityOfBirth="
        + nationalityOfBirth;
  }

  @Override
  public int compareTo(Nationality that) {
    return ComparisonChain.start().compare(this.nationality, that.nationality)
        .compare(this.nationalityOfBirth, that.nationalityOfBirth).result();
  }

}
