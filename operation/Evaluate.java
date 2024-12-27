package operation;

import unit.*;

/**
 * Evaluate class provides methods for evaluating expressions.
 * 
 * @author S24Team2D
 * @version 1
 */
public class Evaluate
{
  private static final String IN = "in";
  /**
   * Evaluates the given expression and returns the result.
   * 
   * @param exp
   *          the expression to evaluate
   * @return the evaluated operand
   */
  public static Operand evaluate(final Expression exp)
  {
    switch (exp.getOp())
    {
      case ADD:
        return add(exp);
      case SUBTRACT:
        return sub(exp);
      case MULTIPLY:
        return mult(exp);
      case DIVIDE:
      default:
        return div(exp);
    }

  }
  // test

  /**
   * Performs addition operation on the provided expression.
   * 
   * @param exp
   *          the expression to be added
   * @return the operand after addition
   */
  private static Operand add(final Expression exp)
  {
    DisplayUnit leftUnit = exp.getLeft().getUnit();
    DisplayUnit rightUnit = exp.getRight().getUnit();

    try
    {
      FullUnit l = (FullUnit) leftUnit;
      if (l.getUnit().getType() == 'x')
      {
        double val = exp.getLeft().getValue() + exp.getRight().getValue();
        return new Result(val, leftUnit, val < 0);
      }
    }
    catch (ClassCastException e)
    {
      int x = 0;
    }

    if (leftUnit.equal(rightUnit) == 0)
    {
      Operand left = exp.getLeft();
      Operand right = exp.getRight();

      double lVal = left.getValue();
      double rVal = right.getValue();
      boolean resNeg = false;

      // if (left.getNegative())
      // {
      // lVal = -1 * lVal;
      // }

      // if (right.getNegative())
      // {
      // rVal = -1 * rVal;
      // }
      //
      double res = lVal + rVal;
      //
      if (res < 0)
      {
        resNeg = true;
      }

      return new Result(res, left.getUnit(), resNeg);

    }
    else
    {
      return null;
    }

  }

  /**
   * Performs subtraction operation on the provided expression.
   * 
   * @param exp
   *          the expression to be subtracted
   * @return the operand after subtraction
   */
  private static Operand sub(final Expression exp)
  {
    DisplayUnit leftUnit = exp.getLeft().getUnit();
    DisplayUnit rightUnit = exp.getRight().getUnit();

    try
    {
      FullUnit l = (FullUnit) leftUnit;
      if (l.getUnit().getType() == 'x')
      {
        double val = exp.getLeft().getValue() - exp.getRight().getValue();
        return new Result(val, leftUnit, val < 0);
      }
    }
    catch (ClassCastException e)
    {
      int x = 0;
    }

    if (leftUnit.equal(rightUnit) == 0)
    {
      Operand left = exp.getLeft();
      Operand right = exp.getRight();

      double lVal = left.getValue();
      double rVal = right.getValue();
      boolean resNeg = false;

      // if (left.getNegative())
      // {
      // lVal = -1 * lVal;
      // }
      ////
      // if (right.getNegative())
      // {
      // rVal = -1 * rVal;
      // }

      double res = lVal - rVal;

      if (res < 0)
      {
        resNeg = true;
      }

      return new Result(res, left.getUnit(), resNeg);

    }
    else
    {
      return null;
    }

  }

  /**
   * Performs multiplication operation on the provided expression.
   * 
   * @param exp
   *          the expression to be multiplied
   * @return the operand after multiplication
   */
  private static Operand mult(final Expression exp)
  {
    Operand left = exp.getLeft();
    Operand right = exp.getRight();
    DisplayUnit leftU = left.getUnit();
    DisplayUnit rightU = right.getUnit();
    Unit inch = new Unit(IN, 'l', 'a');
    FullUnit f = new FullUnit(inch, 4);
    double leftV = left.getValue();
    double rightV = right.getValue();
    boolean isLeftFull = leftU.getClass().equals(f.getClass());
    boolean isRightFull = rightU.getClass().equals(f.getClass());
    DisplayUnit u = UnitCompound.compoundUnit(left, right, Operation.MULTIPLY);

    if (isLeftFull && isRightFull)
    {
      FullUnit f1 = (FullUnit) leftU;
      FullUnit f2 = (FullUnit) rightU;
      int isEqual = f1.equal(f2);
      if (isEqual == 0 || isEqual == 0)
      {
        u = new FullUnit(f1.getUnit(), f1.getPower() + f2.getPower());
      }
    }

    // if(left.getNegative())
    // {
    // leftV = leftV * -1;
    // }
    ////
    // if(right.getNegative())
    // {
    // rightV = rightV * -1;
    // }

    double finV = leftV * rightV;

    boolean isNeg = finV < 0;

    return new Result(finV, u, isNeg);

  }

  /**
   * Performs division operation on the provided expression.
   * 
   * @param exp
   *          the expression to be divided
   * @return the operand after division
   */
  private static Operand div(final Expression exp)
  {
    Operand left = exp.getLeft();
    Operand right = exp.getRight();

    if (right.getValue() == 0)
    {
      return null;
    }
    DisplayUnit leftU = left.getUnit();
    DisplayUnit rightU = right.getUnit();
    Unit inch = new Unit(IN, 'l', 'a');
    FullUnit f = new FullUnit(inch, 4);
    double leftV = left.getValue();
    double rightV = right.getValue();
    boolean isLeftFull = leftU.getClass().equals(f.getClass());
    boolean isRightFull = rightU.getClass().equals(f.getClass());
    DisplayUnit u = UnitCompound.compoundUnit(left, right, Operation.DIVIDE);

    if (isLeftFull && isRightFull)
    {
      FullUnit f1 = (FullUnit) leftU;
      FullUnit f2 = (FullUnit) rightU;
      int isEqual = f1.equal(f2);
      if (isEqual == 0 || isEqual == 2)
      {
        u = new FullUnit(f1.getUnit(), f1.getPower() - f2.getPower());
      }
    }

    // if(left.getNegative())
    // {
    // leftV = leftV * -1;
    // }
    //
    // if(right.getNegative())
    // {
    // rightV = rightV * -1;
    // }

    double finV = leftV / rightV;

    boolean isNeg = finV < 0;

    return new Result(finV, u, isNeg);
  }

}
