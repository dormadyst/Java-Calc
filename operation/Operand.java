package operation;

import unit.DisplayUnit;

/**
 * Operand Interface.
 * 
 * @author S24Team2D
 * @version 1
 */
public interface Operand
{
  /**
   * 
   * @return test
   */
  public abstract double getValue();

  /**
   * 
   * @return test
   */
  public abstract DisplayUnit getUnit();

  /**
   * 
   * @return test
   */
  public abstract boolean getNegative();
}
