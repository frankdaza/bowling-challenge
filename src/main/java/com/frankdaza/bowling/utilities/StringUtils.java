/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
package com.frankdaza.bowling.utilities;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
public class StringUtils {
	
	public static final Logger log = Logger.getLogger(StringUtils.class);
	
	/**
	 * Validates if a string has only two values, separates by a blank space.
	 * The first value should be a string and the second value, should be
	 * an integer between 0 - 10 inclusive or, a 'F' letter. 
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 * @param line
	 * @return Boolean Return true if the string is valid, otherwise return false.
	 */
	public static final Boolean isFileLineValid(String line) {
		if (line != null && !line.trim().isEmpty()) {
			String[] values = WordUtils.capitalizeFully(line.trim()).split("\t");
			
			if (values.length == 2) {
				if (values[1].equals("F")) {
					return true;
				}
				
				try {
					Integer point = Integer.parseInt(values[1]);
					
					if (point >= 0 && point <= 10)
						return true;
				} catch(Exception e) {
					log.error(e.getMessage());
					return false;
				}
				
			}
			
		}
		
		return false;
	}

}
