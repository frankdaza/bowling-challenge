/**
 * @author Frank Edward Daza González
 * @date 2019-08-02
 */
package com.frankdaza.bowling.service;

import java.util.List;

import com.frankdaza.bowling.domain.Frame;
import com.frankdaza.bowling.domain.Player;
import com.frankdaza.bowling.domain.Score;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-02
 */
public class BoardService {
	
	
	/**
	 * Print a game board of players, with their scores.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param playersScores
	 */
	public void printGameBoard(List<Player> playersScores) {
		for (Player player : playersScores) {
			printPlayerScore(player);
		}
	}
	
	/**
	 * Print a board score for a player.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param player
	 */
	private void printPlayerScore(Player player) {
		Score score = player.getScore();
		
		System.out.printf("Frame    %d     %d     %d     %d     %d     %d     %d     %d     %d        %d \n",
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println(player.getName());
		
		printFallsPlayer(score);
		
		System.out.printf("Score    %s   %s   %s   %s   %s   %s   %s   %s   %s      %s \n\n",
				getCorrectSpacesForNumber(score.getFrame1().getTotal()),
				getCorrectSpacesForNumber(score.getFrame2().getTotal()),
				getCorrectSpacesForNumber(score.getFrame3().getTotal()),
				getCorrectSpacesForNumber(score.getFrame4().getTotal()),
				getCorrectSpacesForNumber(score.getFrame5().getTotal()),
				getCorrectSpacesForNumber(score.getFrame6().getTotal()),
				getCorrectSpacesForNumber(score.getFrame7().getTotal()),
				getCorrectSpacesForNumber(score.getFrame8().getTotal()),
				getCorrectSpacesForNumber(score.getFrame9().getTotal()),
				getCorrectSpacesForNumber(score.getFrame10().getTotal()));
	}
	
	/**
	 * Return a string formated with 'correct' spaces.
	 * This is an auxiliar method, that helps with the correct
	 * output format.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param totalFrame
	 * @return String
	 */
	private String getCorrectSpacesForNumber(Integer totalFrame) {
		if (totalFrame < 10) {
			return totalFrame + "  ";
		}
		
		if (totalFrame >= 10 && totalFrame < 100) {
			return totalFrame + " ";
		}
		
		return totalFrame + "";
	}
	
	/**
	 * Print the pinfalls from a player.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param score
	 */
	private void printFallsPlayer(Score score) {
		String frame1 = getValueOrSymbolOfPinFalls(score.getFrame1());
		String frame2 = getValueOrSymbolOfPinFalls(score.getFrame2());
		String frame3 = getValueOrSymbolOfPinFalls(score.getFrame3());
		String frame4 = getValueOrSymbolOfPinFalls(score.getFrame4());
		String frame5 = getValueOrSymbolOfPinFalls(score.getFrame5());
		String frame6 = getValueOrSymbolOfPinFalls(score.getFrame6());
		String frame7 = getValueOrSymbolOfPinFalls(score.getFrame7());
		String frame8 = getValueOrSymbolOfPinFalls(score.getFrame8());
		String frame9 = getValueOrSymbolOfPinFalls(score.getFrame9());
		String frame10 = getValueOrSymbolOfPinFalls(score.getFrame10());
		
		System.out.printf("PinFalls %s     %s     %s     %s     %s     %s     %s     %s     %s        %s \n",
				frame1, 
				frame2, 
				frame3, 
				frame4, 
				frame5, 
				frame6, 
				frame7, 
				frame8, 
				frame9, 
				frame10);
	}
	
	
	/**
	 * Return the correct value or symbol from a point of each frame.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param frame
	 * @return String
	 */
	private String getValueOrSymbolOfPinFalls(Frame frame) {	
		if (frame.getPoint3() == null) {
			if (frame.getPoint2() == null) {						
				return getCorrectSpacesForNumber(frame.getPoint1()).replace("10", "X ").replace("-2", "F");
			}
			
			Integer totalSpare = frame.getPoint1() + frame.getPoint2();
			
			// SPARE
			if (totalSpare.equals(10)) {
				return (getCorrectSpacesForNumber(frame.getPoint1()) + "/  ").replace("10", "X ").replace("-2", "F");
			}
			
			return (getCorrectSpacesForNumber(frame.getPoint1()) + 
					getCorrectSpacesForNumber(frame.getPoint2())).replace("10", "X ").replace("-2", "F");		
		} else {
			if (frame.getPoint3() == null) {							
				Integer totalSpare = frame.getPoint1() + frame.getPoint2();
				
				// SPARE
				if (totalSpare.equals(10)) {
					return (getCorrectSpacesForNumber(frame.getPoint1()) + "/  ").replace("10", "X ").replace("-2", "F");
				}
			} else {
				Integer totalSpare = frame.getPoint2() + frame.getPoint3();
				
				// SPARE
				if (totalSpare.equals(10)) {
					return (getCorrectSpacesForNumber(frame.getPoint2()) + "/  ").replace("10", "X ").replace("-2", "F");
				}
			}
			
			return (getCorrectSpacesForNumber(frame.getPoint1()) + 
					getCorrectSpacesForNumber(frame.getPoint2()) + 
					getCorrectSpacesForNumber(frame.getPoint3())).replace("10", "X ").replace("-2", "F");
		}
	}
	
	
	/**
	 * Return a string formated with 'correct' spaces.
	 * This is an auxiliar method, that helps with the correct
	 * output format.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param string
	 * @return String
	 */
	private String getCorrectSpacesForString(String string) {
		String[] resultArray = string.trim().split(" ");
		String result = "";
		
		for (int i = 0; i < resultArray.length; i++) {
			int characters = resultArray[i].length();
			
			if (characters < 10) {
				result += resultArray[i] + "  ";
			}
			
			if (characters >= 10 && characters < 100) {
				result += resultArray[i] + " ";
			}
			
			result += resultArray[i];
		}
		
		return result;
	}
	


}
