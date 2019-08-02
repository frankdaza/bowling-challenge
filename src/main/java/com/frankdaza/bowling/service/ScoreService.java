/**
 * @author Frank Edward Daza González
 * @date 2019-08-02
 */
package com.frankdaza.bowling.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.frankdaza.bowling.domain.Frame;
import com.frankdaza.bowling.domain.Player;
import com.frankdaza.bowling.domain.Score;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-02
 */
public class ScoreService {
	
	private static final Logger log = Logger.getLogger(ScoreService.class);
	
	
	private LinkedHashMap<String, List<Integer>> playersLinkedHashMap = new LinkedHashMap<>();
	
	
	/**
	 * Convert File lines to players scores.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param lines
	 */
	public void convertFileLinesToScores(List<String> lines) {
		for (String line : lines) {
			String[] lineArray = line.split("\t");
			String name = WordUtils.capitalize(lineArray[0].trim());
			
			Integer score = getScoreFromString(lineArray[1]);
			
			
			if (this.playersLinkedHashMap.get(name) != null) {
				List<Integer> actualScore = this.playersLinkedHashMap.get(name);
				actualScore.add(score);
				
				this.playersLinkedHashMap.put(name, actualScore);
			} else {
				List<Integer> actualScore = new ArrayList<Integer>();
				actualScore.add(score);
				this.playersLinkedHashMap.put(name, actualScore);
			}
			
		}
		
		log.info(playersLinkedHashMap);
		
		createScoreForPlayer();
	}
	
	/**
	 * Auxiliar method that get the score from a line file score.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param lineScore
	 * @return Integer Return player score.
	 */
	private Integer getScoreFromString(String lineScore) {
		String lineScoreOld = lineScore.trim().toUpperCase();
		
		if (lineScoreOld.equals("F")) {
			return -2;
		} else {
			return Integer.parseInt(lineScoreOld);
		}
	}
	
	/**
	 * Create an Score for each player in the playersLinkedHashMap.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 */
	private void createScoreForPlayer() {
		List<Player> players = new ArrayList<>();
		
		for (Entry<String, List<Integer>> map : this.playersLinkedHashMap.entrySet()) {
			Player player = getPlayerFromMapScores(map);
			players.add(player);
		}
		
		System.out.println(players);
	}
	
	
	/**
	 * Calculates the frames of a score from a player and,
	 * return a player with its scores.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param map
	 * @return Player
	 */
	private Player getPlayerFromMapScores(Entry<String, List<Integer>> map) {
		Player player = new Player();
		player.setName(map.getKey());
		List<Integer> playerScores = map.getValue();
		
		Frame frame1 = new Frame();
		Frame frame2 = new Frame();
		Frame frame3 = new Frame();
		Frame frame4 = new Frame();
		Frame frame5 = new Frame();
		Frame frame6 = new Frame();
		Frame frame7 = new Frame();
		Frame frame8 = new Frame();
		Frame frame9 = new Frame();
		Frame frame10 = new Frame();
		
		LinkedHashMap<Integer, Frame> frameLinkedHashMap = new LinkedHashMap<Integer, Frame>();
		frameLinkedHashMap.put(1, frame1);
		frameLinkedHashMap.put(2, frame2);
		frameLinkedHashMap.put(3, frame3);
		frameLinkedHashMap.put(4, frame4);
		frameLinkedHashMap.put(5, frame5);
		frameLinkedHashMap.put(6, frame6);
		frameLinkedHashMap.put(7, frame7);
		frameLinkedHashMap.put(8, frame8);
		frameLinkedHashMap.put(9, frame9);
		frameLinkedHashMap.put(10, frame10);
		
		
		for (int i = 1; i <= 10; i++) {
			Frame actualFrame = frameLinkedHashMap.get(i);
			List<Integer> valuesToRemove = new ArrayList<>(); 
			
			for (Integer point : playerScores) {
				if (i != 10) {
					if (actualFrame.getPoint1() == null) {
						actualFrame.setPoint1(point);
						valuesToRemove.add(point);
						
						if (point.equals(10)) {
							break;
						}
						
						continue;
					}
					
					if (actualFrame.getPoint2() == null) {
						actualFrame.setPoint2(point);
						valuesToRemove.add(point);
						break;
					}									
					
				} else {
					if (actualFrame.getPoint1() == null) {
						actualFrame.setPoint1(point);
						valuesToRemove.add(point);												
						
						continue;
					}
					
					if (actualFrame.getPoint2() == null) {
						actualFrame.setPoint2(point);
						valuesToRemove.add(point);
						
						continue;
					}
					
					if (actualFrame.getPoint3() == null) {
						actualFrame.setPoint3(point);
						valuesToRemove.add(point);
					}
				}
			}
			
			for (Integer point : valuesToRemove) {
				playerScores.remove(point);
			}
			
			
		}
		
		log.info(frameLinkedHashMap);
		
		Score score = new Score(
				frame1, frame2, frame3, frame4, frame5, 
				frame6, frame7, frame8, frame9, frame10);
		
		player.setScore(score);
		
		return player;
	}

}
