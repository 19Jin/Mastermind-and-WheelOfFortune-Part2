import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebAnalyze {
    public List<Map.Entry<Character, Integer>> freqWebText(String url) {
        Map<Character, Integer> letterFrequency = new HashMap<>();

        try {
            URL webUrl = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(webUrl.openStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)) {
                        c = Character.toLowerCase(c);
                        letterFrequency.put(c, letterFrequency.getOrDefault(c, 0) + 1);
                    }
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Sort letter frequency results by value
        List<Map.Entry<Character, Integer>> sortedList = new ArrayList<>(letterFrequency.entrySet());
        Collections.sort(sortedList, (a, b) -> b.getValue().compareTo(a.getValue()));

        return sortedList;
    }
}
