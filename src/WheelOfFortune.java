import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class WheelOfFortune extends Game {
    /**
     * This class will have much of the code from your existing WheelOfFortune class
     * including readPhrases, randomPhrase, getHiddenPhrase, and processGuess.
     * It should also define an abstract method getGuess(String previousGuesses),
     * which returns a char, thus requiring all concrete WheelOfFortune games to implement it.
     * And of course WheelOfFortune needs to implement the abstract methods in its parent Game.
     */

    /**
     * ❓️⚠️I can't instantiate WheelOfFortune (abstract class) but google said abstract can have constructor.⚠️
     * Game class includes playAll(): AllGamesRecord (use the list of GameRecord)
     * play(): GameRecord, return a result of one game; playNext(): bool
     */

    /**
     * ❓️⚠️set those data member as protected. Is this because that we need to used those data member in subclass?⚠️
     */
    protected List<String> phraseList;
    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected StringBuilder previousPhrase;
    protected String previousGuesses;


    @Override
    public GameRecord play() {

        randomPhrase();
        System.out.println("The phrase is: " + phrase);
        getHiddenPhrase();
        System.out.println(hiddenPhrase);

        previousGuesses = "";

        String playerId = getUserId();

        int score = 0;

        //input the number of guesses allowed
        int num = getMaxGuessNum();

        int numOfGuess = 0;
        int numOfIncorrect = 0;
        int numOfCorrect = 0;
        int numOfLast = num;

        while(numOfGuess < num){

            char user = getGuess(previousGuesses);

            boolean flag = processGuess(user);

            if(!flag){
                numOfIncorrect++;
            }else if(flag && !previousPhrase.toString().equals(phrase)){
                numOfCorrect++;
                ;
            }else if(flag && previousPhrase.toString().equals(phrase)){
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

        score = score + numOfCorrect * 5 - numOfIncorrect * 3;
        System.out.println("Your score is:  " + score);

        //remove thg guessed phrase from the PhraseList
        phraseList.remove(phrase);

        return new GameRecord(playerId, score);
    }


    //Common abstract methods for Wheel of Fortune
    //humanUser AIUser getGuess 的方式不一样
    public abstract char getGuess(String previousGuesses);
    public abstract String getUserId();
    public abstract int getMaxGuessNum();

    //Common concrete methods
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

    public String randomPhrase(){
        // Get a random phrase from the list
        Random rand = new Random();
        int r = rand.nextInt(phraseList.size());
        phrase = phraseList.get(r);
        return phrase;
    }

    public void getHiddenPhrase(){
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isLetter(phrase.charAt(i))) {
                sb.append('*');
            } else {
                sb.append(phrase.charAt(i));
            }
        }
        hiddenPhrase = sb;
        previousPhrase = sb;
    }

    public boolean processGuess(Character userInput){

        boolean res = false;

        if(!Character.isLetter(userInput)){
            System.out.println("You Should Guess The Letter.");
        }else if(!phrase.toLowerCase().contains(String.valueOf(userInput))){
            System.out.println("Wrong Letter");
        }else if(phrase.toLowerCase().contains(String.valueOf(userInput))){
            for(int idx = 0; idx < phrase.length(); idx++){
                if(phrase.toLowerCase().charAt(idx) == userInput){
                    previousPhrase.setCharAt(idx, phrase.charAt(idx));
                }
            }
            System.out.println("Correct Letter!");
            System.out.println(this.previousPhrase);
            res = true;
        }
        return res;
    }

}
