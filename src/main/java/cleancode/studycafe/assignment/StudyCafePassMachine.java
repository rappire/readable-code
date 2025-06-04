package cleancode.studycafe.assignment;

import cleancode.studycafe.assignment.exception.AppException;
import cleancode.studycafe.assignment.io.InputHandler;
import cleancode.studycafe.assignment.io.OutputHandler;
import cleancode.studycafe.assignment.io.StudyCafeFileHandler;
import cleancode.studycafe.assignment.model.StudyCafePackageCatalog;
import cleancode.studycafe.assignment.model.StudyCafePassPackage;
import cleancode.studycafe.assignment.model.StudyCafePassType;
import cleancode.studycafe.assignment.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.sheet.StudyCafeSheetPass;
import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
    private StudyCafePackageCatalog studyCafePackageCatalog;

    public void run() {
        try {
            loadCatalog();
            greetUser();
            StudyCafePassPackage selectedCatalog = selectPackage();
            outputHandler.showPassOrderSummary(selectedCatalog);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePassPackage selectPackage() {
        StudyCafePassPackage studyCafePassPackage = selectedPassAndGetPackage();
        if (studyCafePassPackage.isLockerAvailable()) {
            selectLockerPass(studyCafePassPackage);
        }
        return studyCafePassPackage;
    }

    private void loadCatalog() {
        List<StudyCafeSheetPass> sheetPasses = studyCafeFileHandler.readStudyCafePasses();
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

        studyCafePackageCatalog = StudyCafePackageCatalog.initialize(sheetPasses, lockerPasses);
    }

    private StudyCafePassPackage selectedPassAndGetPackage() {
        outputHandler.askPassTypeSelection();
        StudyCafePassType selectedType = inputHandler.getPassTypeSelectingUserAction();

        List<StudyCafePassPackage> candidatePackages = studyCafePackageCatalog.getPackagesByType(selectedType);
        outputHandler.showPassListForSelection(candidatePackages);
        return inputHandler.getSelectPass(candidatePackages);
    }

    private void selectLockerPass(StudyCafePassPackage selectedPackage) {
        outputHandler.askLockerPass(selectedPackage);
        boolean isLockerSelected = inputHandler.getLockerSelection();

        if (isLockerSelected) {
            selectedPackage.useLocker();
        }
    }

    private void greetUser() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

}
