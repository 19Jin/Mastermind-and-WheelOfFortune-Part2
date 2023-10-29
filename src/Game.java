import java.util.ArrayList;

public abstract class Game {

    AllGamesRecord agr = new AllGamesRecord();

    /**
     * AllGamesRecord playAll()— a method that plays a set of games
     * and records and returns an AllGamesRecord object for the set.
     */
    public AllGamesRecord playAll(){

        agr.add(play());

        while(playNext()){
            agr.add(play());
        }

        return agr;

    }

    /**
     * Class Game should also define these abstract methods:
     * - GameRecord play()-- plays a game and returns a GameRecord
     * - boolean playNext() -- asks if the next game should be played
     * (this will be called even to check if the first game should be played).
     * The function should return a boolean.
     * @return
     */
    public abstract GameRecord play();
    public abstract boolean playNext();

}
