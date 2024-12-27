package unit;

/**
 * DisplayUnit Interface.
 * 
 * @author S24Team2D
 * @version 1
 */
public interface DisplayUnit 
{
  
  /**
   * 
   * @return test
   */
	public abstract String toString();
	
	/**
	 * 
	 * @param unit2
	 * @return test
	 */
	public abstract int equal(DisplayUnit unit2);
}
