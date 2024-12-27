package operation;

/**
 * Expression class represents a mathematical expression consisting of two operands and an
 * operation.
 * 
 * @author S24Team2D
 * @version 1
 */
public class Expression
{

  private Operand left;
  private Operand right;
  private Operation op;

  /**
   * Expression constructor initializes an expression with the provided operands and operation.
   * 
   * @param left
   *          The left operand
   * @param right
   *          The right operand
   * @param op
   *          The operation
   */
  public Expression(final Operand left, final Operand right, final Operation op)
  {
    this.left = left;
    this.right = right;
    this.op = op;
  }

  /**
   * 
   * @return left
   */
  public Operand getLeft()
  {
    return left;

  }

  /**
   * 
   * @return right
   */
  public Operand getRight()
  {
    return right;

  }

  /**
   * 
   * @return op
   */
  public Operation getOp()
  {
    return op;
  }

  /**
   * Returns a String representation of the Expression, including the operands and operation.
   * 
   * @return test
   */
  public String toString()
  {
    switch (op)
    {
      case ADD:
        return String.format("%s + %s", left.toString(), right.toString());
      case SUBTRACT:
        return String.format("%s - %s", left.toString(), right.toString());
      case MULTIPLY:
        return String.format("%s * %s", left.toString(), right.toString());
      case DIVIDE:
      default:
        return String.format("%s / %s", left.toString(), right.toString());
    }
  }

}
