package cleancode.studycafe.assignment.io;

import cleancode.studycafe.assignment.model.StudyCafePackageCatalog;
import cleancode.studycafe.assignment.model.StudyCafePassPackage;
import cleancode.studycafe.assignment.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.sheet.StudyCafeSheetPass;
import java.util.List;

public class OutputHandler {

    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    public void askPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    public void showPassListForSelection(List<StudyCafePassPackage> passes) {
        System.out.println();
        System.out.println("이용권 목록");
        for (int index = 0; index < passes.size(); index++) {
            StudyCafePassPackage pass = passes.get(index);
            System.out.println(String.format("%s. ", index + 1) + pass.displaySheetPass());
        }
    }

    public void askLockerPass(StudyCafePassPackage selectedPackage) {
        System.out.println();
        String askMessage = String.format(
            "사물함을 이용하시겠습니까? (%s)",
            selectedPackage.displayLockerPass()
        );

        System.out.println(askMessage);
        System.out.println("1. 예 | 2. 아니오");
    }

    public void showPassOrderSummary(StudyCafePassPackage selectedPackage) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + selectedPackage.displaySheetPass());
        if (selectedPackage.isLockerAvailable()) {
            System.out.println("사물함: " + selectedPackage.displayLockerPass());
        }

        int discountPrice = selectedPackage.getDiscountedPrice();
        if (discountPrice > 0) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }

        int totalPrice = selectedPackage.getTotalPrice();
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

}
