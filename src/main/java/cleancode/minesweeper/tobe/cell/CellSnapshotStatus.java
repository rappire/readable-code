package cleancode.minesweeper.tobe.cell;

public enum CellSnapshotStatus {
    EMPTY("빈 칸"),
    FLAG("깃발 꽂힘"),
    LAND_MINE("지뢰"),
    NUMBER("숫자"),
    UNCHECKED("선택되지 않음");

    private final String description;

    CellSnapshotStatus(String description) {
        this.description = description;
    }
}
