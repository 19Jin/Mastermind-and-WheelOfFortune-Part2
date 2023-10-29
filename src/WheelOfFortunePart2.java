import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Paths;
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

    protected List<String> phraseList;
    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected StringBuilder previousPhrase;
    protected String previousGuesses;


    @Override
    public GameRecord play() {

        randomPuzzle();
        System.out.println("The phrase is: " + phrase);
        getHiddenPuzzle();
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

            String user = getGuess(previousGuesses);

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
    public abstract String getGuess(String previousGuesses);
    public abstract String getUserId();
    public abstract int getMaxGuessNum();

    public boolean checkAnswer(){
        return previousPhrase.toString().equals(phrase);
    }

    public void removePuzzle(){
        phraseList.remove(puzzle); //remove thg guessed phrase from the PhraseList
    }

    //Common concrete methods
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
        }else if(!phrase.toLowerCase().contains(String.valueOf(userInput))){
            System.out.println("Wrong Letter");
        }else if(phrase.toLowerCase().contains(String.valueOf(userInput))){
            for(int idx = 0; idx < phrase.length(); idx++){
                if(phrase.toLowerCase().charAt(idx) == userInput.charAt(0)){
                    previousPhrase.setCharAt(idx, phrase.charAt(idx));
                }
            }
            System.out.println("Correct Letter!");
            System.out.println(this.previousPhrase);
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