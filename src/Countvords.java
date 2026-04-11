import java.util.HashMap;
import java.util.Map;

public class Countvords {

    public static void main(String[] args) {
        String text = "Java это очень интересный язык Java и я очень";
        String[] words = text.split(" ");

        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }

        System.out.println("Самое длинное слово: " + longestWord);}}
