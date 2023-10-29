import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class GuessingGame extends Game {
/**
 * re-factor WheelOfFortune so that as much of the code as possible for the two games
 * is placed in a superclass named “GuessingGame”.
 * Create a new project and repo for part 2 and begin with your code from part 1.
 * The goal of part 2 is to encapsulate code within GuessingGame so that
 * it can be reused in WheelOfFortuned and Mastermind-- communicate with your client before coding!
 */
    protected List<String> phraseList;
    protected String puzzle;
    protected StringBuilder hiddenPuzzle;
    protected StringBuilder previousPuzzle;
    protected ArrayList<String> previousGuesses;


    @Override
    public GameRecord play() {

        randomPuzzle();
        System.out.println("The hidden one is: " + puzzle);
        getHiddenPuzzle();
        System.out.println(hiddenPuzzle);

        previousGuesses = new ArrayList<>();

        String playerId = getUserId();

        int score = 0;

        //input the number of guesses allowed
        int num = getMaxGuessNum();

        int numOfGuess = 0;
        int numOfIncorrect = 0;
        int numOfCorrect = 0;
        int numOfLast = num;

        while(numOfGuess < num){

            String user = getGuess(previousGuesses);

            boolean flag = processGuess(user);

            if(!flag){
                numOfIncorrect++;
            }else if(flag && !checkAnswer()){
                numOfCorrect++;
                ;
            }else if(flag && checkAnswer()){
                numOfCorrect++;
                break;
            }

            numOfGuess++;
            numOfLast = num - numOfGuess;
            System.out.println("Number of Previous Misses: " + numOfIncorrect);
            System.out.println("You Still Have " + numOfLast + " Chances");
        }

        if(numOfGuess >= num){
            System.out.println("You Failed");
            System.out.println("You Missed " + numOfIncorrect + " Times");
        }else{
            score += 50;
            System.out.println("Congratulations! You Win!!");
        }

        score = score + numOfCorrect * 5 - numOfIncorrect * 3 - numOfGuess * 2;
        System.out.println("Your score is:  " + score);

        //remove thg guessed phrase from the PhraseList
        removePuzzle();

        return new GameRecord(playerId, score);
    }

    //Common abstract methods for Wheel of Fortune
    //humanUser AIUser getGuess 的方式不一样
    public abstract String getGuess(ArrayList<String> previousGuesses);
    public abstract String getUserId();
    public abstract int getMaxGuessNum();
    public abstract boolean checkAnswer();
    public abstract void removePuzzle();
    public abstract void readPhrases();
    public abstract String randomPuzzle();
    public abstract boolean processGuess(String userInput);

    //Common concrete methods
    public void getHiddenPuzzle(){
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < puzzle.length(); i++) {
            if (Character.isLetter(puzzle.charAt(i))) {
                sb.append('*');
            } else {
                sb.append(puzzle.charAt(i));
            }
        }
        hiddenPuzzle = sb;
        previousPuzzle = sb;
    }

    @Override
    public boolean playNext() {
        System.out.println("Do you want to play again? Y or N ");
        Scanner answerKeyboard = new Scanner(System.in);
        String userLetter = answerKeyboard.nextLine().toUpperCase();
        if(userLetter.contentEquals("Y")){
            return true;
        }else{
            return false;
        }
    }

}
