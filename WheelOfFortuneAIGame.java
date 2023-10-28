import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WheelOfFortuneAIGame extends WheelOfFortune {

    private ArrayList<WheelOfFortunePlayer> listAiPlayer;
    private int idx;

    /**
     * 3 constructor:
     */
    //set the WheelOfFortunePlayer to a default player: random player
    public WheelOfFortuneAIGame(){
        super.readPhrases();
        listAiPlayer = new ArrayList<>();
        WheelOfFortuneAIRandom defaultPlayer = new WheelOfFortuneAIRandom();
        listAiPlayer.add(defaultPlayer);
    }

    //allow the client to specify a single concrete WheelOfFortunePlayer
    public WheelOfFortuneAIGame(WheelOfFortunePlayer playerAI){
        super.readPhrases();
        listAiPlayer = new ArrayList<>();
        listAiPlayer.add(playerAI);
    }

    //accept a list of WheellOfFortunePlayers.
    public WheelOfFortuneAIGame(ArrayList<WheelOfFortunePlayer> playerAiList){
        super.readPhrases();
        this.listAiPlayer = playerAiList;
    }

    @Override
    public char getGuess(String previousGuesses) {
        char c = listAiPlayer.get(idx).nextGuess();
        System.out.println(c);
        return c;
    }


    @Override
    public String getUserId() {
        return listAiPlayer.get(idx).playerId() + idx;
    }

    @Override
    public int getMaxGuessNum() {
        return 26;
    }

    @Override
    public boolean playNext() {
        if(phraseList.size() != 0){
            listAiPlayer.get(idx).reset();
            return true;
        }
        return false;
    }

    @Override
    public AllGamesRecord playAll() {

        while(idx < listAiPlayer.size()-1){
            super.playAll();
            super.readPhrases();
            idx++;
        }

        return super.playAll();
    }


    //main function:
    public static void main(String [] args) {

        ArrayList<WheelOfFortunePlayer> test = new ArrayList<>();

        WheelOfFortuneAIOrder player01 = new WheelOfFortuneAIOrder();
        WheelOfFortunePlayer player02 = new WheelOfFortuneAIRandom();
        WheelOfFortunePlayer player03 = new WheelOfFortuneAIWebCrawler();

        test.add(player01);
        test.add(player02);
        test.add(player03);

        WheelOfFortuneAIGame botUserGame1 = new WheelOfFortuneAIGame(test);

        AllGamesRecord record = botUserGame1.playAll();
        System.out.println(record);

    }
}