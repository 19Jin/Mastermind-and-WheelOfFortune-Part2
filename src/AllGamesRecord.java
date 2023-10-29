import java.util.ArrayList;
import java.util.Collections;

public class AllGamesRecord {

    private ArrayList<GameRecord> listGameRecord;

    //Constructor:
    public AllGamesRecord(){
        listGameRecord = new ArrayList<>();
    }

    /**
     * add(GameRecord)-- adds a GameRecord to the AllGamesRecord
     * @param gr
     */
    public void add(GameRecord gr){
        listGameRecord.add(gr);
    }

    /**
     * average()-- returns the average score for all games added to the record
     * @return
     */
    public int average(){
        //to do:
        int sum = 0;

        for(GameRecord gr : listGameRecord){
            sum += gr.getScore();
        }

        return sum/listGameRecord.size();
    }

    /**
     * average(playerId) - returns the average score for all games of a particular player
     */
    public int average(String playerId){
        //to do
        int totleScorePlayer = 0;
        int num = 0;
        for(GameRecord gr : listGameRecord){
            if(gr.getPlayerId() == playerId){
                num++;
                totleScorePlayer += gr.getScore();
            }
        }
        return totleScorePlayer/num;
    }

    /**
     * highGameList(n)-- returns a sorted list of the top n scores including player and score.
     * This method should use the Collections class to sort the game instances.
     */
    //Q: "to sort the game instances" does it mean that sort the GameRecords in the list
    //Q: return a list of GameRecord or return: "playID1" 23, "playID2" 45, "playID3" 12,
    public ArrayList<GameRecord> highGameList(int n){

        ArrayList<GameRecord> topNList = new ArrayList<>();
        Collections.sort(listGameRecord);
        for(GameRecord gr : listGameRecord){
            if(n <= 0){
                break;
            }
            topNList.add(gr);
            n--;
        }

        return topNList;
    }

    /**
     * highGameList(playerId, n)-- returns a sorted list of the top n scores for the specified player.
     * This method should use the Collections class to sort the game instances.
     */
    public ArrayList<GameRecord> highGameList(String playerId, int n){

        ArrayList<GameRecord> topNListPlayer = new ArrayList<>();
        ArrayList<GameRecord> tempListPlayer = new ArrayList<>();
        for(GameRecord gr : listGameRecord){
            if(gr.getPlayerId() == playerId){
                tempListPlayer.add(gr);
            }
        }
        Collections.sort(tempListPlayer);
        for(GameRecord gr : tempListPlayer){
            if(n <= 0){
                break;
            }
            topNListPlayer.add(gr);
            n--;
        }

        return topNListPlayer;
    }

    /**
     * I want to print the information in the listGameRecord
     * @return
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(GameRecord gr : listGameRecord){
            sb.append("Player Id is: " + gr.getPlayerId() + "," + " " + "Player Score is: " + gr.getScore() + "\n");
        }
        return "The Game Record is: " + "\n" + sb;
    }

}
