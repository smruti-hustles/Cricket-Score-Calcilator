import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CricketScoreCalculator {
    private static Map<String, Map<String, Integer>> teamScores = new HashMap<>();
    private static String currentTeam;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Cricket Score Calculator!");
            System.out.println("Please select an option:");
            System.out.println("1. Choose a team");
            System.out.println("2. Add a new player");
            System.out.println("3. Add runs for a player");
            System.out.println("4. Record a wicket");
            System.out.println("5. View current scores");
            System.out.println("6. Determine the winning team");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    chooseTeam(scanner);
                    break;
                case 2:
                    addPlayer(scanner);
                    break;
                case 3:
                    addRuns(scanner);
                    break;
                case 4:
                    recordWicket();
                    break;
                case 5:
                    displayScores();
                    break;
                case 6:
                    determineWinner();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting Cricket Score Calculator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void chooseTeam(Scanner scanner) {
        System.out.print("Enter the name of the first team: ");
        String team1 = scanner.nextLine();
        teamScores.put(team1, new HashMap<>());
        currentTeam = team1;
        System.out.println("Team " + team1 + " has been chosen.");
    }

    private static void addPlayer(Scanner scanner) {
        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine();
        teamScores.get(currentTeam).put(playerName, 0);
        System.out.println("Player " + playerName + " added to team " + currentTeam + " successfully.");
    }

    private static void addRuns(Scanner scanner) {
        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine();
        if (teamScores.get(currentTeam).containsKey(playerName)) {
            System.out.print("Enter runs scored: ");
            int runsScored = scanner.nextInt();
            scanner.nextLine(); // consume newline character
            teamScores.get(currentTeam).put(playerName, teamScores.get(currentTeam).get(playerName) + runsScored);
            System.out.println(playerName + " of team " + currentTeam + " scored " + runsScored + " runs.");
        } else {
            System.out.println("Player " + playerName + " not found in team " + currentTeam + ".");
        }
    }

    private static void recordWicket() {
        System.out.println("A wicket has been recorded for team " + currentTeam + ".");
    }

    private static void displayScores() {
        System.out.println("Current Scores:");
        for (Map.Entry<String, Map<String, Integer>> teamEntry : teamScores.entrySet()) {
            String teamName = teamEntry.getKey();
            Map<String, Integer> playerScores = teamEntry.getValue();
            System.out.println("Team " + teamName + ":");
            int teamTotal = 0;
            for (Map.Entry<String, Integer> playerEntry : playerScores.entrySet()) {
                String playerName = playerEntry.getKey();
                int playerScore = playerEntry.getValue();
                System.out.println(playerName + ": " + playerScore + " runs");
                teamTotal += playerScore;
            }
            System.out.println("Team " + teamName + " total: " + teamTotal + " runs");
        }
    }

    private static void determineWinner() {
        int team1Total = 0, team2Total = 0;
        for (Map.Entry<String, Map<String, Integer>> teamEntry : teamScores.entrySet()) {
            String teamName = teamEntry.getKey();
            Map<String, Integer> playerScores = teamEntry.getValue();
            int teamTotal = 0;
            for (int playerScore : playerScores.values()) {
                teamTotal += playerScore;
            }
            if (teamName.equals(teamScores.keySet().toArray()[0])) {
                team1Total = teamTotal;
            } else {
                team2Total = teamTotal;
            }
        }
        if (team1Total > team2Total) {
            System.out.println("Team " + teamScores.keySet().toArray()[0] + " wins!");
        } else if (team2Total > team1Total) {
            System.out.println("Team " + teamScores.keySet().toArray()[1] + " wins!");
        } else {
            System.out.println("The match is tied!");
        }
    }
}
