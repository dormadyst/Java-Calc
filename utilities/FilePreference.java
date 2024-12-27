package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class provides an abject to represent the preferences of a user
 * and methods to read and write them from a file.
 */
public class FilePreference
{
  private static final String PREF = "preferences.txt";
  private boolean commaSep;
  private boolean fixed;
  private boolean maximum;
  private int digits;
  
  
  
  /**
   * The default constructor for the FilePreference object.
   */
  public FilePreference() 
  {
    commaSep = false;
    fixed = true;
    maximum = false;
    digits = 2;
  }
  
  /**
   * The parameterized constructor for the FilePreference object.
   * @param commaSep A flag to determine if comma separation is used
   * @param fixed A boolean flag that determines the digits value to be 
   *        a fixed number
   * @param maximum A boolean flag that determines the digits value to be 
   *        a maximum number
   * @param digits The number of allowed digits after a decimal
   */
  public FilePreference(final boolean commaSep, final boolean fixed, 
      final boolean maximum, final int digits)
  {
    this.commaSep = commaSep;
    this.fixed = fixed;
    this.maximum = maximum;
    this.digits = digits;
    
  }

  /**
   * Simple getter for the comma separation flag.
   * @return The comma separation flag
   */
  public boolean isCommaSep()
  {
    return commaSep;
  }

  /**
   * Simple getter for the fixed flag.
   * @return The fixed flag
   */
  public boolean isFixed()
  {
    return fixed;
  }

  /**
   * Simple getter for the maximum flag.
   * @return The maximum flag
   */
  public boolean isMaximum()
  {
    return maximum;
  }

  /**
   * Simple getter for the digits.
   * @return The digits value
   */
  public int getDigits()
  {
    return digits;
  }
  
  /**
   * This method saves a set of preferences to the default file.
   * @throws IOException Throws an io exception if the file can't be written 
   * to or created
   */
  public void save() throws IOException 
  {
    this.saveAs(new File(PREF));
  }
  
  /**
   * This method saves a set of preferences to the specified file.
   * @param file The destination file
   * @throws IOException Throws an io exception if the file can't be written 
   * to or created
   */
  public void saveAs(final File file) throws IOException 
  {
    file.createNewFile();
    FileWriter w = new FileWriter(file);
    w.write(this.toString());
    w.close();
  }
  
  /**
   * This method loads a set of preferences from the default file.
   * @throws FileNotFoundException Throws an exception if the provided 
   * file cannot be found
   */
  public void open() throws FileNotFoundException 
  {
    this.openAs(new File(PREF));
  }
  
  /**
   * This method loads a set of preferences from the specified file.
   * @param file The destination file
   * @throws FileNotFoundException Throws an exception if the provided 
   * file cannot be found
   */
  public void openAs(final File file) throws FileNotFoundException 
  {
    Scanner s = new Scanner(file);
    s.useDelimiter(",");
    this.commaSep = s.nextBoolean();
    this.fixed = s.nextBoolean();
    this.maximum = s.nextBoolean();
    this.digits = s.nextInt();
    s.close();
    
  }
  
  /**
   * An overridden toString method for the FilePreference class.
   * @return The string representation of the FilePreference class
   */
  @Override
  public String toString() 
  {
    return String.format("%b,%b,%b,%d", commaSep, fixed, maximum, digits);
  }
  
  

}
