package models;

public class Match {
    private static int idCounter = 1;

    private int matchId;
    private Team teamA;
    private Team teamB;
    private int scoreA;
    private int scoreB;
    private boolean played;
    private String round;

    public Match(Team teamA, Team teamB, String round) {
        this.matchId = idCounter++;
        this.teamA   = teamA;
        this.teamB   = teamB;
        this.scoreA  = 0;
        this.scoreB  = 0;
        this.played  = false;
        this.round   = round;
    }

    public void recordResult(int scoreA, int scoreB) {
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.played = true;

        if (scoreA > scoreB) {
            teamA.recordWin();
            teamB.recordLoss();
        } else {
            teamB.recordWin();
            teamA.recordLoss();
        }
    }

    public Team getWinner() {
        if (!played) return null;
        return (scoreA > scoreB) ? teamA : teamB;
    }

    public int  getMatchId() { return matchId; }
    public Team getTeamA()   { return teamA; }
    public Team getTeamB()   { return teamB; }
    public int  getScoreA()  { return scoreA; }
    public int  getScoreB()  { return scoreB; }
    public boolean isPlayed(){ return played; }
    public String getRound() { return round; }

    @Override
    public String toString() {
        if (played) {
            return "[Match #" + matchId + " | " + round + "] "
                 + teamA.getTeamName() + " " + scoreA
                 + " - "
                 + scoreB + " " + teamB.getTeamName()
                 + "  --> Winner: " + getWinner().getTeamName();
        } else {
            return "[Match #" + matchId + " | " + round + "] "
                 + teamA.getTeamName() + " vs " + teamB.getTeamName()
                 + " (not played yet)";
        }
    }
}
