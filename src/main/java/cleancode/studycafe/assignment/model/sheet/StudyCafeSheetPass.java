package cleancode.studycafe.assignment.model.sheet;

import cleancode.studycafe.assignment.model.StudyCafePassType;
import cleancode.studycafe.assignment.model.locker.StudyCafeLockerPass;

public class StudyCafeSheetPass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSheetPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSheetPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSheetPass(passType, duration, price, discountRate);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscountedPrice() {
        return (int) (price * discountRate);
    }

    public int getTotalPrice() {
        return price - getDiscountedPrice();
    }

    public boolean isLockerAvailable(StudyCafeLockerPass lockerPass) {
        return lockerPass.equalPassType(passType) && lockerPass.equalDuration(duration);
    }

    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }

}
