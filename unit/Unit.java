package unit;

/**
 * Unit class represents a unit with symbol, type, and code.
 * 
 * @author Steve Dormady
 * @version 1
 */
public class Unit
{

  private final String symbol;
  private final char type;
  private final char code;

  /**
   * Constructs a Unit object with the given symbol, type, and code.
   * 
   * @param symbol
   *          the symbol of the unit
   * @param type
   *          the type of the unit
   * @param code
   *          the code of the unit
   */
  public Unit(final String symbol, final char type, final char code)
  {
    this.symbol = symbol;
    this.type = type;
    this.code = code;
  }

  /**
   * Gets the symbol of this Unit.
   * 
   * @return the symbol
   */
  public String getSymbol()
  {
    return symbol;
  }

  /**
   * Gets the type of this Unit.
   * 
   * @return the type
   */
  public char getType()
  {
    return type;
  }

  /**
   * Gets the code of this Unit.
   * 
   * @return the code
   */
  public char getCode()
  {
    return code;
  }

  /**
   * Checks if this Unit is unitless.
   * 
   * @return true if the unit is unitless, false otherwise
   */
  public boolean isUnitless()
  {
    return "".equals(symbol);
  }
}