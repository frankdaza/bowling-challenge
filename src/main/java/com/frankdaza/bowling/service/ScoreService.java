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

import com.frankdaza.bowling.domain.Player;

/**
 * @author Frank Edward Daza González
 * @date 2019-08-02
 */
public class ScoreService {
	
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
		
		System.out.println(playersLinkedHashMap);
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
			return 0;
		} else {
			return Integer.parseInt(lineScoreOld);
		}
	}
	
	

}
