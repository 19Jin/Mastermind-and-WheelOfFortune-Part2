import java.util.ArrayList;
import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortunePart2 {

    public WheelOfFortuneUserGame(){
        super.readPhrases();
    }

    /**
     * override the “getGuess” method of the abstract WheelOfFortune class
     * using Scanner to get the guess.
     * @param
     * @return
     */
    @Override
    public String getGuess(ArrayList<String> previousGuesses) {
        //scanner the user input
        System.out.println("Enter a letter: ");
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.nextLine().toLowerCase();

        //check validity
        if(!Character.isLetter(userInput.charAt(0))){
            System.out.println("You Should Guess A Letter.");
            return getGuess(this.previousGuesses);
        }
        //check for duplicates
        if(this.previousGuesses.indexOf(userInput) == -1){
            this.previousGuesses.add(userInput);
            return userInput;
        }else{
            System.out.println("You have guessed this letter \n Please enter another one: ");
            return getGuess(this.previousGuesses);
        }
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


    //main function:
    public static void main(String [] args) {
        WheelOfFortuneUserGame hangmanUserGame = new WheelOfFortuneUserGame();
        AllGamesRecord record = hangmanUserGame.playAll();
        System.out.println(record);
    }

}
