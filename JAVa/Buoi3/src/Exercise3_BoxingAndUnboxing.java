public class Exercise3_BoxingAndUnboxing {
    public void question1(){
        int a = 5000;
        System.out.printf("%.2f", (float) a);
    }

    public void question2(){
        String a = "1234567";
        System.out.println(Integer.parseInt(a));
    }

    public void question3(){
        Integer a = Integer.valueOf("1234567");
        System.out.println((int)a);
    }

    
}
