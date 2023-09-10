package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class Main {
    public static void main(String[] args) {
        FileManipulation fileManipulation = new FileManipulation();
        fileManipulation.createFile("file.txt");
        fileManipulation.writeFile("987-123-4567");
        fileManipulation.writeFile("123 456 7890");
        fileManipulation.writeFile("(123) 456-7890");
        fileManipulation.writeFile("------------");
        fileManipulation.writeFile("124453453453");
        fileManipulation.writeFile("987-123-45674565");
        fileManipulation.writeFile("(123) 456-789075758");
        fileManipulation.findRightNumbers();
        System.out.println(fileManipulation.getRightNumbers());
        fileManipulation.deleteFile();

        fileManipulation.createFile("file.txt");
        fileManipulation.writeFile("name age\n" +
                "alice 21\n" +
                "ryan 30");
        fileManipulation.writeFile("124453453453");
        fileManipulation.writeFile("Andrii 20");
        fileManipulation.writeFile("Cara 29");
        fileManipulation.findUsers();

        FileManipulation fileCounter = new FileManipulation();
        fileCounter.createFile("words.txt");
        fileCounter.writeFile("the day is sunny the the\n" +
                "the sunny is is");
        fileCounter.findWords();
        System.out.println(fileCounter.getWordsInfo());
        fileCounter.deleteFile();

    }
}