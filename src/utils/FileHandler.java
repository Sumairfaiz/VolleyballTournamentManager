package utils;

import tournament.Tournament;
import models.Team;
import models.Match;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    private static final String FILE_NAME = "tournament_results.txt";

    public static void saveResults(Tournament t) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME);

            fw.write("Tournament : " + t.getName() + "\n");
            fw.write("Location   : " + t.getLocation() + "\n");
            fw.write("Date       : " + t.getDate() + "\n\n");

            fw.write("=== STANDINGS ===\n");
            Team[] teams = t.getTeams();
            for (int i = 0; i < t.getTeamCount(); i++) {
                fw.write((i + 1) + ". " + teams[i].getDetails() + "\n");
            }

            fw.write("\n=== MATCH RESULTS ===\n");
            Match[] matches = t.getMatches();
            for (int i = 0; i < t.getMatchCount(); i++) {
                fw.write(matches[i].toString() + "\n");
            }

            fw.close();
            System.out.println("Results saved to " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Could not save file: " + e.getMessage());
        }
    }
}
