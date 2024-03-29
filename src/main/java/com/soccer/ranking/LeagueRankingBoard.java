package com.soccer.ranking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soccer.ranking.model.Team;
import com.soccer.ranking.process.FixtureProcessor;

/**
 * LeagueRankingBoard
 * 
 * @author Netshia Rendani
 * @since 18/11/2021 T21:00
 */
public class LeagueRankingBoard {
	private List<Team> leagueRankingTable = null;
	public Map<String, Integer> matchPlayed = new HashMap<String, Integer>();

	/**
	 * LeagueRankingBoard
	 */
	public LeagueRankingBoard() {
		this.leagueRankingTable = new ArrayList<>();
	}

	/**
	 * parseFixtureResultsFile
	 * 
	 * @param file
	 */
	public void parseFixtureResultsFile(File file) {
		if (!file.exists()) {
			System.err.println("Error while getting File Name : " + file.getName());
			System.exit(0);
		} else {
			try {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(file), "UTF8"));
				String inputParline;
				System.out.println(
						"============================ Fixture Input ====================================================");
				while ((inputParline = bufferedReader.readLine()) != null) {
					System.out.println(inputParline);
					FixtureProcessor fixtureProcessor = new FixtureProcessor();
					fixtureProcessor.processResultPerLine(inputParline);
					fixtureProcessor.recordPoints(); //
					for (Team team : fixtureProcessor.getPlayedTeams()) {
						addTeam(team);
					}

					ArrayList<String> listofTeam = new ArrayList<String>();
					for (Team teams : fixtureProcessor.getPlayedTeams()) {
						listofTeam.add(teams.getTeamName());
					}
					for (String teams : listofTeam) {
						Integer j = matchPlayed.get(teams);
						matchPlayed.put(teams, (j == null) ? 1 : j + 1);
					}
				}

				System.out.println(
						"==============================================================================================");
				System.out.println();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		}
	}

	/**
	 * This Methods add the Team object to leagueRankingTable list and check whether
	 * the teams was already add to the list.
	 * 
	 * @param playedTeam
	 */
	public void addTeam(Team playedTeam) {
		for (Team team : leagueRankingTable) {
			if (team.getTeamName().equalsIgnoreCase(playedTeam.getTeamName())) {
				team.incrementPoints(playedTeam.getPoints());
				return;
			}
		}
		leagueRankingTable.add(playedTeam);
	}

	public void sortTeams() {
		Collections.sort(leagueRankingTable, Team.TeamComparator);
	}

	/**
	 * print LeagueRanking Board
	 */
	public void printLeagueRankingBoard() {
		sortTeams();
		String suffix = "";
		int index = 0;
		System.out.println(
				"============================League Table Results==============================================");
		for (Team team : leagueRankingTable) {
			String teamName = team.getTeamName();
			suffix = team.getPoints() <= 1 ? " pt" : " pts";
			System.out.println(index + 1 + ". " + teamName + "," + team.getPoints() + suffix);
			index++;
		}
		System.out.println(
				"==============================================================================================");

	}

	public void advancePrintLeagueRankingBoard() {
		sortTeams();
		String suffix = "";
		int index = 0;
		System.out.println(
				"============================League Table Results==============================================");
		System.out.printf("%s%9s%19s%8s\n", "Position", "Teams", "MP", "PTS"); 
		for (Team team : leagueRankingTable) {
			String teamName = team.getTeamName();
			int mp = 0;
			for (Map.Entry<String, Integer> val : matchPlayed.entrySet()) {
				if (val.getKey().equalsIgnoreCase(teamName)) {
					mp = val.getValue();
				}
			}
			System.out.printf( "%5d%19s%11d%9d\n", (index+1), teamName ,mp ,team.getPoints());
			index++;
		}
		System.out.println(
				"==============================================================================================");

	}

	public List<Team> getLeagueRankingTable() {
		return leagueRankingTable;
	}

	public void setLeagueRankingTable(List<Team> leagueRankingTable) {
		this.leagueRankingTable = leagueRankingTable;
	}

	public Map<String, Integer> getMatchPlayed() {
		return matchPlayed;
	}

	public void setMatchPlayed(Map<String, Integer> matchPlayed) {
		this.matchPlayed = matchPlayed;
	}

}
