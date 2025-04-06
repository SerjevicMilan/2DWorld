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
    Seed seedMenu;
    World world;
    TERenderer render = new TERenderer();

    //used to check witch key was pressed
    InputHandler inputHandler = new InputHandler();

    //set game state to main menu
    public Game() {
        state = GameState.MAIN_MENU;
        render.initialize(width, height);
    }

    //render game based on current state
    public void runGame() {
        init();
        while(state != GameState.GAME_OVER) {
            updateState();
            renderGame();
            StdDraw.pause(30);//draw every 30 milliseconds
        }
        System.exit(0);
    }

    //init world and menu
    private void init() {
        seedMenu = new Seed(width, height);
        renderMenu = new RenderMenu(new Menu(width, height), render);
        renderSeed = new RenderSeed(seedMenu, render);

    }

    //update state based on key inputs
    private void updateState() {
        if (state == GameState.MAIN_MENU && inputHandler.isKeyPressed('N')) {
            state = GameState.SEED_INPUT;

        }
        if (state == GameState.MAIN_MENU && inputHandler.isKeyPressed('L')) {
            world = new Load().loadGame();
            renderWorld = new RendererWorld(world, render);
            state = GameState.WORLD_RENDER;
        }

        if (state == GameState.SEED_INPUT) {
            updateSeed();
            prepareWorldForRendering();
            if (inputHandler.isKeyPressed('S')) {
                state = GameState.WORLD_RENDER;
            }
        }
        if (inputHandler.isKeyPressed('Q')) {
            state = GameState.GAME_OVER;
            new Save().saveGame(world);
        }
        if (state == GameState.WORLD_RENDER) {
            updateWorldState();
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

    //generate world based on current seed
    private void prepareWorldForRendering() {
        seed = seedMenu.getSeedInt();
        world = new World(height, width, seed, density);
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
                world.updateState(c);
            }
        }
    }
}
