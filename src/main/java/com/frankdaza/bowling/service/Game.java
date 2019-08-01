/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
package com.frankdaza.bowling.service;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
public interface Game {
	
	/**
	 * Start the game.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 */
	public void startGame();
	
	/**
	 * Load players scores file.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 */
	public void loadFile();
	
	/**
	 * Ends the game.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 */
	public void endGame();
	
	/**
	 * Bad file format.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 */
	public void badFileFormat();

}
