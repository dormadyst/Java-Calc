package utilities;

import java.util.LinkedList;

/**
 * NumberFormat provides a utility for formatting string representations of doubles with selected
 * thousands separators.
 * 
 * @author S24Team2D
 * @version 1
 */
public class NumberFormat
{

  /**
   * If enabled, this method takes a string representation of a double and returns a string
   * formatted with the selected thousands separators.
   * 
   * @param initial
   *          The initial string representation of the double
   * @param enable
   *          A boolean flag indicating whether to enable formatting
   * @param period
   *          A boolean flag indicating whether to use a period as the decimal separator
   * @return The formatted string representing the double
   */
  public static String thouSeperator(final String initial, final boolean enable,
      final boolean period)
  {
    if (!enable)
    {
      return initial;
    }

    char sep = ',';
    char sep2 = ' ';

    if (period)
    {
      sep2 = sep;
      sep = '.';
    }

    char[] chars = initial.toCharArray();

    int index = chars.length - 1;
    char c = chars[index];

    while ((c != '.') && (index >= 0))
    {
      c = chars[index];
      index--;

    }

    if ((chars[index + 1] == '.') && (period))
    {
      chars[index + 1] = sep2;
    }

    if (index < 0)
    {
      index = chars.length - 1;
    }

    int count = 0;
    LinkedList<Character> list = new LinkedList<>();
    for (char x : chars) list.add(x);

    for (int i = index; i >= 0; i--)
    {
      count++;
      if (count == 3)
      {
        count = 0;
        list.add(i, sep);
      }
    }

    char z = list.getFirst();

    if ((z == ',') || (z == '.'))
    {
      list.removeFirst();
    }

    String res = new String();

    for (int i = 0; i < list.size(); i++)
    {
      res = res + list.get(i);
    }

    return res;

  }

}
