package cleancode.studycafe.assignment.model;

import cleancode.studycafe.assignment.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.sheet.StudyCafeSheetPass;
import java.util.List;

public class StudyCafePassPackage {

    private final StudyCafeSheetPass studyCafeSheetPass;
    private final StudyCafeLockerPass studyCafeLockerPass;
    private boolean isUseLocker;

    private StudyCafePassPackage(StudyCafeSheetPass studyCafeSheetPass, StudyCafeLockerPass studyCafeLockerPass) {
        this.studyCafeSheetPass = studyCafeSheetPass;
        this.studyCafeLockerPass = studyCafeLockerPass;
    }

    public static StudyCafePassPackage createPackage(StudyCafeSheetPass studyCafeSheetPass, List<StudyCafeLockerPass> studyCafeLockerPasses) {
        StudyCafeLockerPass availableLocker = studyCafeLockerPasses.stream()
            .filter(studyCafeSheetPass::isLockerAvailable)
            .findFirst()
            .orElse(null);

        return new StudyCafePassPackage(studyCafeSheetPass, availableLocker);
    }

    public void useLocker(){
        isUseLocker = true;
    }

    public boolean isLockerAvailable() {
        return studyCafeLockerPass != null;
    }

    public StudyCafePassType getStudyCafeSheetPass() {
        return studyCafeSheetPass.getPassType();
    }

    public int getTotalPrice(){
        if (isUseLocker) {
            return studyCafeSheetPass.getTotalPrice() + studyCafeLockerPass.getPrice();
        }
        return studyCafeSheetPass.getTotalPrice();
    }

    public int getDiscountedPrice() {
        return studyCafeSheetPass.getDiscountedPrice();
    }

    public String displaySheetPass() {
        return studyCafeSheetPass.display();
    }

    public String displayLockerPass() {
        return studyCafeLockerPass != null ? studyCafeLockerPass.display() : "사물함 미선택";
    }
}
