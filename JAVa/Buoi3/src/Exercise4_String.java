import java.util.Scanner;

import models.Group;

public class Exercise4_String {
    public void question1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a line:");
        String inputLine = sc.nextLine();
        // remove first and final space
        inputLine = inputLine.trim();
        // change all multiple consecutive space into singular space
        inputLine = inputLine.replaceAll(" +", " ");
        // count all the space and + 1 = result
        System.out.println(inputLine.length() - inputLine.replace(" ", "").length() + 1);
        sc.close();
        return;
    }

    public void question2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a line:");
        String inputLine1 = sc.nextLine();
        System.out.println("Input 2nd line:");
        String inputLine2 = sc.nextLine();
        System.out.println(inputLine1 + inputLine2);
        sc.close();
        return;
    }

    public void question3() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a name:");
        String inputLine = sc.nextLine();
        // remove first and final space
        inputLine = inputLine.trim();
        // change all multiple consecutive space into singular space
        inputLine = inputLine.replaceAll(" +", " ");

        // uppercase the start char:
        inputLine = inputLine.substring(0, 1).toUpperCase() + inputLine.substring(1);

        for (int i = 1; i < inputLine.length(); i++) {
            if (inputLine.charAt(i - 1) == ' ') {
                inputLine = inputLine.substring(0, i).toUpperCase() + inputLine.substring(i);
            }
        }
        System.out.println(inputLine);
        sc.close();
        return;
    }

    public void question4() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a name:");
        String inputLine = sc.nextLine();
        for (int i = 0; i < inputLine.length(); i++) {
            System.out.printf("Ký tự thứ %d là: %s%n", i + 1, inputLine.charAt(i) + "");
        }
        sc.close();
        return;
    }

    public void question5() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Firstname:");
        String inputLine1 = sc.nextLine();
        System.out.println("Input Lastname:");
        String inputLine2 = sc.nextLine();
        System.out.println(inputLine1.trim() + " " + inputLine2.trim());
        sc.close();
        return;
    }

    public void question6() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a name:");
        String inputLine = sc.nextLine();
        // remove first and final space
        inputLine = inputLine.trim();
        // change all multiple consecutive space into singular space
        inputLine = inputLine.replaceAll(" +", " ");

        int firstSpaceIndex = inputLine.indexOf(" ");
        int lastSpaceIndex = inputLine.lastIndexOf(" ");

        // the first name shall be the before the first appearance of space
        String firstName = inputLine.substring(0, firstSpaceIndex);
        System.out.println("THOU FIRST NAME SHALL BE: " + firstName);

        // and the last name shall be after the last appearance of space, duh!
        String lastName = (firstSpaceIndex == -1 ? "NOTHING! WHERE ART THY LAST NAME?"
                : inputLine.substring(inputLine.lastIndexOf(" ") + 1));// +1 to skip space
        System.out.printf("THOU LAST NAME SHALL BE: %s%n", lastName);
        // the rest is surname, ig, if there are any
        if (firstSpaceIndex == lastSpaceIndex) {
            System.out.println("THOU HAVETH NO SURNAME.");
        } else {
            System.out.println("THOU SURNAME SHALL BE: " + inputLine.replace(firstName, "").replace(lastName, "").trim());
        }
        sc.close();
        return;
    }

    public void question7(){
        question3();
    }

    public void question8(Group[] groups){
        System.out.println("All group contains 'Java' text: ");
        for (Group group : groups) {
            if (group.getGroupName().contains("Java")) System.out.println(group.getGroupName());
        }
    }

    public void question9(Group[] groups){
        System.out.println("All group contains 'Java' text: ");
        for (Group group : groups) {
            if (group.getGroupName().equals("Java")) System.out.println(group.getGroupName());
        }
    }

    public boolean question10(String a, String b){
        if (a.length() != b.length()) return false;
        for (int i = 0; i < a.length() ; i++) {
            if (a.charAt(i) != b.charAt(b.length() -i)) {
                return false;
            }
        }
        return true;
    }

    public int question11(String a){
        int result = 0;
        for (int i = 0; i < a.length() ; i++) {
            if (a.charAt(i) == 'a') result++;
        }
        return result;
    }

    public String question12(String inputString){
        String reverseString = "";
        for (int i = 0; i < inputString.length(); i++) {
            reverseString += inputString.charAt(i) +"";
        }
        return reverseString;
    }
    public boolean question13(String inputString){
        if (inputString == null) return false;
        String removedSpaceString =  inputString.replaceAll("[0-9]", "");
        if (inputString.length() - removedSpaceString.length()>0) return false;
        return true;
    }
    public String question14(String inputString){
        String resultString = inputString.replaceAll("e", "*");
        return resultString;
    }
    public String question15(String inputString){
        String resultString = "";
        inputString = inputString.trim().replaceAll(" +", " ");
        for (int i = inputString.length() - 1; i >= 0 ; i--) {
            if (inputString.charAt(i) == ' '){
                resultString += inputString.substring(i+1);
            }
        }
        return resultString;
    }
    public String[] question16(String inputString,int n){
        if (inputString.length() % n != 0) {
            System.out.println("KO");
            return null;
        }
        String[] collections = new String[inputString.length()/n];
        for (int i = 0; i < inputString.length()/n - 1; i++) {
            collections[i] = inputString.substring(i*n, (i+1)*n);
        }
        return collections;
    }
}
