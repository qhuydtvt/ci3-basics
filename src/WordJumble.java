import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by huynq on 12/20/17.
 */
public class WordJumble {
    public static void main(String[] args) {
        String[] words = {"smart", "cliche", "car", "tomorrow"};

        Random random = new Random();
        int randomPick = random.nextInt(words.length);

        String randomWord = words[randomPick];
        randomWord = randomWord.toUpperCase();

        String[] characters = randomWord.split("");
        ArrayList<String> characterList = new ArrayList<>(Arrays.asList(characters));

        while(characterList.size() > 0) {
            // 1. Select random character
            int randomIndex = random.nextInt(characterList.size());
            String randomCharacter = characterList.get(randomIndex);

            // 2. Print it out
            System.out.print(randomCharacter + " ");

            // 3. Remove it from characterList
            characterList.remove(randomIndex);
        }

        System.out.println("Guess what it is?");
        Scanner scanner = new Scanner(System.in);
        String userGuess = scanner.nextLine();

        if (userGuess.equalsIgnoreCase(randomWord)) {
            System.out.println("Bingo");
        } else {
            System.out.println(":(");
        }



//        String s = "Hello";
//        String s2 = s.toLowerCase();
//        System.out.println(s2);


    }
}
