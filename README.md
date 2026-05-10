# Volleyball Tournament Manager 🏐

A console-based Java application for managing volleyball tournaments — add teams, register players, schedule fixtures, record match results, view standings, and save results to a file.

---

## Group Members

| Name            | CMS ID      | Section |
|-----------------|-------------|---------|
| Sumair Muhammad | 023-05-0110 | D       |
| Laraib Mughal   | 023-24-0214 | D       |
| Ghulam Mujtaba  | 023-25-0038 | D       |

---

## Purpose

Manually tracking teams, fixtures, results, and standings in a volleyball tournament is messy and error-prone. This system handles it all through a simple console menu.

**Users:** Tournament organizers, sports coordinators, coaches.

---

## Project Structure

```
src/
├── models/
│   ├── Player.java          - Player with name, jersey, position
│   ├── Team.java            - Team with player array, wins/losses/points
│   └── Match.java           - Match between two teams, records result
├── tournament/
│   ├── Tournament.java      - Abstract base class
│   ├── KnockoutTournament.java   - Extends Tournament (single elimination)
│   └── RoundRobinTournament.java - Extends Tournament (everyone vs everyone)
├── utils/
│   └── FileHandler.java     - Saves results to tournament_results.txt
└── Main.java                - Menu + all program logic
```

---

## OOP Concepts Used

| Concept | Where |
|---------|-------|
| Encapsulation | Private fields + getters/setters in Player, Team, Match |
| Inheritance | KnockoutTournament and RoundRobinTournament extend Tournament |
| Polymorphism | scheduleMatches() and getDetails() overridden in both subclasses |
| Abstract Class | Tournament is abstract — cannot be instantiated directly |
| Arrays | Player[] in Team, Team[] and Match[] in Tournament |
| File Handling | FileHandler writes results to .txt using FileWriter |

---

## How to Run

**Requirement:** JDK 8 or above

**Compile:**
```
cd src
javac -d ../out models/*.java tournament/*.java utils/*.java Main.java
```

**Run:**
```
cd ../out
java Main
```

---

Demo Video
(https://www.youtube.com/watch?v=KPI7QTrJa5o)

GitHub Repository
(https://github.com/Sumairfaiz/VolleyballTournamentManager#demo-video)
