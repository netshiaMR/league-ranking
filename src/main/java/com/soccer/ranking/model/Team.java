package com.soccer.ranking.model;

import java.util.Comparator;

public class Team {
	private int points;
	private String teamName;

	public Team(String teamName) {
		this.teamName = teamName;
	}

	public void incrementPoints(int points) {
		this.points += points;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Team)) {
			return false;
		}

		Team other = (Team) obj;
		return this.getTeamName().equalsIgnoreCase(other.getTeamName());
	}

	public static Comparator<Team> TeamComparator = new Comparator<Team>() {
		public int compare(Team teamA, Team teamB) {
			int teamAPoints = teamA.getPoints();
			int teamBPoints = teamB.getPoints();

			int difference = teamBPoints - teamAPoints;

			if (!(difference == 0)) {
				return difference;
			}
			return teamA.getTeamName().compareTo(teamB.getTeamName());
		}

	};
}
