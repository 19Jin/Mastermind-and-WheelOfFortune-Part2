import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {
    //test:
    public static void main(String[] args){
//        GameRecord g1 = new GameRecord("A", 23);
//        GameRecord g2 = new GameRecord("ZB", 10);
//        GameRecord g3 = new GameRecord("C", -3);
//        GameRecord g4 = new GameRecord("F", 67);
//        GameRecord g5 = new GameRecord("F", 1);
//        GameRecord g6 = new GameRecord("F", 53);
//
//        AllGamesRecord agr = new AllGamesRecord();
//        agr.add(g1);
//        agr.add(g2);
//        agr.add(g3);
//        agr.add(g4);
//        agr.add(g5);
//        agr.add(g6);
//
//        System.out.println(agr);
//        System.out.println(agr.average());

//        String test = "";
//        System.out.println("Enter a letter: ");
//        Scanner keyboard = new Scanner(System.in);
//        String userInput = keyboard.nextLine().toLowerCase();
//        System.out.println(test.indexOf(userInput));
        WheelOfFortuneUserGame hangmanUserGame = new WheelOfFortuneUserGame();
        hangmanUserGame.randomPhrase();
        System.out.println(hangmanUserGame.phraseList);
        System.out.println("\n");
        System.out.println(hangmanUserGame.phrase);
        hangmanUserGame.phraseList.remove(hangmanUserGame.phrase);
        System.out.println(hangmanUserGame.phraseList);


    }
}
