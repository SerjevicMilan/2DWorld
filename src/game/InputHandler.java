package game;

import edu.princeton.cs.algs4.StdDraw;

import static edu.princeton.cs.algs4.StdDraw.hasNextKeyTyped;
import static edu.princeton.cs.algs4.StdDraw.nextKeyTyped;

//Listen for input
public class InputHandler {
    private char keyPressed = '?';//default unused key

    //check if key pressed matches
    public boolean isKeyPressed(char key) {
        if(StdDraw.hasNextKeyTyped()) {//if true update key
            keyPressed = Character.toUpperCase(StdDraw.nextKeyTyped());
        }
        if(keyPressed == key) {
            keyPressed = '?';//reset to default value
            return true;
        }
        return false;
    }

    public char getKeyPressed () {
        return keyPressed;
    }
}
