import java.util.Random;
import java.util.Scanner;

/**
 * Created by huynq on 12/20/17.
 */
public class DungeonEscape {
    public static void main(String[] args) {

        String[][] map = {
                {"-", "-", "W", "K"},
                {"-", "-", "-", "M"},
                {"-", "W", "-", "-"},
                {"-", "-", "-", "E"}
        };

        Scanner keyScanner = new Scanner(System.in);
        Random random = new Random();

        int playerRow = 1;
        int playerCol = 2;
        boolean loop = true;
        boolean hasKey = false;

        int playerHP = 10;
        int playerStr = 6;
        int playerDex = 5;

        int goblinHP = 4;
        int goblinStr = 7;
        int goblinDex = 2;


        while (loop) {
            for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
                String[] row = map[rowIndex];
                for (int colIndex = 0; colIndex < row.length; colIndex++) {
                    String cell = row[colIndex];
                    if (rowIndex == playerRow && colIndex == playerCol) {
                        System.out.print("P");
                    } else {
                        System.out.print(cell);
                    }
                    System.out.print(" ");
                }
                System.out.println("");
            }

            System.out.print("Your move? ");
            String command = keyScanner.nextLine();

            int playerNextRow = playerRow;
            int playerNextCol = playerCol;

            if (command.equalsIgnoreCase("W")) {
                playerNextRow--;
            } else if (command.equalsIgnoreCase("S")) {
                playerNextRow++;
            } else if (command.equalsIgnoreCase("A")) {
                playerNextCol--;
            } else if (command.equalsIgnoreCase("D")) {
                playerNextCol++;
            } else {
                System.out.println("Command not found");
            }

            // Clamp
            playerNextRow = clamp(playerNextRow, 0, map.length - 1);
            playerNextCol = clamp(playerNextCol, 0, map[0].length - 1);


            if(map[playerNextRow][playerNextCol].equalsIgnoreCase("M")) {
                boolean inCombat = true;
                while (inCombat) {
                    System.out.print("Your choice: 1. Attack, 2. Flee?");
                    command = keyScanner.nextLine();
                    if (command.equalsIgnoreCase("1")) {
                        int dice = random.nextInt(6) + 1;
                        if (dice < playerDex - goblinDex) {
                            int damage = playerStr;
                            if (damage > 0) {
                                goblinHP -= damage;
                                System.out.println(String.format("You attacked the goblin and it has %s HP left", goblinHP));
                                if (goblinHP <= 0) {
                                    System.out.println("The goblin has fallen");
                                    inCombat = false;
                                }
                            }
                        } else {
                            System.out.println("You attacked the goblin but missed");
                        }

                        if (inCombat) {
                            dice = random.nextInt(6) + 1;
                            if (dice > 3) {
                                playerHP -= goblinStr;
                                System.out.println(String.format("The goblin attacked you, now you have %sHP left", playerHP));
                                if (playerHP <= 0) {
                                    System.out.println("You're dead");
                                    inCombat = false;
                                    loop = false;
                                }
                            } else {
                                System.out.println("The goblin attacked you but missed");
                            }
                        }
                    } else if(command.equalsIgnoreCase("2")) {
                        System.out.println("Sorry:(. Not done yet");
                    }
                }
            }
            else if (!map[playerNextRow][playerNextCol].equalsIgnoreCase("W")) {
                // Allow move
                playerRow = playerNextRow;
                playerCol = playerNextCol;
            }

            if (map[playerRow][playerCol].equalsIgnoreCase("K")) {
                hasKey = true;
                map[playerRow][playerCol] = "-";
            }

            if (map[playerRow][playerCol].equalsIgnoreCase("E")) {
                if (hasKey) {
                    System.out.println("You've escaped the dungeon");
                    loop = false;
                } else {
                    System.out.println("Get the key, dude!");
                }
            }
        }

    }

    static int clamp(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}
