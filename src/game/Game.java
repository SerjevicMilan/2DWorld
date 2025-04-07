package game;

import core.World;
import edu.princeton.cs.algs4.StdDraw;
import loading.Load;
import loading.Save;
import menu.Menu;
import render.GRender;
import render.RenderMenu;
import render.RenderSeed;
import render.RendererWorld;
import seed.Seed;
import tileengine.TERenderer;

//running game, listens for input  and render games based on it
public class Game {
    //keys responsible for movement
    private static final char KEY_NEW_GAME = 'N';
    private static final char KEY_LOAD_GAME = 'L';
    private static final char KEY_QUIT = 'Q';
    private static final char KEY_START_GAME = 'S';

    //default game size
    private int width = 80;
    private int height = 50;

    //seed for pseudo random and density world generation
    private int seed = 1;//default seed
    private int density = 1;

    //controls game flow
    private GameState state ;

    //render variables
    GRender renderMenu;
    GRender renderWorld;
    GRender renderSeed;

    //Seed menu and world
    Seed seedMenu;
    WorldState world;

    //render engine
    TERenderer render = new TERenderer();

    //used to check witch key was pressed
    InputHandler inputHandler = new InputHandler();

    //set game state to main menu and initialise render and menu
    public Game() {
        state = GameState.MAIN_MENU;
        render.initialize(width, height);
        initMenu();
    }

    //render game based on current state
    public void runGame() {
        while(state != GameState.GAME_OVER) {
            updateState();
            renderGame();
            StdDraw.pause(30);//draw every 30 milliseconds
        }
        System.exit(0);
    }

    //update state based on key inputs
    private void updateState() {
        if (state == GameState.MAIN_MENU ) {
            handleMainMenuInput();
        }

        if (state == GameState.SEED_INPUT) {
            handleSeedInput();
        }

        if (state == GameState.WORLD_RENDER) {
            updateWorldState();
        }

        if (isQuitPressed()) {
            handleQuit();
        }
    }

    //render based on state
    private void renderGame() {
        if (state == GameState.MAIN_MENU) {
            renderMenu.render();
        }
        if (state == GameState.SEED_INPUT) {
            renderSeed.render();
        }
        if (state == GameState.WORLD_RENDER) {
            renderWorld.render();
        }
    }

    //init seed and menu
    private void initMenu() {
        seedMenu = new Seed(width, height);
        renderMenu = new RenderMenu(new Menu(width, height), render);
        renderSeed = new RenderSeed(seedMenu, render);

    }

    //generate world based on current seed
    private void prepareWorldForRendering() {
        seed = seedMenu.getSeedInt();
        world = new WorldState();
        world.generateWorld(height, width, seed, density);
        renderWorld = new RendererWorld(world, render);
    }

    //check if number form 0 to 9 is pressed and update current seed number
    private void updateSeed() {
        for (int i = 0; i < 10; i++) {
            if (inputHandler.isKeyPressed((char)('0' + i))) {
                seedMenu.changeNumber((char)('0' + i));
            }
        }
    }

    //if W,S,A or D pressed passe it to world to update state
    private void updateWorldState() {
        for (char c : new char[] {'W', 'A', 'S', 'D'}) {
            if (inputHandler.isKeyPressed(c)) {
                world.updatePlayerPosition(c);
            }
        }
    }

    //change state depending on key pressed in menu
    private void handleMainMenuInput() {
        if (inputHandler.isKeyPressed(KEY_NEW_GAME)) {
            state = GameState.SEED_INPUT;
        } else if (inputHandler.isKeyPressed(KEY_LOAD_GAME)) {//load world state, initialise new render and change game state
            world = new Load().loadGame();
            renderWorld = new RendererWorld(world, render);
            state = GameState.WORLD_RENDER;
        }
    }

    //handle input, update world and render. If 'S' is pressed change game state to WORLD_RENDER
    private void handleSeedInput() {
        updateSeed();
        prepareWorldForRendering();
        if (isStartGamePressed()) {
            state = GameState.WORLD_RENDER;
        }
    }

    //if key pressed is s return true
    private boolean isStartGamePressed() {
        return inputHandler.isKeyPressed(KEY_START_GAME);
    }

    //if key pressed is q return true
    private boolean isQuitPressed() {
        return inputHandler.isKeyPressed(KEY_QUIT);
    }

    //Change game state to GAME_OVER and save if game was running
    private void handleQuit() {
        state = GameState.GAME_OVER;
        if (world != null) {//check if world was initialised
            new Save().saveGame(world);
        }
    }
}
