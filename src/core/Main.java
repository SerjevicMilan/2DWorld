package core;

public class Main {
    public static void main(String[] args) {
        World world = new World(50, 80, 1, 1.0 , 2, 10);
        RendererWorld rendererWorld = new RendererWorld(world);
        rendererWorld.initialiseWorld();
        rendererWorld.renderFrame();
    }
}
