package gui;

import java.awt.*;

import javax.swing.*;

/**
 * CalculatorOperations class provides utility methods for performing calculator operations.
 * 
 * @author S24Team2d
 * @version 1
 */
public class CalculatorOperations
{

  private static final String ER = "Error";
  private static final String P = ".";
  private static final String D = "-";
  private static final String Z = "0";

  /**
   * Checks if the input in the current operand field is a number.
   * 
   * @param currentOperand
   *          the JTextField containing the current operand
   * @param parent
   *          the parent component for displaying error messages
   * @return true if the input is a number, false otherwise
   */
  public static boolean numberCheck(final JTextField currentOperand, final Component parent)
  {
    String input = currentOperand.getText().trim();
    if (!input.isEmpty() && input.matches(".*\\d.*"))
    {
      return true;
    }
    else
    {
      JOptionPane.showMessageDialog(parent, "Invalid input: Please enter a number.", ER,
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
  }

  /**
   * Checks if a unit is selected in the units dropdown.
   * 
   * @param unitsDropdown
   *          the JComboBox containing units
   * @param parent
   *          the parent component for displaying error messages
   * @return true if a unit is selected, false otherwise
   */
  public static boolean unitCheck(final JComboBox<String> unitsDropdown, final Component parent)
  {
    String selectedUnit = unitsDropdown.getSelectedItem().toString();
    if (selectedUnit.equals(""))
    {
      JOptionPane.showMessageDialog(parent, "Please select a unit.", ER, JOptionPane.ERROR_MESSAGE);
      return false;
    }
    else
    {
      return true;
    }
  }

  /**
   * Appends text to the current operand JTextField.
   * 
   * @param text
   *          the text to be appended
   * @param currentOperand
   *          the JTextField containing the current operand
   * @param unitsDropdown
   *          the JComboBox containing units
   * @param operationSelected
   *          indicates if an operation is selected
   * @param selectedOperator
   *          the selected operator
   * @param display
   *          the display JTextField
   */
  public static void appendToOperand(final String text, final JTextField currentOperand,
      final JComboBox<String> unitsDropdown, final boolean operationSelected,
      final String selectedOperator, final JTextField display)
  {
    String currentText = currentOperand.getText();
    if (text.matches("[0-9]") || text.equals(P))
    {
      if (currentText.equals(Z) && !text.equals(P))
      {
        currentOperand.setText(text);
      }
      else if (!currentText.contains(P) || !text.equals(P))
      {
        currentOperand.setText(currentText + text);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(currentOperand.getParent(),
          "Invalid input: Please enter a number or decimal point.", ER, JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Removes the last character from the current operand JTextField.
   * 
   * @param currentOperand
   *          the JTextField containing the current operand
   */
  public static void backspace(final JTextField currentOperand)
  {
    String text = currentOperand.getText();
    if (text.length() > 1)
    {
      currentOperand.setText(text.substring(0, text.length() - 1));
    }
    else
    {
      currentOperand.setText(Z);
    }
  }

  /**
   * Toggles the sign of the current operand JTextField.
   * 
   * @param currentOperand
   *          the JTextField containing the current operand
   */
  public static void toggleSign(final JTextField currentOperand)
  {
    String text = currentOperand.getText();
    if (!text.equals(Z))
    {
      currentOperand.setText(text.startsWith(D) ? text.substring(1) : D + text);
    }
  }

  /**
   * Performs a mathematical operation on two operands.
   * 
   * @param operand1
   *          the first operand
   * @param operand2
   *          the second operand
   * @param operator
   *          the operator
   * @param parent
   *          the parent component for displaying error messages
   * @return the result of the operation
   */
  public static double performOperation(final double operand1, final double operand2,
      final String operator, final Component parent)
  {
    double res = 0;
    switch (operator)
    {
      case "+":
        res = operand1 + operand2;
        break;
      case D:
        res = operand1 - operand2;
        break;
      case "X":
        res = operand1 * operand2;
        break;
      case "/":
        if (operand2 == 0)
        {
          JOptionPane.showMessageDialog(parent, "Cannot divide by zero.", ER,
              JOptionPane.ERROR_MESSAGE);
          res = 0;
          break;
        }
        res = operand1 / operand2;
        break;
      default:
        res = 0; // Default case, though we should never get here
    }
    return res;
  }
}
