package core;

//stores x and y coordinates and can calculate distance between two coordinates.
public class Coordinate {
    int x;
    int y;

    //assign x and y
    public Coordinate(int x, int y) {
        checkIfCoordinatesAreValid(x, y);

        this.x = x;
        this.y = y;
    }

    //calc distance between two coordinates
    public int distanceBettwen(Coordinate otherCordinate) {
        int a;
        int b;

        //check if they are on same axis
        if (this.x == otherCordinate.x) { return Math.abs(this.y - otherCordinate.y); }
        if (this.y == otherCordinate.y) { return Math.abs(this.x - otherCordinate.x); }

        //get difference between two values on same axis
        a = Math.abs(this.x - otherCordinate.x);
        b = Math.abs(this.y - otherCordinate.y);
        return (int) Math.round(Math.hypot(a, b));//Pythagorean Calculation
    }

    //Change coordinates stored.
    public Coordinate changeCoordinates(int x, int y) {
        checkIfCoordinatesAreValid(x, y);

        this.x = x;
        this.y = y;

        return this;
    }

    //If coordinates negative throw error.
    public void checkIfCoordinatesAreValid(int x, int y) {
        if (x < 0 || y <0) {
            throw new IllegalArgumentException("Cordinates can't be negative");
        }
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) { return true; }
        if (!(obj instanceof Coordinate)) { return false; }

        Coordinate o = (Coordinate) obj;
        return x == o.x && y == o.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}