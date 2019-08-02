/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
package com.frankdaza.bowling.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.frankdaza.bowling.utilities.Constant;
import com.frankdaza.bowling.utilities.StringUtils;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
public class GameServiceImpl implements GameService {
	
	private static final Logger log = Logger.getLogger(GameServiceImpl.class);
	private List<String> fileLines;
	private ScoreService score = new ScoreService();
	
	/**
	 * Prints a message on console.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 * @param message
	 */
	private void consoleMessage(String message) {
		System.out.println("********************************************");
		System.out.println("*		" + message + "		  *");
		System.out.println("********************************************");
	}
	

	/**
	 * @see com.frankdaza.bowling.service.GameService#loadFile()
	 */
	@Override
	public void loadFile() {
		try (Stream<String> stream = Files.lines(Paths.get(Constant.FILE_GAME_PATH))) {

			this.fileLines = stream
					.collect(Collectors.toList());
			
			for (String line : fileLines) {
				log.debug(line);
				
				if (!StringUtils.isFileLineValid(line)) {
					badFileFormat();
					break;
				}
			}
			
			stream.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * @see com.frankdaza.bowling.service.GameService#startGame()
	 */
	public void startGame() {
		consoleMessage("BOWLING GAME STARTED");
		loadFile();
		this.score.convertFileLinesToScores(this.fileLines);
		endGame();
	}

	/**
	 * @see com.frankdaza.bowling.service.GameService#endGame()
	 */
	@Override
	public void endGame() {
		consoleMessage("BOWLING GAME ENDED");
	}


	/**
	 * @see com.frankdaza.bowling.service.GameService#badFileFormat()
	 */
	@Override
	public void badFileFormat() {
		consoleMessage("BAD FILE FORMAT");
	}

}
