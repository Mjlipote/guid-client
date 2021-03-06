/**
 * 
 * @author Wei-Ming Wu
 * 
 * 
 *         Copyright 2013 Wei-Ming Wu
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License"); you
 *         may not use this file except in compliance with the License. You may
 *         obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 * 
 */
package wmw.validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TWNationalIdValidatorTest {

  @Test
  public void testValidate() {
    assertTrue(TWNationalIdValidator.validate("A123456789"));
    assertFalse(TWNationalIdValidator.validate("A987654310"));
    assertFalse(TWNationalIdValidator.validate("A987654321"));
    assertFalse(TWNationalIdValidator.validate("A666"));
    assertFalse(TWNationalIdValidator.validate(null));
  }

}
