import java.util.ArrayList;
import java.util.Random;

public class WheelOfFortuneAIOrder implements WheelOfFortunePlayer{
    private String alphabetList;
    private int idx;

    @Override
    public char nextGuess() {
        char c = alphabetList.charAt(idx);
        idx++;
        return c;
    }

    @Override
    public String playerId() {
        return "Robot Order-Order ";
    }

    @Override
    public void reset() {
        idx = 0;
    }

    public WheelOfFortuneAIOrder(){
        alphabetList = "abcdefghijklmnopqrstuvwxyz";
    }
}
