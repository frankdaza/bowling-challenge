/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
package com.frankdaza.bowling.service;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
public class GameImpl implements Game {
	
	public void startGame() {
		System.out.println("********************************************");
		System.out.println("*			BOWLING GAME STARTED		   *");
		System.out.println("********************************************");
		
		loadFile();
	}

	/**
	 * @see com.frankdaza.bowling.service.Game#loadFile()
	 */
	@Override
	public void loadFile() {
		System.out.println("Players scores file loaded!");
	}

}
