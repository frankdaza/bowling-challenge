/**
 * @author Frank Edward Daza González
 * @date 2019-08-02
 */
package com.frankdaza.bowling.service;

import java.util.ArrayList;
import java.util.Arrays;
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
		
		log.debug(playersLinkedHashMap);
		
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
		
		List<Player> newPlayersScores = calculatePlayersScores(players);
		
//		for (Player player : newPlayersScores) {
//			Score score = player.getScore();
//			
//			System.out.println(player.getName());
//			System.out.println("Frame 1: " + score.getFrame1().getTotal());
//			System.out.println("Frame 2: " + score.getFrame2().getTotal());
//			System.out.println("Frame 3: " + score.getFrame3().getTotal());
//			System.out.println("Frame 4: " + score.getFrame4().getTotal());
//			System.out.println("Frame 5: " + score.getFrame5().getTotal());
//			System.out.println("Frame 6: " + score.getFrame6().getTotal());
//			System.out.println("Frame 7: " + score.getFrame7().getTotal());
//			System.out.println("Frame 8: " + score.getFrame8().getTotal());
//			System.out.println("Frame 9: " + score.getFrame9().getTotal());
//			System.out.println("Frame 10: " + score.getFrame10().getTotal());
//		}
		
		// TODO: Create function that validates player's scores.
		
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
		
		log.debug(frameLinkedHashMap);
		
		Score score = new Score(
				frame1, frame2, frame3, frame4, frame5, 
				frame6, frame7, frame8, frame9, frame10);
		
		player.setScore(score);
		
		return player;
	}
	
	/**
	 * Calculates players scores.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param players
	 * @return List<Player>
	 */
	private List<Player> calculatePlayersScores(List<Player> players) {
		List<Player> newPlayers = new ArrayList<>();
		
		for (Player player : players) {
			Score score = player.getScore();
			
			List<Frame> frames = Arrays.asList(
					score.getFrame1(), score.getFrame2(), score.getFrame3(), 
					score.getFrame4(), score.getFrame5(), score.getFrame6(), 
					score.getFrame7(), score.getFrame8(), score.getFrame9(), 
					score.getFrame10());
			
			List<Integer> totalPoints = calculatesTotalFrame(frames);
			
			score.getFrame1().setTotal(totalPoints.get(0));
			score.getFrame2().setTotal(totalPoints.get(1));
			score.getFrame3().setTotal(totalPoints.get(2));
			score.getFrame4().setTotal(totalPoints.get(3));
			score.getFrame5().setTotal(totalPoints.get(4));
			score.getFrame6().setTotal(totalPoints.get(5));
			score.getFrame7().setTotal(totalPoints.get(6));
			score.getFrame8().setTotal(totalPoints.get(7));
			score.getFrame9().setTotal(totalPoints.get(8));
			score.getFrame10().setTotal(totalPoints.get(9));
			
			newPlayers.add(player);
		}
		
		return newPlayers;
	}
	
	/**
	 * Calculates total points of each frame from a player.
	 * 
	 * @author Frank Edward Daza González
	 * @date 2019-08-02
	 * @param frames
	 * @return List<Integer> 
	 */
	private List<Integer> calculatesTotalFrame(List<Frame> frames) {
		List<Integer> totalPoints = new ArrayList<>();		
		List<Frame> newListOfFrames = new ArrayList<>(frames);
		int lastFrameTotal = 0;
		
		for (int i = 0; i < 10; i++) {
			Integer totalPointsFrame = 0;
			
			for (Frame frame : newListOfFrames) {
				if (i < 8) {
					
					// STRIKE
					
					if (frame.getPoint1().equals(10)) {
						totalPointsFrame += 10;
						
						if (newListOfFrames.get(1).getPoint1() != null) {
							Integer point = newListOfFrames.get(1).getPoint1().equals(-2)
									? 0 : newListOfFrames.get(1).getPoint1();
							
							totalPointsFrame += point;
							
							if (newListOfFrames.get(1).getPoint2() != null) {
								Integer point2 = newListOfFrames.get(1).getPoint2().equals(-2)
										? 0 : newListOfFrames.get(1).getPoint2();
								
								totalPointsFrame += point2;
								break;
							} else {
								Integer point2 = newListOfFrames.get(2).getPoint1().equals(-2)
										? 0 : newListOfFrames.get(2).getPoint1();
								
								totalPointsFrame += point2;
								break;
							}
						}
						
					}
					
					if (frame.getPoint1() != null && frame.getPoint2() != null) {
						Integer point1 = frame.getPoint1().equals(-2)
								? 0 : frame.getPoint1();
						
						Integer point2 = frame.getPoint2().equals(-2)
								? 0 : frame.getPoint2();
						
						Integer total = point1 + point2;
						
						// SPARE
						
						if (total.equals(10)) {
							totalPointsFrame += 10;
							
							Integer pointSpare = newListOfFrames.get(1).getPoint1().equals(-2)
									? 0 : newListOfFrames.get(1).getPoint1();
							
							totalPointsFrame += pointSpare;
							break;
						} else {
							totalPointsFrame += total;
							break;
						}
					}
					
				} else {
					if (i == 8) {
						
						// STRIKE
						
						if (frame.getPoint1().equals(10)) {
							totalPointsFrame += 10;
							
							if (newListOfFrames.get(1).getPoint1() != null) {
								Integer point = newListOfFrames.get(1).getPoint1().equals(-2)
										? 0 : newListOfFrames.get(1).getPoint1();
								
								totalPointsFrame += point;
								
								if (newListOfFrames.get(1).getPoint2() != null) {
									Integer point2 = newListOfFrames.get(1).getPoint2().equals(-2)
											? 0 : newListOfFrames.get(1).getPoint2();
									
									totalPointsFrame += point2;
									break;
								} 
							}
							
						}
						
						if (frame.getPoint1() != null && frame.getPoint2() != null) {
							Integer point1 = frame.getPoint1().equals(-2)
									? 0 : frame.getPoint1();
							
							Integer point2 = frame.getPoint2().equals(-2)
									? 0 : frame.getPoint2();
							
							Integer total = point1 + point2;
							
							// SPARE
							
							if (total.equals(10)) {
								totalPointsFrame += 10;
								
								Integer pointSpare = newListOfFrames.get(1).getPoint1().equals(-2)
										? 0 : newListOfFrames.get(1).getPoint1();
								
								totalPointsFrame += pointSpare;
								break;
							} else {
								totalPointsFrame += total;
								break;
							}
						}
						
					}
					
					if (i == 9) {
						
						// STRIKE
						
						if (frame.getPoint1().equals(10)) {
							totalPointsFrame += 10;
							
							if (frame.getPoint2() != null) {
								Integer point = frame.getPoint2().equals(-2)
										? 0 : frame.getPoint2();
								
								totalPointsFrame += point;
								
								if (frame.getPoint3() != null) {
									Integer point3 = frame.getPoint3().equals(-2)
											? 0 : frame.getPoint3();
									
									totalPointsFrame += point3;
									break;
								} 
							}
						}
						
						if (frame.getPoint1() != null && frame.getPoint2() != null) {
							Integer point1 = frame.getPoint1().equals(-2)
									? 0 : frame.getPoint1();
							
							Integer point2 = frame.getPoint2().equals(-2)
									? 0 : frame.getPoint2();
							
							Integer total = point1 + point2;
							
							// SPARE
							
							if (total.equals(10)) {
								totalPointsFrame += 10;
								
								Integer pointSpare = frame.getPoint3().equals(-2)
										? 0 : frame.getPoint3();
								
								totalPointsFrame += pointSpare;
								break;
							} else {
								totalPointsFrame += total;
								break;
							}
						}
						
					}
					
				}									
			}
			
			int tmpTotalPointsFrame = totalPointsFrame;
			totalPointsFrame += lastFrameTotal;
			totalPoints.add(totalPointsFrame);
			newListOfFrames.remove(0);
			lastFrameTotal += tmpTotalPointsFrame;
		}
			
		log.debug(totalPoints);
		
		return totalPoints;
	}

	
}
