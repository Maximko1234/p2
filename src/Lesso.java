import java.time.LocalDateTime;
import java.util.Scanner;
public class Lesso {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        System.out.print("введите своё имя: ");
        String name = scanner.nextLine();


        int time = LocalDateTime.now().getHour();

        if(time <7){
            System.out.println("у вас ночь,"+name);
        }else if (time <11){
            System.out.println("у вас утро,"+name);
        }else if(time <17){
            System.out.println("у вас день,"+name);
        }else if(time <23){
            System.out.println("у вас ночь,"+name);
        }
        else {
            System.out.println("ошибка");
        }
    }
}
