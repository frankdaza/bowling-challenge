/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
package com.frankdaza.bowling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.frankdaza.bowling.utilities.Constant;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
public class FileTest {
	
	private static final Logger log = Logger.getLogger(FileTest.class);
	
	/**
	 * Test functionality of reading a file.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 */
	@Test
	public void readFileTest() {
		try (Stream<String> stream = Files.lines(Paths.get(Constant.FILE_TEST_GAME_PATH))) {

			stream.forEach(System.out::println);
			stream.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		
	}

}
