package org.example.zmeu;

import java.io.*;
import java.util.Scanner;

// #2876
public class Zmeu {
    public static void main(String[] args) throws IOException {

        File file = new File("zmeu.in");
        FileWriter output = new FileWriter("zmeu.out");

        Scanner scanner = new Scanner(file);

        long n = scanner.nextLong();
        int m = scanner.nextInt();

        long numberOfKites = n/ (m*2);
        long restOfMaterial = n % (m*2);

        output.write(Long.toString(numberOfKites));
        output.write(System.getProperty("line.separator"));
        output.write(Long.toString(m*2 - restOfMaterial));

        output.close();
        scanner.close();
    }
}
