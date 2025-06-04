package cleancode.studycafe.assignment.io;

import cleancode.studycafe.assignment.model.StudyCafePassType;
import cleancode.studycafe.assignment.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.sheet.StudyCafeSheetPass;
import java.util.List;

public class StudyCafeFileHandler {

    public static final String STUDYCAFE_PASS_CSV = "src/main/resources/cleancode/studycafe/pass-list.csv";
    public static final String STUDYCAFE_LOCKER_PASS_CSV = "src/main/resources/cleancode/studycafe/locker.csv";

    public List<StudyCafeSheetPass> readStudyCafePasses() {
        return ExcelFileHandler.readExcelFile(STUDYCAFE_PASS_CSV, values ->
            StudyCafeSheetPass.of(
                StudyCafePassType.valueOf(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2]),
                Double.parseDouble(values[3])
            )
        );
    }

    public List<StudyCafeLockerPass> readLockerPasses() {
        return ExcelFileHandler.readExcelFile(STUDYCAFE_LOCKER_PASS_CSV, values ->
            StudyCafeLockerPass.of(
                StudyCafePassType.valueOf(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2])
            )
        );
    }
}
