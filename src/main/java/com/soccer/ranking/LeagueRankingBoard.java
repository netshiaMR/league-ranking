package com.soccer.ranking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.soccer.ranking.model.Team;
import com.soccer.ranking.process.FixtureProcessor;

/**
 * LeagueRankingBoard
 * @author Netshia Rendani
 * @since 18/11/2021 T21:00
 */
public class LeagueRankingBoard {
	private List<Team> leagueRankingTable = null;
	/**
	 * LeagueRankingBoard
	 */
	public LeagueRankingBoard(){
		this.leagueRankingTable = new ArrayList<>();
	}
	/**
	 * parseFixtureResultsFile
	 * @param file
	 */
	public void parseFixtureResultsFile(File file) {
		if (!file.exists()) {
			System.err.println("Error while getting File Name : " + file.getName());
			System.exit(0);
		} else {
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
				String str;
				System.out.println("============================ Fixture Input ====================================================");
				while ((str = bufferedReader.readLine()) != null) {
					System.out.println(str);
					FixtureProcessor fixtureProcessor = new FixtureProcessor();
					fixtureProcessor.processResultPerLine(str);
					fixtureProcessor.recordPoints();
					for (Team team : fixtureProcessor.getPlayedTeams()) {
						addTeam(team);
					}
				}
				System.out.println("==============================================================================================");
				System.out.println();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		}
	}

	/**
	 * This Methods add the Team object to leagueRankingTable list and check whether the teams was already add to the list.
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
		System.out.println("============================League Table Results==============================================");
		for (Team team : leagueRankingTable) {
			suffix = team.getPoints() == 1 ? " pt" : " pts";
			System.out.println(index + 1 + ". " + team.getTeamName() + ", " + team.getPoints() + suffix);
			index++;
		}
		System.out.println("==============================================================================================");
	}

}
