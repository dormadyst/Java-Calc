package gui;

import javax.swing.*;
import javax.swing.border.*;

import app.ResourceCopier;
import operation.*;
import unit.*;
import utilities.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * The Window class represents the main window of the unit conversion application.
 * 
 * @author S24Team2D
 * @version 1
 */
public class Window extends JFrame implements ActionListener, KeyListener
{

  private static final long serialVersionUID = 1L;
  // Frequently used strings
  private static final String A = "About";
  private static final String U = "unitED";
  private static final String HELP = "Help";
  private static final String ZERO = "0";
  private static final String SPACE = " ";
  private static final String PLUS = "+";
  private static final String EQUALS = "=";
  private static final String ARROW = ">";
  private static final String MINUS = "-";
  private static final String MULT = "X";
  private static final String DIV = "/";
  private static final String CLEAR = "C";
  private static final String RESET = "R";
  private static final String DECIMAL = ".";
  private static final String PM = "+/-";
  private static final String BACKSPACE = "<";
  private static final String ERROR = "Error";
  private static final String EXP = "<html>x<sup>y</sup></html>";
  private static final String SAVE = "Save";
  private static final String F = "f";
  private static final String E_W_SPACE = " = ";
  private static final String EDIT = "Edit";
  private static final String OPEN = "Open";
  private static final String WINDOW = "NewWindow";
  private static final String SAVES = "SaveSuccess";
  private static final String SAVEE = "SaveError";
  private static final String OPC = "OperationCanceled";
  private static final String C = ": ";
  private static final String ERRORM = "Please select a unti from the dropdown menu.";

  // GUI Items
  private JMenuBar menuBar;
  private JMenu fileMenu;
  private JMenu helpMenu;
  private JMenu prefMenu;
  private JMenu unitsMenu;
  private JMenuItem addUnit;
  private JMenuItem exitItem;
  private JMenuItem saveSession;
  private JMenuItem aboutItem;
  private JMenuItem helpItem;
  private JMenuItem printItem;
  private JMenuItem editItem;
  private JTextField display;
  private JMenuItem prefSaveItem;
  private JMenuItem prefOpenItem;
  private JMenuItem prefSaveAsItem;
  private JMenuItem prefEditItem;
  private JTextField currentOperand;
  private JComboBox<String> unitsDropdown;
  private JTextField numberOfDigitsField;
  private JRadioButton maximumButton;
  private JRadioButton fixedButton;
  private ButtonGroup decimalFormatGroup;
  private boolean useCommaSeparation;
  private boolean usePeriodSeperation;
  private JPanel decimalSettingsPanel;

  private JMenuItem newWindowItem;


  private Result previousResult = null;
  private boolean unitless;
  private FileHistory history;
  private FilePreference preferences;

  //private Locale l = Locale.getDefault();

  private Operand left;
  private Operand right;
  private Operand result;
  private String selectedOperator = "";
  private Operation op;
  private boolean operationSelected = false;
  private List<JButton> operatorButtons = new ArrayList<>(); // Holds operator buttons

  // For History
  private JDialog historyDialog;
  private JTextArea historyTextArea; // Display area for the history
  private boolean popOutVisible = false; // Flag to track the pop-out visibility

  //ResourceBundle r = ResourceBundle.getBundle("resources/ResourceBundle", l);

  /**
   * Constructor.
   */
  public Window()
  {
    // Set the title
    setTitle(U);

    history = new FileHistory();
    preferences = new FilePreference();
    
    try
    {
      preferences.open();
    } catch(FileNotFoundException E) 
    {
      System.out.print("");
    }
      // Create the menu bar and menus

    menuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    prefMenu = new JMenu("Preference");
    unitsMenu = new JMenu("Units");
    helpMenu = new JMenu(HELP);
    unitless = false;

    // Create and add menu items
    exitItem = new JMenuItem("Exit");
    addUnit = new JMenuItem("Add");
    aboutItem = new JMenuItem(A);
    helpItem = new JMenuItem(HELP);
    printItem = new JMenuItem("Print Session");
    saveSession = new JMenuItem("Save Session");
    editItem = new JMenuItem(EDIT);
    prefSaveAsItem = new JMenuItem("Save As");
    prefEditItem = new JMenuItem(EDIT);
    prefOpenItem = new JMenuItem(("Open"));
    prefSaveItem = new JMenuItem(("Save"));
    newWindowItem = new JMenuItem(("New Window")); // For new window
    numberOfDigitsField = new JTextField(10); // 10 columns wide
    maximumButton = new JRadioButton(("Maximum"));
    fixedButton = new JRadioButton(("Fixed"), true); // Fixed is selected by default

    usePeriodSeperation = Boolean.parseBoolean(("periodSep"));
    // New toggle button for comma separation
    JToggleButton toggleCommaSeparation = new JToggleButton(("CommaSeparationOff"));
    toggleCommaSeparation.addItemListener(e -> 
    {
      boolean selected = toggleCommaSeparation.isSelected();
      toggleCommaSeparation.setText(("CommaSeparation") + SPACE 
          + (selected ? ("On") : ("Off")));
      // Here, you would modify the boolean that tracks the state of comma separation
      useCommaSeparation = selected; // Assume `useCommaSeparation` is a boolean field
    });
    

    // Adds JMenuItems to their respective JMenu
    fileMenu.add(exitItem);
    fileMenu.add(printItem);
    fileMenu.add(saveSession);
    fileMenu.add(newWindowItem);
    helpMenu.add(aboutItem);
    aboutItem.addActionListener(this);
    unitsMenu.add(addUnit);
    addUnit.addActionListener(this);
    helpMenu.add(helpItem);
    helpItem.addActionListener(this);
    editItem.addActionListener(this);

    addKeyListener(this);
    setFocusable(true);

    // Adds JmenuItems to the menuBar
    menuBar.add(fileMenu);
    menuBar.add(prefMenu);
    menuBar.add(unitsMenu);
    menuBar.add(helpMenu);
    setJMenuBar(menuBar);

    decimalFormatGroup = new ButtonGroup();
    decimalFormatGroup.add(maximumButton);
    decimalFormatGroup.add(fixedButton);
    decimalSettingsPanel = new JPanel(new GridLayout(0, 1));
    decimalSettingsPanel.add(new JLabel(("NumberofDigits")));
    decimalSettingsPanel.add(numberOfDigitsField);
    decimalSettingsPanel.add(maximumButton);
    decimalSettingsPanel.add(fixedButton);
    decimalSettingsPanel.add(toggleCommaSeparation);

    prefMenu.add(prefEditItem);
    prefMenu.add(prefOpenItem);
    prefMenu.add(prefSaveItem);
    prefMenu.add(prefSaveAsItem);

    prefEditItem.addActionListener(this);
    prefSaveAsItem.addActionListener(this);
    prefOpenItem.addActionListener(this);
    prefSaveItem.addActionListener(this);

    // Load the logo image
    BufferedImage originalImage = null;
    try
    {
      originalImage = ImageIO.read(getClass().getResource("unitED_Logo.png"));
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }
    Image resizedImage = originalImage.getScaledInstance(100, 35, Image.SCALE_SMOOTH); // Resize to
                                                                                       // new width
                                                                                       // and height
    ImageIcon logoIcon = new ImageIcon(resizedImage);
    JLabel logoLabel = new JLabel(logoIcon);
    logoLabel.setHorizontalAlignment(JLabel.LEFT); // Align the logo to the left
    logoLabel.setBorder(new EmptyBorder(10, 20, 10, 20)); // Top, Left, Bottom, Right padding

    // History Initialization
    historyDialog = new JDialog(this, "History", false);
    historyTextArea = new JTextArea(10, 30);
    historyTextArea.setEditable(false);
    historyTextArea.setBackground(new Color(0xE5E5E5));
    JScrollPane historyScrollPane = new JScrollPane(historyTextArea);
    historyDialog.add(historyScrollPane);
    historyDialog.pack();
    historyDialog.setSize(200, 300); // You can set your desired size

    // Display for the calculator, this is the top bar "result tab"
    display = new JTextField();
    display.setEditable(false);
    display.setHorizontalAlignment(JTextField.CENTER);
    display.setBackground(new Color(0xF59A9A)); // Set background color to a light red (F59A9A)
    // Set a colored border for the display
    Border displayBorder = new LineBorder(new Color(0x6b0700), 2); // Dark Red color, 2 pixels
                                                                   // thickness
    display.setBorder(displayBorder);
    display.setPreferredSize(new Dimension(200, 45)); // Set width as needed

    // Panel to hold the display with padding
    JPanel displayPanel = new JPanel(new BorderLayout());
    displayPanel.setBorder(new EmptyBorder(0, 50, 0, 50)); // Top, Left, Bottom, Right padding
    displayPanel.add(display, BorderLayout.CENTER); // Add the display in the center of the panel

    // Panel for the current operand and units dropdown
    currentOperand = new JTextField();
    currentOperand.setHorizontalAlignment(JTextField.CENTER);

    currentOperand.setBorder(new LineBorder(Color.BLACK, 1)); // Set border thickness and color
    currentOperand.setPreferredSize(new Dimension(200, 45)); // Set width as needed

    unitsDropdown = new JComboBox<>(UnitManager.getAllUnitSymbols().toArray(new String[0]));
    unitsDropdown.setEditable(true);
    unitsDropdown.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(final ActionEvent e)
      {
        @SuppressWarnings("unchecked")
        JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
        String newUnit = (String) comboBox.getEditor().getItem();
        // Check if the unit is new, not empty, and does not exist
        if (newUnit != null && !newUnit.trim().isEmpty()
            && UnitManager.getUnitBySymbol(newUnit) == null)
        {
          UnitManager.addCustomUnit(newUnit); // Add the new unit to the system
          comboBox.addItem(newUnit); // Optionally add to the dropdown for future selection
        }
      }
    });

    // Sub-panel for operand and units
    JPanel operandSubPanel = new JPanel(new BorderLayout());
    operandSubPanel.add(currentOperand, BorderLayout.CENTER);
    operandSubPanel.add(unitsDropdown, BorderLayout.EAST);

    // Operand panel setup
    JPanel operandPanel = new JPanel(new BorderLayout());
    operandPanel.setBorder(new EmptyBorder(20, 50, 20, 50)); // Padding around the sub-panel
    operandPanel.add(operandSubPanel, BorderLayout.CENTER); // Add sub-panel to operand panel

    JPanel logoAndDisplayPanel = new JPanel(new BorderLayout());
    logoAndDisplayPanel.add(logoLabel, BorderLayout.NORTH); // Add the logo on the left
    logoAndDisplayPanel.add(displayPanel, BorderLayout.CENTER); // Add the padded display panel
    logoAndDisplayPanel.add(operandPanel, BorderLayout.SOUTH);

    // Panel to hold the display and the operand panel
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(logoAndDisplayPanel, BorderLayout.NORTH); // Add the logo at the top

    // Buttons
    JPanel buttonPanel = new JPanel(new GridLayout(5, 5, 5, 10)); // 6 rows, 4 columns, 5px gaps
    buttonPanel.setBackground(new Color(0x746D6D));
    String[] buttons = {PM, CLEAR, RESET, PLUS, EXP, "7", "8", "9", MINUS, ARROW, "4", "5", "6",
        MULT, "", "1", "2", "3", DIV, "", ZERO, DECIMAL, "\u232B", EQUALS, ""}; // Replaced "<" with
                                                                                // Unicode for
                                                                                // "ERASE TO THE


    // Define the button color using hexadecimal value
    Color buttonColor = new Color(0xD6D3D3);

    for (String buttonText : buttons)
    {
      if (buttonText.isEmpty())
      {
        buttonPanel.add(new JLabel("")); // Add a filler label as a placeholder
      }
      else
      {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        button.setBackground(buttonColor); // Set the button's background color
        button.setOpaque(true); // Make the button opaque to ensure the color shows
        button.setBorderPainted(false); // Optional: remove the border if visual style requires
        buttonPanel.add(button);
        if (buttonText.equals(PLUS) || buttonText.equals(MINUS) || buttonText.equals(MULT)
            || buttonText.equals(DIV))
        {
          operatorButtons.add(button); // If this button is a binary operator, add it to the list
                                       // holding operator buttons

        }
      }
    }

    // Add components to the frame
    setLayout(new BorderLayout()); // Ensure the layout manager is set to BorderLayout
    add(topPanel, BorderLayout.NORTH); // Top panel with display and operand
    add(buttonPanel, BorderLayout.CENTER); // Button panel in the center

    // Set up listeners for menu items
    printItem.addActionListener(this);
    exitItem.addActionListener(this);
    saveSession.addActionListener(this);
    newWindowItem.addActionListener(this); // Listener for new Window

    // Configure the frame
    setSize(375, 575);
    Border windowBorder = new LineBorder(new Color(0x6b0700), 4); // Black border with 4 pixels
                                                                  // thickness
    getRootPane().setBorder(windowBorder);
    setLocationRelativeTo(null); // Center the window
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setFocusable(true);

  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    Object source = e.getSource();
    if (source == exitItem)
    {
      System.exit(0);
    }
    else if (source == printItem)
    {
      PrinterHelper.printComponent(historyTextArea);
    }
    else if (source == saveSession)
    {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle("Save History As");
      fileChooser.setApproveButtonText(SAVE);
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      int userSelection = fileChooser.showSaveDialog(this);

      if (userSelection == JFileChooser.APPROVE_OPTION)
      {
        File fileToSave = fileChooser.getSelectedFile();
        try
        {
          history.writeFile(fileToSave);
          JOptionPane.showMessageDialog(this,
              ("HistorySuccess") + fileToSave.getAbsolutePath(), 
              (SAVES),
              JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ex)
        {
          JOptionPane.showMessageDialog(this, ("HistoryFail") + ex.getMessage(),
              (SAVEE), JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    else if (source == aboutItem)
    {
      showAboutDialog();
    }
    else if (source == helpItem)
    {
      try
      {
        showHelp();
      }
      catch (IOException | URISyntaxException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    else if (source == prefEditItem)
    {
      // Display the custom dialog box
      int res = JOptionPane.showConfirmDialog(null, decimalSettingsPanel, 
          ("EditUserPreferences"),
          JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      
      // Check if the user clicks cancel
      if(res == JOptionPane.OK_OPTION)
      {
        String numberOfDigits = numberOfDigitsField.getText().trim();
        int digits = 0;
        try
        {
          digits = Integer.parseInt(numberOfDigits);
        }
        catch (NumberFormatException z)
        {
          // JOptionPane.showMessageDialog(this, "Invalid number of digits", "Format Error",
          // JOptionPane.ERROR_MESSAGE);
          // return String.valueOf(res);
        }
        preferences = new FilePreference(useCommaSeparation,
            fixedButton.isSelected(),maximumButton.isSelected(),digits);
      }

      // Check if the user clicks cancel
      if (res == JOptionPane.CANCEL_OPTION)
      {
        // Handle cancel option
        JOptionPane.showMessageDialog(null, (OPC));

      } 
    }
    else if (source == prefOpenItem)
    {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle(("OpenPreferences"));
      fileChooser.setApproveButtonText((OPEN));
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      int userSelection = fileChooser.showSaveDialog(this);

      if (userSelection == JFileChooser.APPROVE_OPTION)
      {
        File fileToOpen = fileChooser.getSelectedFile();
        try
        {
          preferences.openAs(fileToOpen);
          JOptionPane.showMessageDialog(this,
              ("PreferencesSuccess") + fileToOpen.getAbsolutePath(),
              ("OpenSuccessful"), JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ex)
        {
          JOptionPane.showMessageDialog(this, ("FailOpen") + ex.getMessage(),
              ("OpenError"), JOptionPane.ERROR_MESSAGE);
        }
      }

    }
    else if (source == prefSaveItem)
    {
      try
      {
        preferences.save();
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
    else if (source == prefSaveAsItem)
    {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle(("SavePreferencesAs"));
      fileChooser.setApproveButtonText(SAVE);
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      int userSelection = fileChooser.showSaveDialog(this);

      if (userSelection == JFileChooser.APPROVE_OPTION)
      {
        File fileToSave = fileChooser.getSelectedFile();
        try
        {
          preferences.saveAs(fileToSave);
          JOptionPane.showMessageDialog(this,
              ("PreferencesSuccessTo") + fileToSave.getAbsolutePath(),
              (SAVES), JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ex)
        {
          JOptionPane.showMessageDialog(this, ("PreferenceSaveFail")
              + SPACE + ex.getMessage(),
              (SAVEE), JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    else if (source == addUnit)
    {
      addUnit();
    }
    else if (source instanceof JButton)
    {
      JButton button = (JButton) source;
      String buttonText = button.getText();
      processButton(buttonText);
    }
    else if (e.getActionCommand().equals((WINDOW)))
    {
      // Run the new instance
      SwingUtilities.invokeLater(new Runnable()
      {
        public void run()
        {
          new Window().setVisible(true); // Create a new instance of the Window
        }
      });
    }
    this.addKeyListener(this);

  }

  /**
   * This method creates and displays a dialog box to accept new unit details from the user. It
   * collects information about the unit name, type, and conversion factor, and processes this input
   * if the user confirms the operation. If the user cancels, it displays a cancellation message.
   */
  private void addUnit()
  {
    // Create a new JPanel to hold input fields
    JPanel panel = new JPanel(new GridLayout(0, 1));

    // Add input fields to the panel
    JTextField unit = new JTextField(20);
    panel.add(new JLabel(("EnterNewLikeUnit")+SPACE));
    panel.add(unit);
    JTextField type = new JTextField(20);
    panel.add(new JLabel(("EnterType")+SPACE));
    panel.add(type);
    JTextField conv = new JTextField(20);
    panel.add(new JLabel(("EnterConversionFactor")));
    panel.add(conv);

    // Display the custom dialog box
    int res = JOptionPane.showConfirmDialog(null, panel, ("LoadUnitsfromFile"),
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    // Check if the user clicks OK
    if (res == JOptionPane.OK_OPTION)
    {
      // Get the unit name, type, and conversion factor from the text fields
      String unit2 = unit.getText();
      String type2 = type.getText();
      String conv2 = conv.getText();
      loadFactorsFromInput(unit2, type2, conv2);
    }
    else if (res == JOptionPane.CANCEL_OPTION)
    {
      // Handle cancel option
      JOptionPane.showMessageDialog(null, (OPC));
    }
  }

  /**
   * Processes input data to create a custom unit in the system. This method interprets the unit
   * type to assign a specific code, adds the unit to the unit manager, and manages conversion
   * factors for both directions between the new unit and its corresponding base unit.
   *
   * @param unit
   *          The name of the new unit to be added.
   * @param type
   *          A string representing the type of the unit which determines the conversion logic.
   * @param conv
   *          A string representing the conversion factor from this unit to a base unit.
   */
  private void loadFactorsFromInput(final String unit, final String type, final String conv)
  {
    // Extract the first character of the type as a key identifier.
    char type2 = type.charAt(0);
    // Parse the conversion factor from String to double.
    double conv2 = Double.parseDouble(conv);
    char code;

    // Assign a code based on the type of the unit.
    if (type2 == 'l')
    {
      code = 'i';
    }
    else if (type2 == 'w')
    {
      code = 'f';
    }
    else if (type2 == 'm')
    {
      code = 'c';
    }
    else if (type2 == 'v')
    {
      code = 'f';
    }
    else if (type2 == 't')
    {
      code = 'e';
    }
    else
    {
      code = 'b'; // default code
    }

    // Add the custom unit with determined code to the UnitManager.
    UnitManager.addCustomUnit(unit, type2, code);

    // Store the conversion factor from this unit to the base unit using a unique key.
    ConversionFactors.getFactors().put(String.valueOf(type2) + code + 't', conv2);

    // Calculate and store the reverse conversion factor from the base unit to this unit.
    ConversionFactors.getFactors().put(String.valueOf(type2) + code + 'f', 1 / conv2);

    // Add the new unit to the units dropdown menu for user selection.
    unitsDropdown.addItem(unit);
  }

  /**
   * Opens a help page in the default web browser. The method locates a local HTML file specified in
   * the resources, copies it to a temporary directory, and opens it. If there are issues opening
   * the file, an error message is displayed.
   *
   * @throws IOException
   *           If an I/O error occurs while handling the file operations or opening the URI.
   * @throws URISyntaxException
   *           If a URI cannot be created for the path to the help file.
   */
  private void showHelp() throws IOException, URISyntaxException
  {
    String pathS = "helpPagePath";
    // Retrieve the help file path from resource strings.
    String filePath = (pathS);
    String id = "temp";
    String subdir = (pathS);

    // Copy help resources to a temporary directory for viewing.
    Path va = ResourceCopier.copyResourcesToTemp(id, subdir);
    Path helpFilePath = va.resolve("help.html");

    try
    {
      // Create a Path object from the file path
      Path path = Paths.get(filePath);

      // Resolve the path to obtain the absolute path (removes any relative elements)
      Path absolutePath = helpFilePath.toAbsolutePath();

      // Convert the Path to a URI (properly encoded)
      URI uri = absolutePath.toUri();

      // Open the URI in the default web browser
      Desktop.getDesktop().browse(uri);
    }
    catch (IOException e)
    {
      // Display an error message if unable to open the help content
      JOptionPane.showMessageDialog(this, "Error opening help content", ERROR,
          JOptionPane.ERROR_MESSAGE);
      e.printStackTrace(); // Print stack trace for debugging
    }
  }

  /**
   * Displays an "About" dialog box providing information about the application. The dialog includes
   * the application name, version, and developer information. The content of the dialog is
   * dynamically constructed from resources and fixed strings.
   */
  private void showAboutDialog()
  {
    // Retrieve the application name from resources; assuming 'U' is a key or variable that was
    // meant to be replaced
    String applicationName = U; // This should be replaced with r.getString("appName") or similar

    // Define the application version
    String version = "1.0";

    // Developer's name, ideally fetched from a resource or configuration
    String developer = "S24Team2D"; // Replace this with the actual developer's name from resources
                                    // if available

    // Construct the About message using format strings for clean assembly
    String aboutMessage = String.format("%s - %s\nVersion %s\n%s %s", applicationName,
        ("UnitConversionApplication"), version, ("DevelopedBy"), developer);

    // Display the About dialog with the constructed message
    JOptionPane.showMessageDialog(this, aboutMessage, U,
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Handles button press actions for a calculator application. This method interprets the button
   * text and performs corresponding calculator operations. It updates the UI based on the operation
   * performed, such as updating the display, handling errors, and managing the state of the
   * calculator.
   *
   * @param buttonText
   *          The text of the button that was pressed.
   */
  private void processButton(final String buttonText)
  {

    switch (buttonText)
    {
      case PM:
        CalculatorOperations.toggleSign(currentOperand);
        break;
      case CLEAR:
        currentOperand.setText(ZERO);
        enableOperatorButtons(true); // Enable operator buttons after calculation
        break;
      case EXP:
        String numberIn = JOptionPane.showInputDialog(this, ("EnterNumber")+C);
        String exponentIn = JOptionPane.showInputDialog(this, ("EnterExponent")+C);

        try
        {
          double num = Double.parseDouble(numberIn);
          double exponent = Double.parseDouble(exponentIn);

          double res = Math.pow(num, exponent);

          display.setText(num + "^" + exponent + E_W_SPACE + result);
          currentOperand.setText(String.valueOf(res));
        }
        catch (NumberFormatException e)
        {
          e.printStackTrace();
        }
        break;
      case RESET:
        currentOperand.setText(ZERO);
        display.setText("");
        left = null;
        right = null;
        result = null;// Reset the stored first operand
        selectedOperator = ""; // Clear any selected operator
        operationSelected = false; // Indicate no operation is currently selected
        unitsDropdown.setSelectedIndex(0); // reset the units dropdown to its default state
        // Reset Previous Variables
        previousResult = null;
        enableOperatorButtons(true);
        break;
      case BACKSPACE: // Assuming '<' is the backspace button
        CalculatorOperations.backspace(currentOperand);
        break;
      case EQUALS:
        // Evaluate expression
        if (operationSelected && CalculatorOperations.numberCheck(currentOperand, this))
        {
          double val = 0;
          try
          {
            val = Double.parseDouble(currentOperand.getText());
            
          } catch(NumberFormatException e)
          {
            JOptionPane.showMessageDialog(currentOperand.getParent(), 
                ERRORM,ERROR, JOptionPane.ERROR_MESSAGE);
            currentOperand.setText("");
            break;
          }
          String s = unitsDropdown.getSelectedItem().toString(); // Get the unit for the second
                                                                 // operand

          String text = currentOperand.getText();
          Operand rig = new LeftOperand(val, new FullUnit(UnitManager.getUnitBySymbol(s), 1),
              text.startsWith(MINUS));
          right = rig;

          if (!unitless)
          {
            right = UnitConvert.convertUnit(left, rig);
          }

          Expression exp = new Expression(left, right, op);

          Operand result1 = Evaluate.evaluate(exp);

          if (result1 == null)
          {
            JOptionPane.showMessageDialog(currentOperand.getParent(), "Unsupported Operation",
                ERROR, JOptionPane.ERROR_MESSAGE);
            break;
          }

          try
          {
            history.add(exp, (Result) result1); // Add calculation to history
            history.writeFile(new File(history.getFile().getAbsolutePath())); // Write history to
                                                                              // file
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }

          previousResult = (Result) result1; // Store result for future use
          this.result = result1;

          // Format the result before displaying
          String formattedResult = formatResult(result1.getValue());

          String seperatedResult = NumberFormat.thouSeperator(formattedResult, 
              preferences.isCommaSep(),usePeriodSeperation);

          String fullExpression = exp.toString() + E_W_SPACE + seperatedResult + SPACE
              + result1.getUnit().toString();

          unitless = true;

          display.setText(fullExpression); // Show the full expression in the display
          currentOperand.setText(formattedResult); // Update the currentOperand to reflect the
                                                   // result
          historyTextArea.setText(history.toString()); // Update the history text area
          operationSelected = false; // Reset the operation selection state if you want to start a
                                     // new calculation
        }
        
        left = null;
        right = null;
        result = null;// Reset the stored first operand
        selectedOperator = ""; // Clear any selected operator
        operationSelected = false; // Indicate no operation is currently selected// reset the units dropdown to its default state
        // Reset Previous Variables
        previousResult = null;
        enableOperatorButtons(true); // Enable operator buttons after calculation
        break;
      case PLUS:
        op = Operation.ADD;
        selectOperation(buttonText);
        // Update display to show the current operand and selected operator
        if (!operationSelected)
        {
          display.setText(currentOperand.getText() + SPACE + buttonText);
        }

        break;
      case MINUS:
        op = Operation.SUBTRACT;
        selectOperation(buttonText);
        // Update display to show the current operand and selected operator

        break;
      case MULT:
        op = Operation.MULTIPLY;
        selectOperation(buttonText);
        // Update display to show the current operand and selected operator

        break;
      case DIV:
        op = Operation.DIVIDE;
        selectOperation(buttonText);
        // Update display to show the current operand and selected operator

        break;
      case ARROW:
        toggleHistoryDialog();
        break;
      default:
        if (previousResult != null)
        {
          currentOperand.setText(ZERO); // If number is entered after the previous calc., the
                                        // running calc. ends
        }
        CalculatorOperations.appendToOperand(buttonText, currentOperand, unitsDropdown,
            operationSelected, selectedOperator, display);
        break;
    }
  }

  /**
   * Selects a mathematical operation for the calculator based on the operator button pressed. This
   * method sets up the calculator for receiving the next operand by managing states and updating
   * the display. It handles the transition from the completion of one calculation to the start of
   * another, or initiates a new operation.
   *
   * @param operator
   *          The mathematical operator selected by the user.
   */
  private void selectOperation(final String operator)
  {
    if (this.result != null && currentOperand.getText().equals(previousResult.toString()))
    {

      left = this.result;
      display.setText(left.toString() + SPACE + operator + SPACE);
      currentOperand.setText("");
      selectedOperator = operator;
      operationSelected = true;
      // Prepare for the next operand
      enableOperatorButtons(false);
      return;
    }

    if (!operationSelected)
    {
      double val = 0;

      if (CalculatorOperations.numberCheck(currentOperand, this))
      { // Check for both number and unit before proceeding
        try
        {
          val = Double.parseDouble(currentOperand.getText());
          
        } catch(NumberFormatException e)
        {
          JOptionPane.showMessageDialog(currentOperand.getParent(), 
              ERRORM,ERROR, JOptionPane.ERROR_MESSAGE);
          currentOperand.setText("");
          return;
        }
        String s = unitsDropdown.getSelectedItem().toString(); // Store the unit of the
                                                               // first operand
        unitless = false;
        if (s.equals(""))
        {
          unitless = true;
        }

        String text = currentOperand.getText();
        Operand lef = new LeftOperand(val, new FullUnit(UnitManager.getUnitBySymbol(s), 1),
            text.startsWith(MINUS));
        selectedOperator = operator;
        operationSelected = true;
        left = lef;
        display.setText(left.toString() + SPACE + operator + SPACE);
        currentOperand.setText(""); // Prepare for the next operand
        enableOperatorButtons(false); // Disable operator buttons as we are now expecting a
                                      // right-side operand
      }
    }
  }

  /**
   * Toggles the visibility of the history dialog. This method either shows or hides the history
   * dialog based on its current state. The history dialog typically contains a list or log of all
   * past calculations, allowing the user to review previous inputs and results.
   */
  private void toggleHistoryDialog()
  {
    if (popOutVisible)
    {
      historyDialog.setVisible(false);
    }
    else
    {
      // Set the dialog to the right side of the main window
      int x = this.getLocationOnScreen().x + this.getWidth();
      int y = this.getLocationOnScreen().y;
      historyDialog.setLocation(x, y);
      historyDialog.setVisible(true);
    }
    popOutVisible = !popOutVisible; // Toggle the flag
  }

  /**
   * Enables or disables the operator buttons on the calculator interface. This method is typically
   * called to prevent users from pressing operator buttons when it's not appropriate in the
   * sequence of operations (e.g., after entering a number, while waiting for the next operand, or
   * after an operation has been completed).
   *
   * @param enable
   *          A boolean flag indicating whether the operator buttons should be enabled (true) or
   *          disabled (false).
   */
  private void enableOperatorButtons(final boolean enable)
  {
    for (JButton button : operatorButtons)
    {
      button.setEnabled(enable);
    }
  }

  /**
   * Formats a numeric result according to the user's settings for decimal precision. The method
   * checks the format specified by the user (fixed or maximum decimal places) and applies it to
   * format the result. It also handles cases where the number of decimal places is set incorrectly
   * by falling back to the default string representation of the number.
   *
   * @param res
   *          The result to be formatted, typically a floating-point number.
   * @return A formatted string representation of the result based on user-defined decimal settings.
   */
  private String formatResult(final double res)
  {
    String form = "%.";
    int digits = 0;
    digits = preferences.getDigits();
    

    String format;
    if (preferences.isFixed())
    {
      // Fixed decimal places
      format = form + digits + F;
    }
    else
    {
      // Maximum decimal places
      format = form + digits + "g";
    }

    String resultStr = String.format(Locale.US, format, res);

    if (preferences.isMaximum())
    {
      resultStr = new BigDecimal(resultStr).stripTrailingZeros().toPlainString();
    }

    return resultStr;
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    char keyPressed = e.getKeyChar();
    switch (keyPressed)
    {
      case '+':
        processButton(PLUS);
        break;
      case '-':
        processButton(MINUS);
        break;
      case '*':
        processButton(MULT);
        break;
      case '/':
        processButton(DIV);
        break;
      case 'C':
        processButton(CLEAR);
        break;
      case 'R':
        processButton(RESET);
        break;
      case '.':
        processButton(DECIMAL);
        break;
      case '\b': // Backspace key
        processButton(BACKSPACE);
        break;
      case '=':
        processButton(EQUALS);
        break;
      case 0x23CE:
        processButton(EQUALS);
        break;
      default:
        // Check if the pressed key is a digit
        if (Character.isDigit(keyPressed))
        {
          processButton(String.valueOf(keyPressed));
        }
        break;
    }
  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    // TODO Auto-generated method blank.
  }
}
