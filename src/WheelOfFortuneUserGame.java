import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune {

    /**
     * override the “getGuess” method of the abstract WheelOfFortune class
     * using Scanner to get the guess.
     * @param
     * @return
     */

    public WheelOfFortuneUserGame(){
        super.readPhrases();
    }

    /**
     * get the user's guess by input
     * write a loop, scanner the input, if not guessed add into String, if guessed before, start a new scanner
     * @param previousGuesses
     * @return
     */
    @Override
    public char getGuess(String previousGuesses) {
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
            this.previousGuesses += userInput;
            return userInput.charAt(0);
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

    @Override
    public boolean playNext() {
        System.out.println("Do you want to play again? Y or N ");
        Scanner answerKeyboard = new Scanner(System.in);
        String userLetter = answerKeyboard.nextLine().toUpperCase();
        if(userLetter.contentEquals("Y")){
            if(phraseList.size() == 0){
                System.out.println("We have run out of the phrases");
                return false;
            }
            return true;
        }else{
            return false;
        }
    }


    //main function:
    public static void main(String [] args) {
        WheelOfFortuneUserGame hangmanUserGame = new WheelOfFortuneUserGame();
        AllGamesRecord record = hangmanUserGame.playAll();
//        for(GameRecord gr : record.highGameList(3)){
//            System.out.println(gr);
//        }
        System.out.println(record);
    }

}
