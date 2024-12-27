package operation;

import unit.*;

/**
 * 
 */
public class LeftOperand implements Operand
{

  private double value;
  private DisplayUnit unit;
  private boolean negative;

  /**
   * LeftOperand constructor.
   * 
   * @param value double
   * @param unit DisplayUnit
   * @param negative boolean
   */
  public LeftOperand(final double value, final DisplayUnit unit, final boolean negative)
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
   * Returns a String representation of the LeftOperand, including the 
   * value and unit.
   * 
   * @return test
   */
	public String toString() 
	{
		if (!negative || value < 0)
		{
      String s = Double.toString(value);
		  return String.format("%s %s", s, unit.toString());
		} else 
		{
			String s = Double.toString(value);
			return String.format("-%s %s", s, unit.toString());
		}
	}
	
}
