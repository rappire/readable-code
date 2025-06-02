package cleancode.minesweeper.tobe.position;

import cleancode.minesweeper.tobe.cell.Cell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CellPositions {

    private final List<CellPosition> positions;

    private CellPositions(List<CellPosition> positions) {
        this.positions = positions;
    }

    public static CellPositions of(List<CellPosition> positions) {
        return new CellPositions(positions);
    }

    public static CellPositions from(Cell[][] board) {
        List<CellPosition> cellPositions = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            for (int colIndex = 0; colIndex < board[rowIndex].length; colIndex++) {
                cellPositions.add(CellPosition.of(rowIndex, colIndex));
            }
        }

        return of(cellPositions);
    }

    public List<CellPosition> extractRandomPositions(int count) {
        ArrayList<CellPosition> cellPositions = new ArrayList<>(positions);

        Collections.shuffle(cellPositions);
        return cellPositions.subList(0, count);
    }

    public List<CellPosition> subtract(List<CellPosition> positionListToSubtract) {
        ArrayList<CellPosition> cellPositions = new ArrayList<>(this.positions);

        CellPositions positionsToSubtract = CellPositions.of(positionListToSubtract);

        return cellPositions.stream()
            .filter(positionsToSubtract::doesNotContain)
            .toList();
    }

    private boolean doesNotContain(CellPosition position) {
        return !positions.contains(position);
    }

    public List<CellPosition> getPositions() {
        return new ArrayList<>(positions);
    }

}
