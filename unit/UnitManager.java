package unit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UnitManager class manages units and provides methods for retrieving unit information.
 * 
 * @author S24Team2d
 * @version 1
 */
public class UnitManager
{
  private static List<Unit> units = new ArrayList<>();

  static
  {
    // Blank unit for custom entries or unitless values
    units.add(new Unit("", 'x', 'x'));
    // Predefined units
    units.add(new Unit("in", 'l', 'a'));
    units.add(new Unit("ft", 'l', 'b'));
    units.add(new Unit("yd", 'l', 'c'));
    units.add(new Unit("mi", 'l', 'd'));
    units.add(new Unit("mm", 'l', 'e'));
    units.add(new Unit("cm", 'l', 'f'));
    units.add(new Unit("m", 'l', '*'));
    units.add(new Unit("km", 'l', 'h'));
    units.add(new Unit("sec", 't', 'a'));
    units.add(new Unit("min", 't', '*'));
    units.add(new Unit("hr", 't', 'c'));
    units.add(new Unit("day", 't', 'd'));
    units.add(new Unit("oz", 'w', 'a'));
    units.add(new Unit("lb", 'w', 'b'));
    units.add(new Unit("ton", 'w', 'c'));
    units.add(new Unit("g", 'w', '*'));
    units.add(new Unit("kg", 'w', 'e'));
    units.add(new Unit("w", 'p', 'a'));
    units.add(new Unit("kw", 'p', 'a'));
    units.add(new Unit("p", 'v', 'a'));
    units.add(new Unit("q", 'v', 'b'));
    units.add(new Unit("gal", 'v', 'c'));
    units.add(new Unit("cc", 'v', '*'));
    units.add(new Unit("l", 'v', 'e'));
    units.add(new Unit("¢", 'm', 'a'));
    units.add(new Unit("$", 'm', 'b'));
  }

  /**
   * Adds a custom unit to the list of units.
   *
   * @param symbol
   *          The symbol of the custom unit.
   */
  public static void addCustomUnit(final String symbol)
  {
    units.add(new Unit(symbol, 'x', 'x'));
  }
  
  /**
   * Adds a custom unit to the list of units.
   *
   * @param symbol
   *          The symbol of the custom unit.
   * @param type
   *          The type of the custom unit ('l' for length, 't' for time, 'w' for weight, 'p' for
   *          power, 'v' for volume, 'm' for money, 'x' for custom or unitless).
   * @param code
   *          Code
   */
  public static void addCustomUnit(final String symbol, final char type, final char code)
  {
    units.add(new Unit(symbol, type, code));
  }

  /**
   * Retrieves a list of all unit symbols.
   *
   * @return A list containing all unit symbols.
   */
  public static List<String> getAllUnitSymbols()
  {
    return units.stream().map(Unit::getSymbol).collect(Collectors.toList());
  }

  /**
   * Retrieves a unit object by its symbol.
   *
   * @param symbol
   *          The symbol of the unit to retrieve.
   * @return The unit object corresponding to the given symbol, or null if no such unit exists.
   */
  public static Unit getUnitBySymbol(final String symbol)
  {
    for (Unit unit : units)
    {
      if (unit.getSymbol().equals(symbol))
      {
        return unit;
      }
    }
    return null;
  }

  /**
   * Retrieves the standard unit of a given type.
   *
   * @param type
   *          The type of unit ('l' for length, 't' for time, 'w' for weight, 'p' for power, 'v' for
   *          volume, 'm' for money, 'x' for custom or unitless).
   * @return The standard unit object corresponding to the given type, or null if no such unit
   *         exists.
   */
  public static Unit getStandardUnit(final char type)
  {
    for (Unit unit : units)
    {
      if (unit.getType() == type)
      {
        return unit; // Return the first unit matching the type
      }
    }
    return null;
  }
}
