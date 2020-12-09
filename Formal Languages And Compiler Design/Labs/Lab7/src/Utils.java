import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Utils {

    static List<String> split(String s) {
        return Arrays.asList(s.strip().split(" "));
    }

    static  List<String> trim(List<String> list) {
        List<String> trimmed = new ArrayList<>();
        for (String s : list) {
            trimmed.add(s.trim());
        }
        return trimmed;
    }

}


