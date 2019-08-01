/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
package com.frankdaza.bowling.domain;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-01
 */
public class Player {
	
	private String name;
	private Score score;
	
	/**
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 */
	public Player() {
	}
	
	/**
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 * @param name
	 * @param score
	 */
	public Player(String name, Score score) {
		this.name = name;
		this.score = score;
	}

	/**
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 * @return the score
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * @author Frank Edward Daza González
	 * @date 2019-08-01
	 * @param score the score to set
	 */
	public void setScore(Score score) {
		this.score = score;
	}

}
