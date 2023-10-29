import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class WheelOfFortunePart2 extends GuessingGame {
    /**
     * This class will have much of the code from your existing WheelOfFortune class
     * including readPhrases, randomPhrase, getHiddenPhrase, and processGuess.
     * It should also define an abstract method getGuess(String previousGuesses),
     * which returns a char, thus requiring all concrete WheelOfFortune games to implement it.
     * And of course WheelOfFortune needs to implement the abstract methods in its parent Game.
     */

    //Common abstract methods for Wheel of Fortune
    public abstract String getUserId();
    public abstract int getMaxGuessNum();
    public abstract String getGuess(ArrayList<String> previousGuesses);


    //Common concrete methods
    public boolean checkAnswer(){
        return previousPuzzle.toString().equals(puzzle);
    }
    public void removePuzzle(){
        phraseList.remove(puzzle); //remove thg guessed phrase from the PhraseList
    }
    @Override
    public void readPhrases(){
        // Get a random phrase from the list
        phraseList = null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    @Override
    public String randomPuzzle(){
        // Get a random phrase from the list
        Random rand = new Random();
        int r = rand.nextInt(phraseList.size());
        puzzle = phraseList.get(r);
        return puzzle;
    }
    @Override
    public boolean processGuess(String userInput){

        boolean res = false;

        if(!Character.isLetter(userInput.charAt(0))){
            System.out.println("You Should Guess The Letter.");
        }else if(!puzzle.toLowerCase().contains(String.valueOf(userInput))){
            System.out.println("Wrong Letter");
        }else if(puzzle.toLowerCase().contains(String.valueOf(userInput))){
            for(int idx = 0; idx < puzzle.length(); idx++){
                if(puzzle.toLowerCase().charAt(idx) == userInput.charAt(0)){
                    previousPuzzle.setCharAt(idx, puzzle.charAt(idx));
                }
            }
            System.out.println("Correct Letter!");
            System.out.println(this.previousPuzzle);
            res = true;
        }
        return res;
    }
    @Override
    public boolean playNext() {
        if(phraseList.size() == 0){
            System.out.println("We have run out of the phrases");
            return false;
        }
        return super.playNext();
    }

}