package cleancode.minesweeper.tobe.position;

import java.util.Objects;

public class CellPosition {

    private final int rowIndex;
    private final int colIndex;

    private CellPosition(int rowIndex, int colIndex) {
        if (rowIndex < 0 || colIndex < 0) {
            throw new IllegalArgumentException("올바르지 않은 좌표입니다.");
        }
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public static CellPosition of(int rowIndex, int colIndex) {
        return new CellPosition(rowIndex, colIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CellPosition that = (CellPosition) o;
        return rowIndex == that.rowIndex && colIndex == that.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }

    public boolean isRowIndexMoreThanOrEqualTo(int rowIndex) {
        return this.rowIndex >= rowIndex;
    }

    public boolean isColIndexMoreThanOrEqualTo(int colIndex) {
        return this.colIndex >= colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public CellPosition calculatePositionBy(RelativePositon relativePosition) {
        if (this.canCalculatePositionBy(relativePosition)) {
            return CellPosition.of(rowIndex + relativePosition.getDeltaRow(),
                colIndex + relativePosition.getDeltaCol());
        }
        throw new IllegalArgumentException("움직일 수 있는 좌표가 아닙니다.");
    }

    public boolean canCalculatePositionBy(RelativePositon relativePosition) {
        return rowIndex + relativePosition.getDeltaRow() >= 0 &&
            colIndex + relativePosition.getDeltaCol() >= 0;
    }

    public boolean isRowIndexLessThan(int rowSize) {
        return rowIndex < rowSize;
    }

    public boolean isColIndexLessThan(int colSize) {
        return colIndex < colSize;
    }
}
