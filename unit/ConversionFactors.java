package unit;

import java.util.HashMap;

/**
 * ConversionFactors class contains static methods to retrieve conversion factors for various units.
 * 
 * @author S24Team2D
 * @version 1
 */
public class ConversionFactors
{

  private static HashMap<String, Double> map = new HashMap<>();
  
  /**
   * This method get the factors conversion factors. Conversion factors are stored in a HashMap with
   * keys representing the type of conversion and values representing the conversion factor.
   * 
   * String Cipher: First letter - Unit type (Length, weight, etc.), Second Letter - Variable name,
   * Third Letter - to or from
   * 
   * @return HashMap containing all the conversion factors
   */
  public static HashMap<String, Double> getFactors()
  {
    
    
    // Length conversions to meter
    map.put("lat", 0.0254);   // Inch to meter
    map.put("lbt", 0.3048);   // Foot to meter
    map.put("lct", 0.9144);   // Yard to meter
    map.put("ldt", 1609.34);  // Mile to meter
    map.put("let", 0.001);    // Kilometer to meter
    map.put("lft", 0.01);     // Centimeter to meter
    map.put("lht", 1000.0);   // Kilometer to meter

    // Length conversions from meter
    map.put("laf", 39.3701);  // Meter to inch
    map.put("lbf", 3.28084);  // Meter to foot
    map.put("lcf", 1.09361);  // Meter to yard
    map.put("ldf", 0.000621371); // Meter to mile
    map.put("lef", 1000.0);   // Meter to kilometer
    map.put("lff", 100.0);    // Meter to centimeter
    map.put("lhf", 0.001);    // Meter to kilometer

    // Weight conversions to gram
    map.put("wat", 28.3495);  // Ounce to gram
    map.put("wbt", 453.592);  // Pound to gram
    map.put("wct", 907185.0); // Ton to gram
    map.put("wet", 1000.0);   // Kilogram to gram

    // Weight conversions from gram
    map.put("waf", 0.035274); // Gram to ounce
    map.put("wbf", 0.00220462); // Gram to pound
    map.put("wcf", 0.0000011023); // Gram to ton
    map.put("wef", 0.001);    // Gram to kilogram

    // Volume conversions to cc
    map.put("vat", 473.176);  // Quart to cc
    map.put("vbt", 946.353);  // Gallon to cc
    map.put("vct", 3785.41);  // Cubic foot to cc
    map.put("vet", 1000.0);   // Liter to cc

    // Volume conversions from cc
    map.put("vaf", 0.00211338); // cc to quart
    map.put("vbf", 0.00105669); // cc to gallon
    map.put("vcf", 0.000264172); // cc to cubic foot
    map.put("vef", 0.001);    // cc to liter

    // Money conversions
    map.put("mbt", 100.0);    // Dollar to cent
    map.put("mbf", 0.01);     // Cent to dollar

    // Time conversions to minutes
    map.put("tat", 0.0166667); // Second to minutes
    map.put("tct", 60.0);     // Hour to minutes
    map.put("tdt", 1440.0);   // Day to minutes

    // Time conversions from minutes
    map.put("taf", 60.0);     // Minutes to hour
    map.put("tcf", 0.0166667); // Minutes to second
    map.put("tdf", 0.000694444); // Minutes to day

    return map;

  }
}
