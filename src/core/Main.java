package core;

public class Main {
    public static void main(String[] args) {
        RendererWorld rendererWorld = new RendererWorld();
        rendererWorld.initialiseWorld(50, 80, 5);
        rendererWorld.renderFrame();
    }
}
