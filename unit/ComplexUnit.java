package unit;

import operation.*;

/**
 * 
 */
public class ComplexUnit implements DisplayUnit
{
  private DisplayUnit left;
  private DisplayUnit right;
  private Operation op;

  /**
   * 
   * @param left
   * @param right
   * @param op
   */
  public ComplexUnit(final DisplayUnit left, final DisplayUnit right, final Operation op)
  {
    this.left = left;
    this.right = right;
    this.op = op;
  }


  @Override
  public int equal(final DisplayUnit unit2)
  {
    return -1;
  }

  /**
   * 
   */
  @Override
  public String toString()
  {
    switch (op)
    {
      case MULTIPLY:
        return String.format("%s-%s", left.toString(), right.toString());
      case DIVIDE:
        return String.format("%s/%s", left.toString(), right.toString());
      case ADD:
      case SUBTRACT:
      default:
    }
    return null;
  }
}
