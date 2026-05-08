package tournament;

import models.Match;

public class RoundRobinTournament extends Tournament {

    public RoundRobinTournament(String name, String location, String date) {
        super(name, location, date);
    }

    @Override
    public void scheduleMatches() {
        matchCount = 0;
        if (teamCount < 2) {
            System.out.println("Need at least 2 teams first.");
            return;
        }
        for (int i = 0; i < teamCount; i++) {
            for (int j = i + 1; j < teamCount; j++) {
                matches[matchCount++] = new Match(teams[i], teams[j], "Group Stage");
            }
        }
        System.out.println(matchCount + " match(es) scheduled (Round Robin).");
    }

    @Override
    public String getDetails() {
        return "[Round Robin] " + super.getDetails();
    }
}
