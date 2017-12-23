import java.util.Scanner;

/**
 * Created by huynq on 12/20/17.
 */
public class DungeonEscape {
    public static void main(String[] args) {

        String[][] map = {
                {"-", "-", "-", "M"},
                {"-", "-", "-", "-"},
                {"-", "-", "-", "-"},
                {"-", "-", "-", "E"}
        };

        Scanner keyScanner = new Scanner(System.in);

        int playerRowIndex = 1;
        int playerColIndex = 2;
        boolean loop = true;
        boolean hasKey = false;

        while (loop) {
            for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
                String[] row = map[rowIndex];
                for (int colIndex = 0; colIndex < row.length; colIndex++) {
                    String cell = row[colIndex];
                    if (rowIndex == playerRowIndex && colIndex == playerColIndex) {
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

            if (command.equalsIgnoreCase("W")) {
                playerRowIndex--;
            } else if (command.equalsIgnoreCase("S")) {
                playerRowIndex++;
            } else if (command.equalsIgnoreCase("A")) {
                playerColIndex--;
            } else if (command.equalsIgnoreCase("D")) {
                playerColIndex++;
            } else {
                System.out.println("Command not found");
            }

            // Clamp
            playerRowIndex = clamp(playerRowIndex, 0, map.length - 1);
            playerColIndex = clamp(playerColIndex, 0, map[0].length - 1);

            if (map[playerRowIndex][playerColIndex].equalsIgnoreCase("M")) {
                hasKey = true;
                map[playerRowIndex][playerColIndex] = "-";
            }

            if (map[playerRowIndex][playerColIndex].equalsIgnoreCase("E")) {
                if (hasKey) {
                    System.out.println("You've escaped the dungeon");
                    loop = false;
                } else {
                    System.out.println("Get the key, dude!");
                }
            }
        }


        // 1: Scanner => 'W', 'A', 'S', 'D'
        // nextLine

        // 2 move => update playerRowIndex, playerColIndex

        // Print
    }

    static int clamp(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

//    public static void main(String[] args) {
//        int val = 1333;
//        int mi = 0;
//        int mx = 255;
//
//        int x = clamp(val, mi, mx);
//        System.out.println(x);
//    }

}
