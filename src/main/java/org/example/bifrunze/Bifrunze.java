package org.example.bifrunze;

import org.example.Main;

import java.io.*;
import java.util.*;

public class Bifrunze {
    public static void main(String[] args){
        File file = new File(Main.path +
                "bifrunze/bifrunze.in");

        try(Scanner scanner = new Scanner(file)){
            FileWriter writer = new FileWriter(Main.path +
                    "bifrunze/bifrunze.out");

            String[] lines = convertFileToStringArr(scanner);

            int rootIndex = findRootIndex(lines);
            String rootLine = lines[rootIndex];

            Node binaryTree =  createBinaryTree(rootLine, lines);

            inOrder(binaryTree, writer);
            postOrder(binaryTree,writer);
            preOrder(binaryTree,writer);

            writer.close();
        }catch (FileNotFoundException err){
            throw new RuntimeException("File not found");
        } catch (IOException e) {
            throw new RuntimeException("Io error");
        }
    }

    public static void inOrder(Node node, FileWriter writer) throws IOException {
        if(node == null){
            return;
        }
        inOrder(node.getLeft(), writer);

        writer.write(node.getValue()+" ");

        inOrder(node.getRight(), writer);
    }

    public static void preOrder(Node node, FileWriter writer) throws IOException {
        if(node == null){
            return;
        }

        writer.write(node.getValue()+" ");

        preOrder(node.getLeft(), writer);
        preOrder(node.getRight(), writer);
    }

    public static void postOrder(Node node, FileWriter writer) throws IOException {
        if(node == null){
            return;
        }
        postOrder(node.getLeft(), writer);
        postOrder(node.getRight(), writer);

        writer.write(node.getValue()+" ");
    }

    public static String[] convertFileToStringArr(Scanner scanner){
        short numberOfNodes = scanner.nextShort();

        String[] lines = new String[numberOfNodes];

        scanner.nextLine();

        for(int i = 0; i < numberOfNodes; i++){
            lines[i] =scanner.nextLine();
        }

        return lines;
    }
    public static int findRootIndex(String[] lines){
        List<Integer> indexes = new ArrayList<>();

        // Populate indexes with unique index values
        for(String line: lines){
            String[] value =  line.split(" ");

            int leftIndex = Integer.parseInt(value[1]);
            int rightIndex = Integer.parseInt(value[2]);

            if(!indexes.contains(leftIndex)){
                indexes.add(leftIndex);
            }

            if(!indexes.contains(rightIndex)){
                indexes.add(rightIndex);
            }
        }
        int sum =0;

        for(int ind:indexes){
            sum+=ind;
        }

        // Subtract founded index sum from suma Gauss - 1
        return lines.length * (lines.length + 1) / 2 - sum - 1;
    }
    public static Node createBinaryTree(String value, String[] nodes){
        Node node = new Node();

        String[] vals = value.split(" ");
        node.setValue(vals[0]);

        int leftIndex = Integer.parseInt(vals[1]);
        int rightIndex = Integer.parseInt(vals[2]);

        if(leftIndex == 0){
            node.setLeft(null);
        }else{
            node.setLeft(createBinaryTree(nodes[leftIndex - 1], nodes));
        }

        if (rightIndex == 0){
            node.setRight(null);
        }else{
            node.setRight(createBinaryTree(nodes[rightIndex - 1], nodes));
        }

        return node;
    }

}
