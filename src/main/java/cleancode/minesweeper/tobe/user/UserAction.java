package cleancode.minesweeper.tobe.user;

public enum UserAction {
    OPEN("셀 열기"),
    FLAG("깃발 꽂기"),
    UNKNOWN("알 수 없는 동작");

    private final String description;

    UserAction(String description) {
        this.description = description;
    }
}
