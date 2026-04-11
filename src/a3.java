import java.util.ArrayList;
import java.util.List;

    public class a3 {
        public static void main(String[] args) {
            List<Integer> a = new ArrayList();
            a.add(1);
            a.add(2);
            a.add(-4);
            a.add(5);

            Integer max = a.get(0);
            for (int i = 0; i < a.size(); i += 1) {
                if(a.get(i)> max){
                    max = a.get(i);
            }
        }
            System.out.println("max = "+max);
        }
    }


