package models;

public class Team {
    private String teamName;
    private String coach;
    private Player[] players;
    private int playerCount;
    private static final int MAX_PLAYERS = 12;
    private int wins;
    private int losses;
    private int points;

    public Team(String teamName, String coach) {
        this.teamName = teamName;
        this.coach = coach;
        this.players = new Player[MAX_PLAYERS];
        this.playerCount = 0;
        this.wins = 0;
        this.losses = 0;
        this.points = 0;
    }

    public boolean addPlayer(Player p) {
        if (playerCount >= MAX_PLAYERS) {
            System.out.println("Squad full! Max " + MAX_PLAYERS + " players.");
            return false;
        }
        players[playerCount] = p;
        playerCount++;
        return true;
    }

    public void recordWin() {
        wins++;
        points += 3;
    }

    public void recordLoss() {
        losses++;
    }

    // getters
    public String getTeamName() { return teamName; }
    public String getCoach()    { return coach; }
    public Player[] getPlayers(){ return players; }
    public int getPlayerCount() { return playerCount; }
    public int getWins()        { return wins; }
    public int getLosses()      { return losses; }
    public int getPoints()      { return points; }

    // setters
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public void setCoach(String coach)       { this.coach = coach; }

    public String getDetails() {
        return teamName + " | Coach: " + coach
             + " | W: " + wins + " L: " + losses
             + " | Points: " + points;
    }

    @Override
    public String toString() { return teamName; }
}
