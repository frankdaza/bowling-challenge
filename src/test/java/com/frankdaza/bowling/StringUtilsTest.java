/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
package com.frankdaza.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.frankdaza.bowling.utilities.StringUtils;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
public class StringUtilsTest {
	
	/**
	 * Test functionality of isFileLineValid when return true;
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 */
	@Test
	public void isFileLineValidGoodResultTest() {
		String line = "frank	10";
		
		Boolean result = StringUtils.isFileLineValid(line);
		Assertions.assertEquals(true, result);
	}
	
	/**
	 * Test functionality of isFileLineValid when return false;
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 */
	@Test
	public void isFileLineValidBadResultTest() {
		String line = "frank	11";
		
		Boolean result = StringUtils.isFileLineValid(line);
		Assertions.assertEquals(false, result);
	}

}
