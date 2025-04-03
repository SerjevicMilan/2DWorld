package game;

//running game, listens for input  and render games based on it
public class Game {
    //default game size
    private int width = 80;
    private int height = 50;

    //seed for pseudo random and density world generation
    private int seed;
    private int density = 1;

    //controls game flow
    private GameState state;

    InputHandler inputHandler = new InputHandler();

    //set game state to main menu
    public Game() {
        state = GameState.MAIN_MENU;
    }

    //render game based on current state
    public void runGame() {
        while(state != GameState.GAME_OVER) {
            updateState();
            renderGame();
        }
    }

    //update state based on key inputs
    private void updateState() {
        if (state == GameState.MAIN_MENU && inputHandler.isKeyPressed('N')) {
            state = GameState.SEED_INPUT;
        }
        if (state == GameState.MAIN_MENU && inputHandler.isKeyPressed('L')) {
            state = GameState.LOAD_GAME;
        }
        if (state == GameState.SEED_INPUT && inputHandler.isKeyPressed('S')) {
            state = GameState.WORLD_RENDER;
        }
        if (inputHandler.isKeyPressed('Q')) {
            state = GameState.GAME_OVER;
        }
    }

    //render based on state
    private void renderGame() {
        if (state == GameState.SEED_INPUT) {
            //renderSeed
        }
        if (state == GameState.LOAD_GAME) {
            //render prev save
        }
        if (state == GameState.WORLD_RENDER) {
            //render world
        }
    }
}
