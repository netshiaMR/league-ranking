package com.soccer.ranking.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.soccer.ranking.model.Team;

/**
 * Class FixtureProcessor provide api methods to process input data and extends
 * a Constant Class
 * 
 * @author Netshia Rendani
 * @since 18/11/2021 T21:00
 */
public class FixtureProcessor extends Constant {
	private List<Team> playedTeams = new ArrayList<Team>();
	private List<Integer> scores = new ArrayList<Integer>();
	/**
	 * processResultPerLine
	 * 
	 * @param line
	 */
	public void processResultPerLine(String line) {
		for (String splitString : line.trim().split(",")) {
			for (String sps : splitString.trim().split("^([A-Za-z]|[0-9])+$")) {
				String teamName = sps.replaceAll("[^a-zA-Z\\s]", "");
				Team team = new Team(teamName.trim());
				playedTeams.add(team);
			}
			Matcher matcher = Pattern.compile("[\\d+]\\s*$").matcher(splitString);
			while (matcher.find()) {
				scores.add(Integer.parseInt(matcher.group(0)));
			}
		}
	}

	/**
	 * recordWin
	 * 
	 * @param team
	 */
	public void recordWin(Team team) {
		team.incrementPoints(WIN_POINTS);
	}

	/**
	 * recordDraw
	 * 
	 * @param team
	 */
	public void recordDraw(Team team) {
		team.incrementPoints(TIE_POINTS);
	}

	/**
	 * getWinner
	 * 
	 * @param score1
	 * @param score2
	 * @return
	 */
	public Team getWinner(int score1, int score2) {
		if (score1 > score2) {
			return getPlayedTeams().get(0);
		} else {
			return getPlayedTeams().get(1);
		}
	}

	/**
	 * recordPoints
	 */
	public void recordPoints() {
		int scoreForTeamA = this.getScores().get(0);
		int scoreForTeamB = this.getScores().get(1);
		if (scoreForTeamA == scoreForTeamB) {
			recordDraw(getPlayedTeams().get(0));
			recordDraw(getPlayedTeams().get(1));
		} else {
			recordWin(this.getWinner(scoreForTeamA, scoreForTeamB));
		}
	}

	public List<Integer> getScores() {
		return scores;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	public List<Team> getPlayedTeams() {
		return playedTeams;
	}

	public void setPlayedTeams(List<Team> playedTeams) {
		this.playedTeams = playedTeams;
	}
}
