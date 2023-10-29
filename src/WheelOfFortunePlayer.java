public interface WheelOfFortunePlayer {
    /**
     * define the folllowing methods needed by all WheelOfFortunePlayers:
     * - char nextGuess()  — get the next guess from the player
     * - String playerId()  — an id for the player
     * - void reset() — reset the player to start a new game
     */

    // Get the next guess from the player
    char nextGuess();

    // Get an ID for the player
    String playerId();

    // Reset the player to start a new game
    void reset();

}
