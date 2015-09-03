package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkNotNull;
import static tw.edu.ym.guid.client.hashcode.Field.cob;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import tw.edu.ym.guid.client.annotation.Factor;
import wmw.i18n.Nation;

/**
 * 
 * Birthplace is a optional field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class Birthplace implements Comparable<Birthplace> {

  private final Nation birthplace;

  /**
   * Returns a default birthplace of TW(Taiwan).
   * 
   * @return a birthplace of TW
   */
  public static Birthplace getDefault() {
    return new Birthplace(Nation.TW);
  }

  /**
   * Creates a Birthplace.
   * 
   * @param birthplace
   *          a Nation
   */
  public Birthplace(Nation birthplace) {
    this.birthplace = checkNotNull(birthplace, "Birthplace can't be null.");
  }

  /**
   * Returns the nation of birthplace.
   * 
   * @return a Nation
   */
  @Factor(field = cob)
  public Nation getBirthplace() {
    return birthplace;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof Birthplace)) return false;
    Birthplace castOther = (Birthplace) other;
    return Objects.equal(birthplace, castOther.birthplace);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(birthplace);
  }

  @Override
  public String toString() {
    return birthplace.toString();
  }

  @Override
  public int compareTo(Birthplace that) {
    return ComparisonChain.start().compare(birthplace, that.birthplace)
        .result();
  }

}
