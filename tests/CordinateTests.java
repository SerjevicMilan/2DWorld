import core.Coordinate;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static java.beans.Beans.isInstanceOf;
import static org.junit.Assert.assertThrows;
//import static org.assertj.core.api.Assertions.assertThat;


public class CordinateTests {

    @Test
    public void basicTests() {
        Coordinate pos1 = new Coordinate(2, 2);
        Coordinate pos2 = new Coordinate(4, 4);

        //uses Pythagorean Calculation if not on same axis
        assertThat(pos1.distanceBettwen(pos2)).isEqualTo(3);

        //If on same axis just calc difference between opposite axis
        pos2.changeCoordinates(4, 2);
        assertThat(pos1.distanceBettwen(pos2)).isEqualTo(2);

        pos2.changeCoordinates(2, 4);
        assertThat(pos1.distanceBettwen(pos2)).isEqualTo(2);

        //test for bad input(negative coordinates)
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            pos1.changeCoordinates(-1, -1);
        });

        assertThat(e).hasMessageThat().contains("Cordinates can't be negative");

        e = assertThrows(IllegalArgumentException.class, () -> {
            new Coordinate(-1, -1);
        });

        assertThat(e).hasMessageThat().contains("Cordinates can't be negative");

        //test equals
        pos2.changeCoordinates(2, 2);
        assertThat(pos1.equals(pos2)).isTrue();

        pos2.changeCoordinates(2, 1);
        assertThat(pos1.equals(pos2)).isFalse();

        //test toString
        assertThat(pos2.toString()).isEqualTo("(2,1)");
    }
}
