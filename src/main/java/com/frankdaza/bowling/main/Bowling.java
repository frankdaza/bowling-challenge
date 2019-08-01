/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
package com.frankdaza.bowling.main;

import com.frankdaza.bowling.service.GameImpl;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
public class Bowling {

	/**
	 * Main method that executes the Bowling game.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 * @param args
	 */
	public static void main(String[] args) {
		GameImpl gameImpl = new GameImpl();
		gameImpl.startGame();
	}

}
