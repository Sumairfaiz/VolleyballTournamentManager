package tournament;

import models.Team;
import models.Match;

public abstract class Tournament {

    protected String name;
    protected String location;
    protected String date;

    protected Team[]  teams;
    protected Match[] matches;
    protected int teamCount;
    protected int matchCount;

    protected static final int MAX_TEAMS   = 16;
    protected static final int MAX_MATCHES = 120;

    public Tournament(String name, String location, String date) {
        this.name     = name;
        this.location = location;
        this.date     = date;
        this.teams    = new Team[MAX_TEAMS];
        this.matches  = new Match[MAX_MATCHES];
        this.teamCount  = 0;
        this.matchCount = 0;
    }

    // every subclass must define how to schedule
    public abstract void scheduleMatches();

    public void addTeam(Team t) {
        if (teamCount >= MAX_TEAMS) {
            System.out.println("Tournament is full!");
            return;
        }
        teams[teamCount++] = t;
        System.out.println("Team \"" + t.getTeamName() + "\" added.");
    }

    public void showFixtures() {
        System.out.println("\n-------- Fixtures: " + name + " --------");
        if (matchCount == 0) {
            System.out.println("No matches scheduled yet.");
            return;
        }
        for (int i = 0; i < matchCount; i++) {
            System.out.println(matches[i]);
        }
    }

    public void showStandings() {
        System.out.println("\n-------- Standings: " + name + " --------");
        // selection sort — highest points first
        for (int i = 0; i < teamCount - 1; i++) {
            int best = i;
            for (int j = i + 1; j < teamCount; j++) {
                if (teams[j].getPoints() > teams[best].getPoints()) best = j;
            }
            Team tmp = teams[i]; teams[i] = teams[best]; teams[best] = tmp;
        }
        for (int i = 0; i < teamCount; i++) {
            System.out.println((i + 1) + ". " + teams[i].getDetails());
        }
    }

    public String getDetails() {
        return name + " | " + location + " | " + date
             + " | Teams: " + teamCount;
    }

    // getters
    public String getName()      { return name; }
    public String getLocation()  { return location; }
    public String getDate()      { return date; }
    public Team[]  getTeams()    { return teams; }
    public Match[] getMatches()  { return matches; }
    public int getTeamCount()    { return teamCount; }
    public int getMatchCount()   { return matchCount; }
}
