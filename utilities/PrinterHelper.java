package utilities;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * Helper class that prints the component it is passed.
 * 
 * @author S24Team2D
 * @version 1
 */
public class PrinterHelper implements Printable
{

  /**
   * Static method used to print the Component.
   * 
   * @param comp
   *          Component to be printed
   */
  public static void printComponent(final Component comp)
  {
    PrinterJob printerJob = PrinterJob.getPrinterJob();
    printerJob.setJobName("Print Session");

    printerJob.setPrintable(new Printable()
    {
      public int print(final Graphics pg, final PageFormat pf, final int pageNum)
      {
        if (pageNum > 0)
        {
          return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) pg;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        comp.printAll(g2);
        return Printable.PAGE_EXISTS;
      }
    });

    if (printerJob.printDialog())
    {
      try
      {
        printerJob.print();
      }
      catch (PrinterException ex)
      {
      }
    }
  }

  @Override
  public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
      throws PrinterException
  {
    // TODO Auto-generated method stub
    return 0;
  }
}
