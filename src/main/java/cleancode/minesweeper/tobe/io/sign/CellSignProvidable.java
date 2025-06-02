package cleancode.minesweeper.tobe.io.sign;

import cleancode.minesweeper.tobe.cell.CellSnapshot;

public interface CellSignProvidable {

    String provide(CellSnapshot cellSnapshot);

    boolean supports(CellSnapshot cellSnapshot);
}
