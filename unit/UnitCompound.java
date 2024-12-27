package unit;

import operation.*;

/**
 * UnitCompound class provides methods for compounding units.
 * 
 * @author S24Team2D
 * @version 1
 */
public class UnitCompound
{
  private static final String FT = "ft";
  /**
   * Compounds two operands with the given operation.
   * 
   * @param left  the left operand
   * @param right the right operand
   * @param op    the operation
   * @return a converted operand
   */
  public static DisplayUnit compoundUnit(final Operand left, final Operand right,
      final Operation op)
  {
    switch (op)
    {
      case MULTIPLY:
        return mult(left, right);
      case DIVIDE:
        return div(left, right);
      case ADD:
      case SUBTRACT:
      default:
    }
    return null;

  }

  /**
   * Compounds two operands by multiplication.
   * 
   * @param left  the left operand
   * @param right the right operand
   * @return a compounded unit
   */
  private static DisplayUnit mult(final Operand left, final Operand right)
  {
    Unit foot = new Unit(FT, 'l', 'b');
    FullUnit f = new FullUnit(foot, 1);
    CompoundUnit u = new CompoundUnit(f, f, Operation.MULTIPLY);
    boolean isleftFull = left.getUnit().getClass().equals(f.getClass());
    boolean isrightFull = right.getUnit().getClass().equals(f.getClass());

    if (isleftFull && isrightFull)
    {
      return new CompoundUnit((FullUnit) left.getUnit(), (FullUnit) right.getUnit(),
          Operation.MULTIPLY);
    }

    boolean leftCompound = left.getUnit().getClass().equals(u.getClass());
    boolean rightCompound = right.getUnit().getClass().equals(u.getClass());
    if (leftCompound && !rightCompound)
    {
      CompoundUnit leftCom = (CompoundUnit) left.getUnit();
      FullUnit rightFull = (FullUnit) right.getUnit();
      DisplayUnit top;
      DisplayUnit bottom;
      FullUnit f1 = leftCom.getNumerator();
      FullUnit f2 = leftCom.getDenominator();

      if (leftCom.getOp() == Operation.MULTIPLY)
      {

        if (f1.getUnit() == rightFull.getUnit() || f2.getUnit() == rightFull.getUnit())
        {
          if (f1.getUnit() == rightFull.getUnit())
          {
            f1 = new FullUnit(f1.getUnit(), f1.getPower() + rightFull.getPower());
          }
          else if (f2.getUnit() == rightFull.getUnit())
          {
            f2 = new FullUnit(f2.getUnit(), f2.getPower() + rightFull.getPower());
          }
          return new CompoundUnit(f1, f2, Operation.MULTIPLY);
        }
        else
        {
          top = leftCom.getNumerator();
          bottom = new CompoundUnit(leftCom.getDenominator(), rightFull, Operation.MULTIPLY);
          return new ComplexUnit(top, bottom, leftCom.getOp());
        }
      }
      else
      {
        if (f1.getUnit() == rightFull.getUnit() || f2.getUnit() == rightFull.getUnit())
        {
          Operation o = Operation.DIVIDE;
          if (f1.getUnit() == rightFull.getUnit())
          {
            f1 = new FullUnit(f1.getUnit(), f1.getPower() + rightFull.getPower());
          }
          if (f2.getUnit() == rightFull.getUnit())
          {
            if (f2.getPower() > rightFull.getPower())
            {
              f2 = new FullUnit(f2.getUnit(), f2.getPower() - rightFull.getPower());
            }
            else if (rightFull.getPower() > f2.getPower())
            {
              f2 = new FullUnit(f2.getUnit(), rightFull.getPower() - f2.getPower());
              o = Operation.MULTIPLY;
            }
            else
            {
              return f1;
            }
          }
          return new CompoundUnit(f1, f2, o);
        }
        else
        {
          top = new CompoundUnit(leftCom.getNumerator(), rightFull, Operation.MULTIPLY);
          bottom = leftCom.getDenominator();
          return new ComplexUnit(top, bottom, leftCom.getOp());
        }
      }

    }
    return null;

  }

  /**
   * Compounds two operands by division.
   * 
   * @param left  the left operand
   * @param right the right operand
   * @return a compounded unit
   */
  private static DisplayUnit div(final Operand left, final Operand right)
  {
    Unit foot = new Unit(FT, 'l', 'b');
    FullUnit f = new FullUnit(foot, 1);
    CompoundUnit u = new CompoundUnit(f, f, Operation.MULTIPLY);
    boolean isleftFull = left.getUnit().getClass().equals(f.getClass());
    boolean isrightFull = right.getUnit().getClass().equals(f.getClass());

    if (isleftFull && isrightFull)
    {
      return new CompoundUnit((FullUnit) left.getUnit(), (FullUnit) right.getUnit(),
          Operation.DIVIDE);
    }

    boolean leftCompound = left.getUnit().getClass().equals(u.getClass());
    boolean rightCompound = right.getUnit().getClass().equals(u.getClass());
    if (leftCompound && !rightCompound)
    {
      CompoundUnit leftCom = (CompoundUnit) left.getUnit();
      FullUnit rightFull = (FullUnit) right.getUnit();
      DisplayUnit top;
      DisplayUnit bottom;
      FullUnit f1 = leftCom.getNumerator();
      FullUnit f2 = leftCom.getDenominator();

      if (leftCom.getOp() == Operation.MULTIPLY)
      {
        Operation o = Operation.DIVIDE;
        if (f1.getUnit() == rightFull.getUnit() || f2.getUnit() == rightFull.getUnit())
        {
          if (f1.getUnit() == rightFull.getUnit())
          {
            if (f1.getPower() > rightFull.getPower())
            {
              f1 = new FullUnit(f1.getUnit(), f1.getPower() - rightFull.getPower());
              o = Operation.MULTIPLY;
            }
            else if (rightFull.getPower() > f1.getPower())
            {
              FullUnit temp = f1;
              f1 = f2;
              f2 = new FullUnit(temp.getUnit(), rightFull.getPower() - temp.getPower());
            }
            else
            {
              return f2;
            }
          }
          else if (f2.getUnit() == rightFull.getUnit())
          {
            if (f2.getPower() > rightFull.getPower())
            {
              f2 = new FullUnit(f2.getUnit(), f2.getPower() - rightFull.getPower());
              o = Operation.MULTIPLY;
            }
            else if (rightFull.getPower() > f2.getPower())
            {
              f2 = new FullUnit(f2.getUnit(), rightFull.getPower() - f2.getPower());
            }
            else
            {
              return f1;
            }
          }
          return new CompoundUnit(f1, f2, o);
        }
        else
        {

          top = leftCom;
          bottom = right.getUnit();
        }
      }
      else
      {
        if (f1.getUnit() == rightFull.getUnit() || f2.getUnit() == rightFull.getUnit())
        {
          Operation o = Operation.DIVIDE;
          if (f1.getUnit() == rightFull.getUnit())
          {
            if (f1.getPower() > rightFull.getPower())
            {
              f1 = new FullUnit(f1.getUnit(), f1.getPower() - rightFull.getPower());
            }
            else if (rightFull.getPower() > f1.getPower())
            {
              f1 = new FullUnit(f1.getUnit(), f1.getPower() - rightFull.getPower());
              f2 = new FullUnit(f2.getUnit(), -f2.getPower());
              o = Operation.MULTIPLY;
            }
            else
            {
              return new FullUnit(f2.getUnit(), -f2.getPower());
            }
          }
          else if (f2.getUnit() == rightFull.getUnit())
          {
            f2 = new FullUnit(f2.getUnit(), f2.getPower() + rightFull.getPower());
          }
          return new CompoundUnit(f1, f2, o);
        }
        else
        {
          top = leftCom.getNumerator();
          bottom = new CompoundUnit(leftCom.getDenominator(), rightFull, Operation.MULTIPLY);
        }
      }
      return new ComplexUnit(top, bottom, Operation.DIVIDE);
    }
    return null;
  }

}
