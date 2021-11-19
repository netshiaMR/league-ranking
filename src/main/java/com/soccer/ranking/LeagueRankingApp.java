package com.soccer.ranking;

import java.io.File;
import java.io.IOException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * LeagueRankingApp 
 * @author netshia Rendani
 * @since 18/11/2021 T21:00
 */
public class LeagueRankingApp {
	@Option(name = "-file", usage = "Upload a txt file with fixture results separated by commas ")
	private File file;

	public static void main(String[] args) {
		try {
			new LeagueRankingApp().run(args);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * run
	 * @param args
	 * @throws IOException
	 */
	public void run(String[] args) throws IOException {
		CmdLineParser parser = new CmdLineParser(this);
		try {
			parser.parseArgument(args);
			if (file != null) {
				LeagueRankingBoard leagueRankingBoard = new LeagueRankingBoard();
				leagueRankingBoard.parseFixtureResultsFile(file);
				leagueRankingBoard.printLeagueRankingBoard();
			} else {
				parser.printUsage(System.err);
			}
		} catch (CmdLineException e) {
			parser.printUsage(System.err);
		}
	}
}
