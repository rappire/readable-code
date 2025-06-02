package cleancode.minesweeper.tobe.position;

import java.util.List;
import java.util.Objects;

public class RelativePositon {

    public static final List<RelativePositon> SURROUNDED_POSITIONS = List.of(
        RelativePositon.of(-1, -1),
        RelativePositon.of(-1, 0),
        RelativePositon.of(-1, 1),
        RelativePositon.of(0, -1),
        RelativePositon.of(0, 1),
        RelativePositon.of(1, -1),
        RelativePositon.of(1, 0),
        RelativePositon.of(1, 1)
    );

    private final int deltaRow;
    private final int deltaCol;

    private RelativePositon(int deltaRow, int deltaCol) {
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    public static RelativePositon of(int deltaRow, int deltaCol) {
        return new RelativePositon(deltaRow, deltaCol);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RelativePositon that = (RelativePositon) o;
        return deltaRow == that.deltaRow && deltaCol == that.deltaCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deltaRow, deltaCol);
    }

    public int getDeltaRow() {
        return deltaRow;
    }

    public int getDeltaCol() {
        return deltaCol;
    }
}
