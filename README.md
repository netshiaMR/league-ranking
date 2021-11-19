# League-ranking
** Rendani Netshai **  </br>
A command-line application that will calculate the ranking table for a soccer league

**Input/output**  </br>
	The input and output will be text. Either using stdin/stdout or taking filenames on the command
	line is fine. </br>
	The input contains results of games, one per line. See “Sample input” for details. </br>
	The output should be ordered from most to least points, following the format specified in
	“Expected output”. </br>
	You can expect that the input will be well-formed. There is no need to add special handling for
	malformed input files. </br>

**The rules** </br>
	In this league, a draw (tie) is worth 1 point and a win is worth 3 points. A loss is worth 0 points. </br>
	If two or more teams have the same number of points, they should have the same rank and be </br>
	printed in alphabetical order (as in the tie for 3rd place in the sample data). </br>
	
**Software requirements**
 - Java JDK 11 or higher
 - [Maven 3.8.0] (https://maven.apache.org/install.html)    

**Installation Instructions**
1. Clone the *League-ranking* or download the zip file from github 
2. Navigate to the project root folder *League-ranking* and open terminal/command line from there
3. Build the by running bat script of maven command : 
	
	> build_leagueranking.bat
4. Use the follow command to run run an application after a successful build then 
5. cd to the target  then run the following command

>  java -jar soccer-league-1.0-SNAPSHOT-jar-with-dependencies.jar -f  `{{path of input file}}`

e.g for the running and application command 
>java -jar ./target/leaguerankingApp-jar-with-dependencies.jar -file C:\input.txt

note that run_app.bat script is not fully completed 

**Test**  </br>
***Sample input:*** </br>
============================ Fixture Input==================================================== </br>
Lions 3, Snakes 3  </br>
Tarantulas 1, FC Awesome 0  </br>
Lions 1, FC Awesome 1  </br>
Tarantulas 3, Snakes 1  </br>
Lions 4, Grouches 0  </br>
===============================================================================================  </br>

***Expected output:*** </br>

============================League Table Results==============================================  </br>
1. Tarantulas, 6 pts  </br>
2. Lions, 5 pts  </br>
3. FC Awesome, 1 pt  </br>
4. Snakes, 1 pt  </br>
5. Grouches, 0 pts  </br>
============================================================================================== </br>
 </br>

