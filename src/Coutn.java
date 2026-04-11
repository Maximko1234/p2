import java.util.HashMap;
import java.util.Map;

public class Coutn {

    public static void main(String[] args) {
        //НАЙТИ КОЛ-ВО ВХОЖДЕНИЙ СЛОВ В ТЕКСТ
        String text = "Java это очень интересны язык Java и я очень его изучаю Java";
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
    }}