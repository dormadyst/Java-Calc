package app;

import gui.*;

import java.nio.file.Path;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

/**
 * Driver for Window class.
 * 
 * @author S24Team2d
 * @version 1.0
 */
public class Driver implements Runnable
{
//  private Locale locale = Locale.getDefault();
//  ResourceBundle res = ResourceBundle.getBundle("resources/ResourceBundle", locale);

  /**
   * The entry point of the application.
   * 
   * @param args
   *          The command line arguments (which are ignored)
   */
  public static void main(final String[] args)
  {
    SwingUtilities.invokeLater(new Driver());
  }

  /**
   * The code to execute in the event dispatch thread.
   */
  public void run()
  {
  //  try
  //  {
  //    String id = "temp";
  //    String subdir = res.getString("helpPagePath");
  //    Path va = ResourceCopier.copyResourcesToTemp(id, subdir);
  //    Path helpFilePath = va.resolve("help.html");
  //    // directory its going at
  //  }
  //  catch (Exception e)
  //  {
  //    e.printStackTrace();
  //  }
    Window window = new Window();
    window.setVisible(true);
  }
}
