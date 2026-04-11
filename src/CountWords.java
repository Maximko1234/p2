import java.util.HashMap;
import java.util.Map;

public class CountWords {

    public static void main(String[] args) {
        String text = "Java это очень интересный язык Java и я очень";
        var words = text.split(" ");

        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (map.containsKey(word)) {
                Integer count = map.get(word);
                map.put(word, count + 1);
            } else {
                map.put(word, 1);
            }
        }

        System.out.println(map);
    }
}

