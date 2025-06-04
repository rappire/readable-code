package cleancode.studycafe.assignment.io;

import cleancode.studycafe.assignment.model.StudyCafePassPackage;
import cleancode.studycafe.assignment.model.StudyCafePassType;
import cleancode.studycafe.assignment.exception.AppException;
import cleancode.studycafe.assignment.model.sheet.StudyCafeSheetPass;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    public StudyCafePassPackage getSelectPass(List<StudyCafePassPackage> packages) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return packages.get(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
