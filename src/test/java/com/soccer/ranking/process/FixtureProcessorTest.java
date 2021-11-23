package com.soccer.ranking.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.soccer.ranking.model.Team;

import org.junit.Assert; // since Assert from  junit.framework package is deprecation, am advance to use and alterntive of it from different package

public class FixtureProcessorTest {

	File file = null;
	private BufferedReader input;

	@Before
	public void init() {
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource("input.txt").getFile());
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		} catch (IOException e) {
			System.err.println("Unable to Read the file due to unexpected error: " + e.getMessage());
		}
	}

	@Test
	public final void testProcessResultPerLine() throws IOException {
		Assert.assertTrue(file.exists());
		String inputStringLine;
		while ((inputStringLine = input.readLine()) != null) {
			FixtureProcessor fixtureProcessor = new FixtureProcessor();
			fixtureProcessor.processResultPerLine(inputStringLine);
			Assert.assertEquals(2, fixtureProcessor.getPlayedTeams().size());
			Assert.assertEquals(2, fixtureProcessor.getScores().size());
		}
	}

	@Test
	public void shouldRecordPoints() {
		String sampleLineText = "Tarantulas 3, Snakes 1";
		FixtureProcessor fixtureProcessor = new FixtureProcessor();
		fixtureProcessor.processResultPerLine(sampleLineText);
		fixtureProcessor.recordPoints();
		Assert.assertEquals(2, fixtureProcessor.getPlayedTeams().size());
		Assert.assertEquals("Tarantulas", fixtureProcessor.getPlayedTeams().get(0).getTeamName());
		Assert.assertEquals("Snakes", fixtureProcessor.getPlayedTeams().get(1).getTeamName());
		Assert.assertEquals(3, fixtureProcessor.getPlayedTeams().get(0).getPoints());
		Assert.assertEquals(0, fixtureProcessor.getPlayedTeams().get(1).getPoints());
	}

	// Lions 3, Snakes 3 Tarantulas 1, FC Awesome 0 Lions 1, FC Awesome 1 Tarantulas
	// 3, Snakes 1 Lions 4, Grouches 0
	@Test
	public final void testGetPlayedTeams() throws IOException {
		Assert.assertTrue(file.exists());
		input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String inputStringLine;
		List<Team> listOfteam = new ArrayList<Team>();
		while ((inputStringLine = input.readLine()) != null) {
			System.out.println(inputStringLine);
			FixtureProcessor fixtureProcessor = new FixtureProcessor();
			fixtureProcessor.processResultPerLine(inputStringLine);
			for (Team team : fixtureProcessor.getPlayedTeams()) {
				listOfteam.add(team);
			}
		}

		Assert.assertEquals("Lions", listOfteam.get(0).getTeamName());
		Assert.assertEquals("Snakes", listOfteam.get(1).getTeamName());

		Assert.assertEquals("Tarantulas", listOfteam.get(2).getTeamName());
		Assert.assertEquals("FC Awesome", listOfteam.get(3).getTeamName());

		Assert.assertEquals("Lions", listOfteam.get(4).getTeamName());
		Assert.assertEquals("FC Awesome", listOfteam.get(5).getTeamName());

		Assert.assertEquals("Tarantulas", listOfteam.get(6).getTeamName());
		Assert.assertEquals("Snakes", listOfteam.get(7).getTeamName());

		Assert.assertEquals("Lions", listOfteam.get(8).getTeamName());
		Assert.assertEquals("Grouches", listOfteam.get(9).getTeamName());

	}

}
