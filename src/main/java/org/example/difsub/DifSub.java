package org.example.difsub;

import org.example.Main;
import org.example.bifrunze.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class DifSub {
    public static void main(String[] args) {
        File file = new File(Main.PATH + "/difsub/difsub.in");

        try(Scanner scanner = new Scanner(file);) {
            FileWriter fileWriter = new FileWriter(Main.PATH + "/difsub" +
                    "/difsub.out");

            String[] lines = convertToList(scanner);
            int rootIndex = findRootIndex(lines);
            Node node = convertToBinaryTree(lines, rootIndex);

            int leftTreeSum = calculateSumOfNodes(node.getLeft());
            int rightTreeSum = calculateSumOfNodes(node.getRight());
            int diff = rightTreeSum - leftTreeSum;

            fileWriter.write(Integer.toString(diff));

            fileWriter.close();
        }catch (FileNotFoundException e){
            throw new RuntimeException();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    private static String[] convertToList(Scanner scanner){
        Short n = scanner.nextShort();
        scanner.nextLine();
        String[] lines = new String[n];

        for (var i =0; i< n; i++){
            lines[i]= scanner.nextLine();
        }

        return lines;
    }
    private static int findRootIndex(String[] lines){
        List<Integer> uniqueValues = new ArrayList<>();

        for(var line: lines){
          int[] nodes =
                  Stream.of(line.split(" ")).mapToInt(Integer::parseInt).toArray();
          if(!uniqueValues.contains(nodes[1])){
              uniqueValues.add(nodes[1]);
          }
          if(!uniqueValues.contains(nodes[2])){
              uniqueValues.add(nodes[2]);
          }
        }
        var uniqueValuesSum= uniqueValues.stream().reduce(0
                , Integer::sum);
        var sumaGaus = lines.length* (lines.length+1)/2;

      return sumaGaus - uniqueValuesSum - 1;
    }
    private static Node convertToBinaryTree(String[] lines, int rootIndex){
        Node node = new Node();
        int[] values =
                Stream.of(lines[rootIndex].split(" ")).mapToInt(Integer::parseInt).toArray();

        int leftIndex = values[1];
        int rightIndex = values[2];

        node.setValue(values[0]);

        if(leftIndex == 0){
            node.setLeft(null);
        }else{
            node.setLeft(convertToBinaryTree(lines, leftIndex-1));
        }
        if(rightIndex == 0){
            node.setRight(null);
        }else{
            node.setRight(convertToBinaryTree(lines,rightIndex-1));
        }

        return node;
    }
    private static int calculateSumOfNodes(Node node){

        if (node == null){
            return 0;
        }

        return node.getValue() + calculateSumOfNodes(node.getLeft()) + calculateSumOfNodes(node.getRight());
    }
}
