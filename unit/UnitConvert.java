package unit;

import java.util.HashMap;

import operation.*;

/**
 * UnitConvert class.
 * 
 * @author S24Team2d
 * @version 1
 */
public class UnitConvert
{
  // Constants for identifying conversion types
  private static final String T = "t";
  private static final String F = "f";

  // map containing conversion factors
  private static HashMap<String, Double> map = ConversionFactors.getFactors();

  /**
   * Converts units based on provided operands.
   * 
   * This method takes two operands, left and right, and converts the unit of the right operand to
   * match the unit of the left operand if possible.
   * 
   * @param left
   *          The left operand.
   * @param right
   *          The right operand.
   * @return The converted operand.
   */
  public static Operand convertUnit(final Operand left, final Operand right)
  {
    Operand converted = right;
    Unit meter = new Unit("m", 'l', '*');
    FullUnit f = new FullUnit(meter, 1);
    CompoundUnit c = new CompoundUnit(f, f, Operation.DIVIDE);
    DisplayUnit leftU = left.getUnit();
    DisplayUnit rightU = right.getUnit();

    if (leftU.getClass().equals(f.getClass()) && rightU.getClass().equals(f.getClass()))
    {
      FullUnit lf = (FullUnit) leftU;
      FullUnit rf = (FullUnit) rightU;

      if (lf.getUnit().getType() == rf.getUnit().getType()
          && lf.getUnit().getCode() != rf.getUnit().getCode())
      {

        if (lf.getUnit().getCode() == '*')
        {
          Unit u = rf.getUnit();
          String code = Character.toString(u.getCode());
          String type = Character.toString(u.getType());
          double factor = map.get(type + code + T);
          FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
          converted = new RightOperand(right.getValue() * factor, f1, right.getNegative());
        }
        else if (rf.getUnit().getCode() == '*')
        {
          Unit u = lf.getUnit();
          String code = Character.toString(u.getCode());
          String type = Character.toString(u.getType());
          double factor = map.get(type + code + F);
          FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
          converted = new RightOperand(right.getValue() * factor, f1, right.getNegative());
        }
        else
        {
          Unit u = rf.getUnit();
          String code = Character.toString(u.getCode());
          String type = Character.toString(u.getType());
          double factor = map.get(type + code + T);
          FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
          double temp = right.getValue() * factor;

          Unit u2 = lf.getUnit();
          String code2 = Character.toString(u2.getCode());
          String type2 = Character.toString(u2.getType());
          double factor2 = map.get(type2 + code2 + F);
          converted = new RightOperand(temp * factor2, f1, right.getNegative());
        }

      }
    }
    else if (leftU.getClass().equals(c.getClass()) && rightU.getClass().equals(f.getClass()))
    {
      CompoundUnit cl = (CompoundUnit) leftU;
      FullUnit lf = cl.getNumerator();
      FullUnit rf = (FullUnit) rightU;

      if (lf.getUnit().getCode() == '*')
      {
        Unit u = rf.getUnit();
        String code = Character.toString(u.getCode());
        String type = Character.toString(u.getType());
        double factor = map.get(type + code + T);
        FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
        converted = new RightOperand(right.getValue() * factor, f1, right.getNegative());
      }
      else if (rf.getUnit().getCode() == '*')
      {
        Unit u = lf.getUnit();
        String code = Character.toString(u.getCode());
        String type = Character.toString(u.getType());
        double factor = map.get(type + code + F);
        FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
        converted = new RightOperand(right.getValue() * factor, f1, right.getNegative());
      }
      else
      {
        Unit u = rf.getUnit();
        String code = Character.toString(u.getCode());
        String type = Character.toString(u.getType());
        double factor = map.get(type + code + T);
        FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
        double temp = right.getValue() * factor;

        Unit u2 = lf.getUnit();
        String code2 = Character.toString(u2.getCode());
        String type2 = Character.toString(u2.getType());
        double factor2 = map.get(type2 + code2 + F);
        converted = new RightOperand(temp * factor2, f1, right.getNegative());
      }

    }
    else if (leftU.getClass().equals(f.getClass()) && rightU.getClass().equals(c.getClass()))
    {
      CompoundUnit cr = (CompoundUnit) rightU;
      FullUnit rf = cr.getNumerator();
      FullUnit lf = (FullUnit) leftU;

      if (lf.getUnit().getCode() == '*')
      {
        Unit u = rf.getUnit();
        String code = Character.toString(u.getCode());
        String type = Character.toString(u.getType());
        double factor = map.get(type + code + T);
        FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
        CompoundUnit c1 = new CompoundUnit(f1, cr.getDenominator(), cr.getOp());
        converted = new RightOperand(right.getValue() * factor, c1, right.getNegative());
      }
      else if (rf.getUnit().getCode() == '*')
      {
        Unit u = lf.getUnit();
        String code = Character.toString(u.getCode());
        String type = Character.toString(u.getType());
        double factor = map.get(type + code + F);
        FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
        CompoundUnit c1 = new CompoundUnit(f1, cr.getDenominator(), cr.getOp());
        converted = new RightOperand(right.getValue() * factor, cr, right.getNegative());
      }
      else
      {
        Unit u = rf.getUnit();
        String code = Character.toString(u.getCode());
        String type = Character.toString(u.getType());
        double factor = map.get(type + code + T);
        FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
        CompoundUnit c1 = new CompoundUnit(f1, cr.getDenominator(), cr.getOp());
        double temp = right.getValue() * factor;

        Unit u2 = lf.getUnit();
        String code2 = Character.toString(u2.getCode());
        String type2 = Character.toString(u2.getType());
        double factor2 = map.get(type2 + code2 + F);
        converted = new RightOperand(temp * factor2, c1, right.getNegative());
      }

    }
    else if (leftU.getClass().equals(c.getClass()) && rightU.getClass().equals(c.getClass()))
    {
      CompoundUnit cr = (CompoundUnit) rightU;
      FullUnit rf = cr.getNumerator();
      CompoundUnit cl = (CompoundUnit) leftU;
      FullUnit lf = cl.getNumerator();

      if (lf.getUnit().getCode() == '*')
      {
        Unit u = rf.getUnit();
        String code = Character.toString(u.getCode());
        String type = Character.toString(u.getType());
        double factor = map.get(type + code + T);
        FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
        CompoundUnit c1 = new CompoundUnit(f1, cr.getDenominator(), cr.getOp());
        converted = new RightOperand(right.getValue() * factor, c1, right.getNegative());
      }
      else if (rf.getUnit().getCode() == '*')
      {
        Unit u = lf.getUnit();
        String code = Character.toString(u.getCode());
        String type = Character.toString(u.getType());
        double factor = map.get(type + code + F);
        FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
        CompoundUnit c1 = new CompoundUnit(f1, cr.getDenominator(), cr.getOp());
        converted = new RightOperand(right.getValue() * factor, cr, right.getNegative());
      }
      else
      {
        Unit u = rf.getUnit();
        String code = Character.toString(u.getCode());
        String type = Character.toString(u.getType());
        double factor = map.get(type + code + T);
        FullUnit f1 = new FullUnit(lf.getUnit(), rf.getPower());
        CompoundUnit c1 = new CompoundUnit(f1, cr.getDenominator(), cr.getOp());
        double temp = right.getValue() * factor;

        Unit u2 = lf.getUnit();
        String code2 = Character.toString(u2.getCode());
        String type2 = Character.toString(u2.getType());
        double factor2 = map.get(type2 + code2 + F);
        converted = new RightOperand(temp * factor2, c1, right.getNegative());
      }

    }

    return converted;
  }
}
