package org.example;

import java.util.Scanner;
public class Main {
    public static final String path = "./src/main/java/org/example/";
    public static void main(String[] args) {
        int initialNumOfHeads= 6;
       Scanner scanner = new Scanner(System.in);

       int daysCount = scanner.nextInt();

       System.out.println(cutHead(daysCount, initialNumOfHeads));
    }

    public static int cutHead(int days, int headCount){
        if(days == 0){
            return headCount;
        }
        if(days == 1){
            return --headCount;
        }

        return cutHead(days - 1, headCount + 5);
    }
}