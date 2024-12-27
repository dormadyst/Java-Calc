package unit;

/**
 * FullUnit class represents a unit with both the unit type and its power. Implements DisplayUnit
 * interface.
 * 
 * @author S24Team2d
 * @version 1
 */
public class FullUnit implements DisplayUnit
{

  private Unit unit;
  private int power;

  /**
   * Constructs a FullUnit object with the given unit and power.
   * 
   * @param unit
   *          the unit
   * @param power
   *          the power of the unit
   */
  public FullUnit(final Unit unit, final int power)
  {
    this.unit = unit;
    this.power = power;
  }

  /**
   * Multiplies this FullUnit by another FullUnit, increasing the power if they are the same unit.
   * 
   * @param other
   *          the FullUnit to multiply with
   * @return FullUnit after multiplication
   * @throws IllegalArgumentException
   *           if attempting to multiply different units
   */
  public FullUnit multiply(final FullUnit other)
  {
    if (this.unit.equals(other.unit))
    {
      return new FullUnit(unit, this.power + other.power);
    }
    throw new IllegalArgumentException("Cannot multiply different units");
  }

  /**
   * Compares this FullUnit with another DisplayUnit.
   * 
   * @param unit2
   *          the DisplayUnit to compare
   * @return an integer representing the comparison result
   */
  public int compareUnits(final DisplayUnit unit2)
  {
    if (!(unit2 instanceof FullUnit))
    {
      return -1;
    }
    return compareDetails((FullUnit) unit2);
  }

  // Helper method for comparing details of this FullUnit with another.
  private int compareDetails(final FullUnit unit2)
  {
    if (unit.getType() != unit2.getUnit().getType())
    {
      return -1;
    }
    else if (unit.getCode() != unit2.getUnit().getCode())
    {
      return 1;
    }
    else if (power != unit2.getPower())
    {
      return 2;
    }
    else
    {
      return 0;
    }
  }

  /**
   * Compares this FullUnit with another DisplayUnit for equality.
   * 
   * @param unit2
   *          the DisplayUnit to compare
   * @return an integer representing the equality result
   */
  public int equal(final DisplayUnit unit2)
  {
    if (!(unit2.getClass().equals(this.getClass())))
    {
      return -1;
    }
    return equalHelp((FullUnit) unit2);
  }

  /**
   * Helper method for comparing this FullUnit with another FullUnit for equality.
   * 
   * @param unit2
   *          the FullUnit to compare
   * @return an integer representing the equality result
   */
  private int equalHelp(final FullUnit unit2)
  {
    if (unit.getType() != unit2.getUnit().getType())
    {
      return -1;
    }
    else if (unit.getCode() != unit2.getUnit().getCode())
    {
      return 1;
    }
    else if (power != unit2.getPower())
    {
      return 2;
    }
    else
    {
      return 0;
    }
  }

  /**
   * Gets the unit of this FullUnit.
   * 
   * @return the unit
   */
  public Unit getUnit()
  {
    return unit;
  }

  /**
   * Gets the power of this FullUnit.
   * 
   * @return the power
   */
  public int getPower()
  {
    return power;
  }

  /**
   * Converts this FullUnit to a string representation.
   * 
   * @return a string representation of this FullUnit
   */
  @Override
  public String toString()
  {
    // Check if the unit is identified as unitless
    if (unit.isUnitless())
    {
      // For unitless units, return an empty string regardless of the power
      return "";
    }
    else if (power == 1)
    {
      // If the unit has a power of 1, just return the symbol of the unit
      return unit.getSymbol();
    }
    else if (power == 0)
    {
      return "";
    }
    else
    {
      // For units with a power greater than 1, format the string to show the symbol and the power
      return String.format("%s^%d", unit.getSymbol(), power);
    }
  }
}
