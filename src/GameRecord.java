public class GameRecord implements Comparable<GameRecord>{

    private String playerId;
    private int score;

    GameRecord(String playerId, int score){
        //to do:
        this.playerId = playerId;
        this.score = score;
    }

    public String getPlayerId(){
        return playerId;
    }

    public int getScore(){
        return score;
    }

    @Override
    //sort descending based on score value
    public int compareTo(GameRecord other){
        if(this.score > other.score){
            return -1;
        }else if(this.score < other.score){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Player Id is: " + getPlayerId() + " " + "Player Score is: " + getScore() + "\n";
    }
}
