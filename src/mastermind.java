import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class mastermind extends GuessingGame {

    @Override
    public String randomPuzzle(){
        // Get a random phrase from the list
        String colorSet = "RGBYOP";
        String generatePuzzle = "";
        Random rand = new Random();
        for(int i = 0; i < 4; i++){
            int r = rand.nextInt(colorSet.length());
            generatePuzzle += colorSet.charAt(r);
        }
        puzzle = generatePuzzle;
        return puzzle;
    }

    @Override
    public String getUserId() {
        System.out.println("Please enter your Id: ");
        Scanner keyboardEnterId = new Scanner(System.in);
        String playerId = keyboardEnterId.nextLine();
        return "HumanPlayer " + playerId;
    }

    @Override
    public int getMaxGuessNum() {
        System.out.println("Enter the number of guesses allowed: ");
        Scanner numberKeyboard = new Scanner(System.in);
        int num = numberKeyboard.nextInt();
        return num;
    }

    @Override
    public String getGuess(ArrayList<String> previousGuesses) {
        //scanner the user input
        System.out.println("Enter Your Guess: ");
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.nextLine().toUpperCase();

        //check validity
        for(int i = 0; i < userInput.length(); i++){
            if(!(userInput.charAt(i) <= 90 && userInput.charAt(i) >= 65)){
                System.out.println("You Should Guess Letters/Colors");
                return getGuess(this.previousGuesses);
            }
        }

        //check for duplicates
        if(this.previousGuesses.indexOf(userInput) == -1){
            this.previousGuesses.add(userInput);
            return userInput;
        }else{
            System.out.println("You have guessed this color set \n Please enter another one: ");
            return getGuess(this.previousGuesses);
        }
    }

    @Override
    public boolean processGuess(String userInput) {
        boolean flag = false;
        int partials = checkPartials(puzzle, userInput);
        int exacts = checkExacts(puzzle, userInput);
        System.out.println(exacts + " exact, " + partials + " partial.");
        if(exacts == 4){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAnswer() {
        return previousGuesses.get(previousGuesses.size()-1).contentEquals(puzzle);
    }

    @Override
    public void removePuzzle(){
        ;
    }

    @Override
    public void readPhrases() {
        ;
    }

    public int checkPartials(String secretSB, String guessSB) {
        // compare secret to guess
        int i=0;
        int partials=0;
        while (i<4) {
            int j=0;
            while (j<4) {
                if (guessSB.charAt(i) == secretSB.charAt(j)) {
                    partials = partials + 1;
                    break;
                }
                j++;
            }
            i++;
        }
        return partials;
    }

    public int checkExacts(String guessSB,String secretSB) {
        // compare secret to guess
        int i=0;
        int exacts=0;
        while (i<4) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                exacts = exacts + 1;
                }
            i++;
        }
        return exacts;
    }

    //main function:
    public static void main(String [] args) {
        mastermind mastermindGame = new mastermind();
        AllGamesRecord record = mastermindGame.playAll();
        System.out.println(record);
    }

}
