package cleancode.studycafe.assignment;

import cleancode.studycafe.assignment.exception.AppException;
import cleancode.studycafe.assignment.io.InputHandler;
import cleancode.studycafe.assignment.io.OutputHandler;
import cleancode.studycafe.assignment.io.StudyCafeFileHandler;
import cleancode.studycafe.assignment.model.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.StudyCafePass;
import cleancode.studycafe.assignment.model.StudyCafePassType;
import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            showInitialMessage();
            StudyCafePass selectedPass = selectedPass();
            Optional<StudyCafeLockerPass> lockerPass = selectLockerPass(selectedPass);
            lockerPass.ifPresentOrElse(
                selectedLockerPass -> outputHandler.showPassOrderSummary(selectedPass, selectedLockerPass),
                () -> outputHandler.showPassOrderSummary(selectedPass));

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectedPass() {
        outputHandler.askPassTypeSelection();
        StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

        List<StudyCafePass> passCandidate = getStudyCafePassCandidateBy(studyCafePassType);

        outputHandler.showPassListForSelection(passCandidate);
        return inputHandler.getSelectPass(passCandidate);
    }

    private List<StudyCafePass> getStudyCafePassCandidateBy(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        return studyCafePasses.stream().filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
            .toList();
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {
        StudyCafeLockerPass lockerPassCandidate = getLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate != null) {
            outputHandler.askLockerPass(lockerPassCandidate);
            boolean isLockerSelected = inputHandler.getLockerSelection();

            if (isLockerSelected) {
                return Optional.of(lockerPassCandidate);
            }
        }

        return Optional.empty();
    }

    private StudyCafeLockerPass getLockerPassCandidateBy(StudyCafePass studyCafePass) {
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
        return lockerPasses.stream().filter(option -> option.getPassType() == studyCafePass.getPassType()
            && option.getDuration() == studyCafePass.getDuration()).findFirst().orElse(null);
    }

    private void showInitialMessage() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

}
