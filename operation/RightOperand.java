package operation;

import unit.*;

/**
 * RightOperand class represents the right operand of an expression, including its value and
 * associated unit.
 * 
 * @author S24Team2D
 * @version 1
 */
public class RightOperand implements Operand
{

  private double value;
  private DisplayUnit unit;
  private boolean negative;

  /**
   * Constructs a RightOperand with the given value, unit, and negativity indicator.
   * 
   * @param value
   *          the numerical value of the right operand
   * @param unit
   *          the unit associated with the right operand
   * @param negative
   *          indicates if the right operand is negative
   */
  public RightOperand(final double value, final DisplayUnit unit, final boolean negative)
  {
    this.value = value;
    this.unit = unit;
    this.negative = negative;
  }

  @Override
  public double getValue()
  {
    return value;
  }

  @Override
  public boolean getNegative()
  {
    return negative;
  }

  @Override
  public DisplayUnit getUnit()
  {
    return unit;
  }

  /**
   * Returns a String representation of the RightOperand, including the value and unit.
   * 
   * @return test
   */
  public String toString()
  {
    if (!negative || value < 0)
    {
      String s = Double.toString(value);
      return String.format("%s %s", s, unit.toString());
    }
    else
    {
      String s = Double.toString(value);
      return String.format("-%s %s", s, unit.toString());
    }
  }

}
