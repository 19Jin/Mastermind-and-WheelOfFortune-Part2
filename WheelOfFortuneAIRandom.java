import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WheelOfFortuneAIRandom implements WheelOfFortunePlayer{

    private ArrayList<Character> alphabetList; //alphabetList

    @Override
    public char nextGuess() {
        Random random = new Random();
        int index = random.nextInt(alphabetList.size());
        char c = alphabetList.get(index);
        alphabetList.remove(index);
        return c;
    }

    @Override
    public String playerId() {
        return "Robot Random-Algorithm ";
    }

    @Override
    public void reset() {
        for(int i = 97; i <= 122; i++){
            alphabetList.add((char)i);
        }
    }

    public WheelOfFortuneAIRandom(){
        alphabetList = new ArrayList<>();
        for(int i = 97; i <= 122; i++){
            alphabetList.add((char)i);
        }
    }
}
