package com.soccer.ranking.process;

import java.util.ArrayList;
import java.util.List;
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
	private List<Team> playedTeams = new ArrayList<Team>(); // 
	private List<Integer> scores = new ArrayList<Integer>(); // list of goals scored 
	/**
	 * processResultPerLine
	 * 
	 * @param line e.g Lions 1, FC Awesome 1
	 */
	public void processResultPerLine(String line) {
		for (String splitString : line.trim().split(",")) { // split the string by commas separated by commas
			for (String sps : splitString.trim().split("^([A-Za-z]|[0-9])+$")) {
				String teamName = sps.replaceAll("[^a-zA-Z\\s]", "");// get name of team in string like Lions
				Team team = new Team(teamName.trim()); // creating an object with the team name
				playedTeams.add(team);
			}
			Matcher matcher = Pattern.compile("[\\d+]\\s*$").matcher(splitString); // getting goals scored
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

	/** Method name recordDraw
	 * Record Draw Updating the recordDraw Methods to make it take in both teamA and teamB object 
	 * @param team
	 */
	public void recordDraw(Team teamA, Team teamB) {
		teamA.incrementPoints(TIE_POINTS);
		teamB.incrementPoints(TIE_POINTS);
	}

	/**
	 * getWinner
	 * 
	 * @param scoreForTeamA
	 * @param scoreForTeamB
	 * @return
	 */
	public Team getWinner(int scoreForTeamA, int scoreForTeamB) {
		if (scoreForTeamA > scoreForTeamB) {
			return getPlayedTeams().get(0);
		} else {
			return getPlayedTeams().get(1);
		}
	}

	/**
	 * recordPoints
	 */
	public void recordPoints() {
		int scoreForTeamA = this.scores.get(0);
		int scoreForTeamB = this.scores.get(1);
		if (scoreForTeamA == scoreForTeamB) {
			recordDraw(getPlayedTeams().get(0), getPlayedTeams().get(1));
		} else {
			recordWin(getWinner(scoreForTeamA, scoreForTeamB));// recode the winner team after 
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
