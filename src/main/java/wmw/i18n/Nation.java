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
package wmw.i18n;

/**
 * 
 * Nation lists all available nations of the world.
 * 
 */
public enum Nation {

  AF("Afghanistan"), AL("Albania"), DZ("Algeria"), AS("American Samoa"),
  AD("Andorra"), AO("Angola"), AI("Anguilla"), AQ("Antarctica"),
  AG("Antigua and Barbuda"), AR("Argentina"), AM("Armenia"), AW("Aruba"),
  AU("Australia"), AT("Austria"), AZ("Azerbaijan"), BS("Bahamas"),
  BH("Bahrain"), BD("Bangladesh"), BB("Barbados"), BY("Belarus"), BE("Belgium"),
  BZ("Belize"), BJ("Benin"), BM("Bermuda"), BT("Bhutan"), BO("Bolivia"),
  BA("Bosnia and Herzegovina"), BW("Botswana"), BV("Bouvet Island"),
  BR("Brazil"), IO("British Indian Ocean Territory"), BN("Brunei Darussalam"),
  BG("Bulgaria"), BF("Burkina Faso"), BI("Burundi"), KH("Cambodia"),
  CM("Cameroon"), CA("Canada"), CV("Cape Verde"), KY("Cayman Islands"),
  CF("Central African Republic"), TD("Chad"), CL("Chile"), CN("China"),
  CX("Christmas Island"), CC("Cocos (Keeling Islands)"), CO("Colombia"),
  KM("Comoros"), CG("Congo"), CK("Cook Islands"), CR("Costa Rica"),
  CI("Cote D'Ivoire (Ivory Coast)"), HR("Croatia (Hrvatska"), CU("Cuba"),
  CY("Cyprus"), CZ("Czech Republic"), DK("Denmark"), DJ("Djibouti"),
  DM("Dominica"), DO("Dominican Republic"), TP("East Timor"), EC("Ecuador"),
  EG("Egypt"), SV("El Salvador"), GQ("Equatorial Guinea"), ER("Eritrea"),
  EE("Estonia"), ET("Ethiopia"), FK("Falkland Islands (Malvinas)"),
  FO("Faroe Islands"), FJ("Fiji"), FI("Finland"), FR("France"),
  FX("France, Metropolitan"), GF("French Guiana"), PF("French Polynesia"),
  TF("French Southern Territories"), GA("Gabon"), GM("Gambia"), GE("Georgia"),
  DE("Germany"), GH("Ghana"), GI("Gibraltar"), GR("Greece"), GL("Greenland"),
  GD("Grenada"), GP("Guadeloupe"), GU("Guam"), GT("Guatemala"), GN("Guinea"),
  GW("Guinea-Bissau"), GY("Guyana"), HT("Haiti"),
  HM("Heard and McDonald Islands"), HN("Honduras"), HK("Hong Kong"),
  HU("Hungary"), IS("Iceland"), IN("India"), ID("Indonesia"), IR("Iran"),
  IQ("Iraq"), IE("Ireland"), IL("Israel"), IT("Italy"), JM("Jamaica"),
  JP("Japan"), JO("Jordan"), KZ("Kazakhstan"), KE("Kenya"), KI("Kiribati"),
  KP("Korea (North)"), KR("Korea (South)"), KW("Kuwait"), KG("Kyrgyzstan"),
  LA("Laos"), LV("Latvia"), LB("Lebanon"), LS("Lesotho"), LR("Liberia"),
  LY("Libya"), LI("Liechtenstein"), LT("Lithuania"), LU("Luxembourg"),
  MO("Macau"), MK("Macedonia"), MG("Madagascar"), MW("Malawi"), MY("Malaysia"),
  MV("Maldives"), ML("Mali"), MT("Malta"), MH("Marshall Islands"),
  MQ("Martinique"), MR("Mauritania"), MU("Mauritius"), YT("Mayotte"),
  MX("Mexico"), FM("Micronesia"), MD("Moldova"), MC("Monaco"), MN("Mongolia"),
  MS("Montserrat"), MA("Morocco"), MZ("Mozambique"), MM("Myanmar"),
  NA("Namibia"), NR("Nauru"), NP("Nepal"), NL("Netherlands"),
  AN("Netherlands Antilles"), NC("New Caledonia"), NZ("New Zealand"),
  NI("Nicaragua"), NE("Niger"), NG("Nigeria"), NU("Niue"), NF("Norfolk Island"),
  MP("Northern Mariana Islands"), NO("Norway"), OM("Oman"), PK("Pakistan"),
  PW("Palau"), PA("Panama"), PG("Papua New Guinea"), PY("Paraguay"), PE("Peru"),
  PH("Philippines"), PN("Pitcairn"), PL("Poland"), PT("Portugal"),
  PR("Puerto Rico"), QA("Qatar"), RE("Reunion"), RO("Romania"),
  RU("Russian Federation"), RW("Rwanda"), KN("Saint Kitts and Nevis"),
  LC("Saint Lucia"), VC("Saint Vincent and The Grenadines"), WS("Samoa"),
  SM("San Marino"), ST("Sao Tome and Principe"), SA("Saudi Arabia"),
  SN("Senegal"), SC("Seychelles"), SL("Sierra Leone"), SG("Singapore"),
  SK("Slovak Republic"), SI("Slovenia"), SB("Solomon Islands"), SO("Somalia"),
  ZA("South Africa"), GS("S. Georgia and S. Sandwich Isls."), ES("Spain"),
  LK("Sri Lanka"), SH("St. Helena"), PM("St. Pierre and Miquelon"), SD("Sudan"),
  SR("Suriname"), SJ("Svalbard and Jan Mayen Islands"), SZ("Swaziland"),
  SE("Sweden"), CH("Switzerland"), SY("Syria"), TW("Taiwan"), TJ("Tajikistan"),
  TZ("Tanzania"), TH("Thailand"), TG("Togo"), TK("Tokelau"), TO("Tonga"),
  TT("Trinidad and Tobago"), TN("Tunisia"), TR("Turkey"), TM("Turkmenistan"),
  TC("Turks and Caicos Islands"), TV("Tuvalu"), UG("Uganda"), UA("Ukraine"),
  AE("United Arab Emirates"), UK("United Kingdom"), US("United States"),
  UM("US Minor Outlying Islands"), UY("Uruguay"), UZ("Uzbekistan"),
  VU("Vanuatu"), VA("Vatican City State (Holy See)"), VE("Venezuela"),
  VN("Viet Nam"), VG("Virgin Islands (British)"), VI("Virgin Islands (US)"),
  WF("Wallis and Futuna Islands"), EH("Western Sahara"), YE("Yemen"),
  YU("Yugoslavia"), ZR("Zaire"), ZM("Zambia"), ZW("Zimbabwe");

  private String name;

  private Nation(String name) {
    this.name = name;
  }

  /**
   * Returns the name of a nation.
   * 
   * @return the name of a nation
   */
  public String getName() {
    return name;
  }

}
