package unit;

import operation.*;

/**
 * CompoundUnit class represents a compound unit consisting of a numerator and a denominator.
 * 
 * @author S24Team2D
 * @version 1
 */
public class CompoundUnit implements DisplayUnit
{

  private FullUnit numerator;
  private FullUnit denominator;
  private Operation op;
  private FullUnit single;
  private boolean isSingle;

  /**
   * Constructs a CompoundUnit with the given numerator, denominator, and operation.
   * 
   * @param numerator
   *          the numerator FullUnit
   * @param denominator
   *          the denominator FullUnit
   * @param op
   *          the operation (MULTIPLY or DIVIDE)
   */
  public CompoundUnit(final FullUnit numerator, final FullUnit denominator, final Operation op)
  {
    this.numerator = numerator;
    this.denominator = denominator;
    this.op = op;

    int sameUnit = numerator.equal(denominator);
    if (sameUnit == 0 || sameUnit == 2)
    {
      isSingle = true;
      if (op == Operation.MULTIPLY)
      {
        int totalPower = numerator.getPower() + denominator.getPower();
        single = new FullUnit(numerator.getUnit(), totalPower);
      }
      else if (op == Operation.DIVIDE)
      {
        int totalPower = numerator.getPower() - denominator.getPower();
        single = new FullUnit(numerator.getUnit(), totalPower);
      }
    }
  }

  /**
   * Compares this CompoundUnit with another DisplayUnit.
   * 
   * @param unit2
   *          the DisplayUnit to compare
   * @return an integer representing the comparison result
   */
  public int equal(final DisplayUnit unit2)
  {
    if (!(unit2.getClass().equals(this.getClass())))
    {
      return -1;
    }
    return equalHelp((CompoundUnit) unit2);

  }

  /**
   * Helper method for comparing this CompoundUnit with another CompoundUnit.
   * 
   * @param unit2
   *          the CompoundUnit to compare
   * @return an integer representing the comparison result
   */
  public int equalHelp(final CompoundUnit unit2)
  {
    FullUnit num1 = numerator;
    FullUnit num2 = unit2.getNumerator();
    FullUnit den1 = denominator;
    FullUnit den2 = unit2.getDenominator();

    boolean sameNum = num1.equal(num2) == 0;
    boolean sameDen = den1.equal(den2) == 0;

    if (sameNum && sameDen)
    {
      if (op == unit2.getOp())
      {
        return 0;
      }
      else
      {
        return 1;
      }
    }
    else
    {
      return 2;
    }
  }

  /**
   * Retrieves the numerator of this CompoundUnit.
   * 
   * @return the numerator FullUnit
   */
  public FullUnit getNumerator()
  {
    return numerator;
  }

  /**
   * Retrieves the denominator of this CompoundUnit.
   * 
   * @return the denominator FullUnit
   */
  public FullUnit getDenominator()
  {
    return denominator;
  }

  /**
   * Retrieves the operation of this CompoundUnit.
   * 
   * @return the operation (MULTIPLY or DIVIDE)
   */
  public Operation getOp()
  {
    return op;
  }

  /**
   * Generates a string representation of this CompoundUnit.
   * 
   * @return the string representation
   */
  public String toString()
  {
    String s = null;
    if (numerator.getPower() == 0 || denominator.getPower() == 0)
    {
      String left = "";
      String right = "";

      if (numerator.getPower() != 0)
      {
        left = numerator.toString();
      }

      if (denominator.getPower() != 0)
      {
        right = denominator.toString();
      }

      return left + right;

    }
    else if (!isSingle)
    {
      switch (op)
      {
        case MULTIPLY:
          return String.format("%s-%s", numerator.toString(), denominator.toString());
        case DIVIDE:
          return String.format("%s/%s", numerator.toString(), denominator.toString());
        case ADD:
        case SUBTRACT:
        default:

      }

    }
    else if (single != null)
    {
      s = single.toString();
    }
    return s;

  }

}
