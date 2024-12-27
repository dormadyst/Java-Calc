package operation;

import unit.*;

/**
 * Result class represents the result of an operation, including its value and associated unit.
 * 
 * @author S24Team2D
 * @version 1
 */
public class Result implements Operand
{

  private double value;
  private DisplayUnit unit;
  private boolean negative;

  /**
   * Constructs a Result with the given value, unit, and negativity indicator.
   * 
   * @param value    the numerical value of the result
   * @param unit     the unit associated with the result
   * @param negative indicates if the result is negative
   */
  public Result(final double value, final DisplayUnit unit, final boolean negative)
  {
    this.value = Math.abs(value);
    this.unit = unit;
    this.negative = negative;
  }

  @Override
  public double getValue()
  {
    return value;
  }

  @Override
  public DisplayUnit getUnit()
  {
    return unit;
  }

  @Override
  public boolean getNegative()
  {
    return negative;
  }

  /**
   * Returns a String representation of the Result, including the 
   * value and unit.
   */
  @Override
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
