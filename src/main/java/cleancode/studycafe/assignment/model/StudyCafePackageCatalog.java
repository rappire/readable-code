package cleancode.studycafe.assignment.model;

import cleancode.studycafe.assignment.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.assignment.model.sheet.StudyCafeSheetPass;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudyCafePackageCatalog {

    private final Map<StudyCafePassType, List<StudyCafePassPackage>> studyCafePassPackages;

    private StudyCafePackageCatalog(Map<StudyCafePassType, List<StudyCafePassPackage>> studyCafePassPackages) {
        this.studyCafePassPackages = studyCafePassPackages;
    }

    public static StudyCafePackageCatalog initialize(List<StudyCafeSheetPass> studyCafeSheetPasses,
        List<StudyCafeLockerPass> studyCafeLockerPasses) {

        Map<StudyCafePassType, List<StudyCafePassPackage>> studyCafePassPackages = studyCafeSheetPasses.stream()
            .map(studyCafeSheetPass -> StudyCafePassPackage.createPackage(
                studyCafeSheetPass,
                studyCafeLockerPasses
            ))
            .collect(
                Collectors.groupingBy(
                    StudyCafePassPackage::getStudyCafeSheetPass
                )
            );
        return new StudyCafePackageCatalog(studyCafePassPackages);
    }

    public List<StudyCafePassPackage> getPackagesByType(StudyCafePassType passType) {
        return List.copyOf(studyCafePassPackages.get(passType));
    }
}
