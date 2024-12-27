package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import operation.*;

/**
 * FileHistory class manages a history of expressions and results stored in a file.
 * 
 * @author S24Team2D
 * @version 1
 */
public class FileHistory
{

  private static final String NL = "\n";
  private static final String HISTORY = "history.txt";
  private ArrayList<String> history;

  /**
   * Constructs a new FileHistory object.
   */
  public FileHistory()
  {
    history = new ArrayList<>();
  }

  /**
   * Adds an expression and its result to the history.
   * 
   * @param exp
   *          the expression
   * @param res
   *          the result
   * @throws IOException
   *           if an I/O error occurs
   */
  public void add(final Expression exp, final Result res) throws IOException
  {
    String s = exp.toString() + " = " + res.toString();
    history.add(s);
  }

  /**
   * Gets the history of expressions and results.
   * 
   * @return the history list
   */
  public List<String> getHistory()
  {
    ArrayList<String> l = new ArrayList<>();
    l.addAll(0, history);
    return l;
  }

  /**
   * Returns a string representation of the history.
   * 
   * @return a string representing the history
   */
  public String toString()
  {
    String s = "";
    for (String i : history)
    {
      s = s + i + NL;
    }
    return s;
  }

  /**
   * Gets the file storing the history.
   * 
   * @return the file containing the history
   * @throws IOException
   *           if an I/O error occurs
   */
  public File getFile() throws IOException
  {
    File file = new File(HISTORY);
    file.deleteOnExit();
    writeFile(file);
    return file;
  }

  /**
   * Writes the history to the specified file.
   * 
   * @param file
   *          the file to write to
   * @throws IOException
   *           if an I/O error occurs
   */
  public void writeFile(final File file) throws IOException
  {
    FileWriter w = new FileWriter(file);
    for (String s : history)
    {
      w.write(s + NL);
    }
    w.close();
  }
}
