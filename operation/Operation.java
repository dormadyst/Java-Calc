package operation;

/**
 * Operation enum represents mathematical operations such as addition, subtraction, multiplication,
 * and division.
 */
public enum Operation
{

  ADD, SUBTRACT, MULTIPLY, DIVIDE;

  /**
   * Converts a string representation of an operation into its corresponding Operation enum value.
   * 
   * @param text
   *          The string representation of the operation
   * @return The Operation enum value corresponding to the input string
   */
  public static Operation fromString(final String text)
  {
    if (text.equals("+"))
    {
      return ADD;
    }

    else if (text.equals("-"))
    {
      return SUBTRACT;
    }

    else if (text.equals("X"))
    {
      return MULTIPLY;
    }

    else
    {
      return DIVIDE;
    }

  }
}
