package Man;

import java.util.List;

public class spiski2 {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        int x1 = list.get(0);
        System.out.println("x1=" + x1);
        System.out.println("size = " + list.size());
        System.out.println("содержит 2?" + list.contains(2));
        System.out.println(list);
    }
}
