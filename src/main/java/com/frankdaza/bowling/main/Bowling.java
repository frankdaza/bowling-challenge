/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
package com.frankdaza.bowling.main;

import com.frankdaza.bowling.service.GameServiceImpl;

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
		GameServiceImpl gameImpl = new GameServiceImpl();
		gameImpl.startGame();
	}

}
