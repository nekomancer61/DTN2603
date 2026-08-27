import java.util.Random;
import java.util.Scanner;

public class Exercise1_DataTypeCasting {
    
    public Exercise1_DataTypeCasting() {
    }
    public void question1(){
        float acc1 = (float) 5240.5;
        float acc2 = (float) 10970.055;
        System.out.println((int) acc1);
        System.out.println((int) acc2);
    }
    public String question2(){
        Random random = new Random();
        String randomNumber = String.format("%05d", random.nextInt(100000));
        return randomNumber;
    }
    public void question3(){
        String randomNumber = question2();
        System.out.println(randomNumber.substring(randomNumber.length()-2));
    }
    public void question4(){
        int a;
        int b;
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("a: ");
            if (sc.hasNextInt()){
                a = sc.nextInt();
                if (sc.hasNextLine()) sc.nextLine();
                break;
            }
        }
        while (true) {
            System.out.println("b: ");
            if (sc.hasNextInt()){
                b = sc.nextInt();
                if (b == 0){
                    continue;
                }
                if (sc.hasNextLine()) sc.nextLine();
                break;
            }
        }
        System.out.println(a/b);
        sc.close();
        return;
    }
}
