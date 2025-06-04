package cleancode.studycafe.assignment.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

public class ExcelFileHandler {
    public static <T> List<T> readExcelFile(String filePath, Function<String[], T> mapper) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            return lines.stream()
                .map(line -> line.split(","))
                .map(mapper)
                .toList();
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
