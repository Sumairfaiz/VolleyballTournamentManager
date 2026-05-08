import models.Player;
import models.Team;
import models.Match;
import tournament.Tournament;
import tournament.KnockoutTournament;
import tournament.RoundRobinTournament;
import utils.FileHandler;

import java.util.Scanner;

public class Main {

    static Tournament tournament = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("     VOLLEYBALL TOURNAMENT MANAGER      ");
        System.out.println(" Sumair Muhammad | Laraib Mughal | Ghulam Mujtaba ");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Enter choice: ");
            sc.nextLine();

            switch (choice) {
                case 1  -> createTournament();
                case 2  -> addTeam();
                case 3  -> addPlayer();
                case 4  -> scheduleMatches();
                case 5  -> showFixtures();
                case 6  -> recordResult();
                case 7  -> showStandings();
                case 8  -> showAllTeams();
                case 9  -> showRoster();
                case 10 -> saveToFile();
                case 0  -> { System.out.println("Goodbye!"); running = false; }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
        sc.close();
    }

    static void showMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println(" 1. Create Tournament");
        System.out.println(" 2. Add Team");
        System.out.println(" 3. Add Player to Team");
        System.out.println(" 4. Schedule Matches");
        System.out.println(" 5. View Fixtures");
        System.out.println(" 6. Record Match Result");
        System.out.println(" 7. View Standings");
        System.out.println(" 8. View All Teams");
        System.out.println(" 9. View Team Roster");
        System.out.println("10. Save Results to File");
        System.out.println(" 0. Exit");
        System.out.println("===========================");
    }

    // ── 1. Create tournament ────────────────────────────────────────────
    static void createTournament() {
        System.out.print("Tournament name : ");
        String name = sc.nextLine();
        System.out.print("Location        : ");
        String loc  = sc.nextLine();
        System.out.print("Date            : ");
        String date = sc.nextLine();
        System.out.print("Type (1=Knockout  2=RoundRobin): ");
        int type = readInt("");
        sc.nextLine();

        if (type == 1) {
            tournament = new KnockoutTournament(name, loc, date);
        } else {
            tournament = new RoundRobinTournament(name, loc, date);
        }
        System.out.println("Tournament created! " + tournament.getDetails());
    }

    // ── 2. Add team ─────────────────────────────────────────────────────
    static void addTeam() {
        if (tournament == null) { System.out.println("Create a tournament first!"); return; }
        System.out.print("Team name : ");
        String tName = sc.nextLine();
        System.out.print("Coach     : ");
        String coach = sc.nextLine();
        tournament.addTeam(new Team(tName, coach));
    }

    // ── 3. Add player ───────────────────────────────────────────────────
    static void addPlayer() {
        if (tournament == null) { System.out.println("Create a tournament first!"); return; }
        System.out.print("Team name    : ");
        String tName = sc.nextLine();

        Team team = findTeam(tName);
        if (team == null) { System.out.println("Team not found!"); return; }

        System.out.print("Player name  : ");
        String pName = sc.nextLine();
        int jersey   = readInt("Jersey number: ");
        sc.nextLine();
        System.out.print("Position     : ");
        String pos = sc.nextLine();

        team.addPlayer(new Player(pName, jersey, pos));
        System.out.println("Player added to " + team.getTeamName());
    }

    // ── 4. Schedule ─────────────────────────────────────────────────────
    static void scheduleMatches() {
        if (tournament == null) { System.out.println("Create a tournament first!"); return; }
        tournament.scheduleMatches();
    }

    // ── 5. Fixtures ─────────────────────────────────────────────────────
    static void showFixtures() {
        if (tournament == null) { System.out.println("Create a tournament first!"); return; }
        tournament.showFixtures();
    }

    // ── 6. Record result ────────────────────────────────────────────────
    static void recordResult() {
        if (tournament == null) { System.out.println("Create a tournament first!"); return; }
        int id = readInt("Match ID : ");
        sc.nextLine();

        Match[] matches = tournament.getMatches();
        for (int i = 0; i < tournament.getMatchCount(); i++) {
            if (matches[i].getMatchId() == id) {
                if (matches[i].isPlayed()) {
                    System.out.println("Result already recorded for this match.");
                    return;
                }
                int sA = readInt("Score for " + matches[i].getTeamA().getTeamName() + ": ");
                sc.nextLine();
                int sB = readInt("Score for " + matches[i].getTeamB().getTeamName() + ": ");
                sc.nextLine();
                matches[i].recordResult(sA, sB);
                System.out.println("Recorded: " + matches[i]);
                return;
            }
        }
        System.out.println("Match #" + id + " not found.");
    }

    // ── 7. Standings ────────────────────────────────────────────────────
    static void showStandings() {
        if (tournament == null) { System.out.println("Create a tournament first!"); return; }
        tournament.showStandings();
    }

    // ── 8. All teams ────────────────────────────────────────────────────
    static void showAllTeams() {
        if (tournament == null) { System.out.println("Create a tournament first!"); return; }
        System.out.println("\n---- Registered Teams ----");
        Team[] teams = tournament.getTeams();
        for (int i = 0; i < tournament.getTeamCount(); i++) {
            System.out.println((i + 1) + ". " + teams[i].getTeamName()
                             + " | Coach: " + teams[i].getCoach());
        }
    }

    // ── 9. Roster ───────────────────────────────────────────────────────
    static void showRoster() {
        if (tournament == null) { System.out.println("Create a tournament first!"); return; }
        System.out.print("Team name: ");
        String tName = sc.nextLine();
        Team team = findTeam(tName);
        if (team == null) { System.out.println("Team not found!"); return; }

        System.out.println("\n---- Roster: " + team.getTeamName()
                         + " (Coach: " + team.getCoach() + ") ----");
        if (team.getPlayerCount() == 0) {
            System.out.println("No players added yet.");
            return;
        }
        Player[] players = team.getPlayers();
        for (int i = 0; i < team.getPlayerCount(); i++) {
            System.out.println(players[i]);
        }
    }

    // ── 10. Save ────────────────────────────────────────────────────────
    static void saveToFile() {
        if (tournament == null) { System.out.println("Create a tournament first!"); return; }
        FileHandler.saveResults(tournament);
    }

    // ── Helper: find team by name ────────────────────────────────────────
    static Team findTeam(String name) {
        Team[] teams = tournament.getTeams();
        for (int i = 0; i < tournament.getTeamCount(); i++) {
            if (teams[i].getTeamName().equalsIgnoreCase(name)) return teams[i];
        }
        return null;
    }

    // ── Helper: safe int input ───────────────────────────────────────────
    static int readInt(String prompt) {
        if (!prompt.isEmpty()) System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }
}
