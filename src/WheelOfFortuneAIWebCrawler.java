import java.util.List;
import java.util.Map;

public class WheelOfFortuneAIWebCrawler implements WheelOfFortunePlayer{

    private String sortedAlphabet;
    private int idx;

    @Override
    public char nextGuess() {
        char res = sortedAlphabet.charAt(idx);
        idx++;
        return res;
    }

    @Override
    public String playerId() {
        return "Robot Random-Crawler ";
    }

    @Override
    public void reset() {
        Crawler();
        idx = 0;
    }

    public void Crawler(){
        /**
         * get the frequency of alphabet for bot player based on a webpage
         * in the following game process, the bot will guess Letter based on the list
         */
        String url = "https://www.usfca.edu/arts-sciences/programs/graduate/computer-science-bridge";
        WebAnalyze analyzer = new WebAnalyze();
        List<Map.Entry<Character, Integer>> sortedLetterFrequency = analyzer.freqWebText(url);
        sortedAlphabet = "";
        //Put the results of alphabetic frequency sorting into letterTable
        for (Map.Entry<Character, Integer> entry : sortedLetterFrequency) {
            sortedAlphabet += entry.getKey();
        }
    }

    public WheelOfFortuneAIWebCrawler(){
        Crawler();
    }

}
