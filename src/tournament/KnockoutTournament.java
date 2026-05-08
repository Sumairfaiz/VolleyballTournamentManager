package tournament;

import models.Match;

public class KnockoutTournament extends Tournament {

    public KnockoutTournament(String name, String location, String date) {
        super(name, location, date);
    }

    @Override
    public void scheduleMatches() {
        matchCount = 0;
        if (teamCount < 2) {
            System.out.println("Need at least 2 teams first.");
            return;
        }
        String round = (teamCount <= 2) ? "Final"
                     : (teamCount <= 4) ? "Semi-Final"
                     : "Round of " + teamCount;

        for (int i = 0; i + 1 < teamCount; i += 2) {
            matches[matchCount++] = new Match(teams[i], teams[i + 1], round);
        }
        System.out.println(matchCount + " match(es) scheduled (Knockout).");
    }

    @Override
    public String getDetails() {
        return "[Knockout] " + super.getDetails();
    }
}
