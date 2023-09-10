package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileManipulation {

    File file;
    StringBuilder text;
    String rightNumbers;
    String wordsInfo;
    public void createFile(String name) {
        file = new File(name);
    }

    public void writeFile(String text) {
        try (FileWriter writer = new FileWriter(file, true))
        {
            writer.write(text + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private <E> void writeJsonFile(List<E> list, String fileName) {
        new File (fileName).delete();
        try (FileWriter writer = new FileWriter(new File (fileName), true))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for(int i = 0; i < list.size(); i++) {
                if(i == 0) {
                    writer.write("[\n");
                }
                String json = gson.toJson(list.get(i));
                System.out.println(json);
                writer.write(json);
                if(i == list.size() - 1) {
                    writer.write("\n]");
                } else {
                    writer.write(",\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void writeWordInfo(String word, int amount) {
        try (FileWriter writer = new FileWriter(file, true))
        {
            writer.write(word + " " + amount + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private <K, V> void createStringWithAmountOfWords(Map<K, V> amountOfWords, int maxAmount) {
        Set<K> words = amountOfWords.keySet();
        StringBuilder result = new StringBuilder();
        for(int i = maxAmount; i >= 0; i--) {
            if(amountOfWords.containsValue(i)) {
                /*for(K word : amountOfWords.keySet()) {
                    if(word == amountOfWords.) {

                    }
                }*/
                //writeWordInfo((String) amountOfWords.get(getKey(amountOfWords, i)), i);
                result.append(getKey(amountOfWords, i));
                result.append(" ");
                result.append(i);
                result.append("\n");
            }
        }
        this.wordsInfo = result.toString();
    }

    private <K, V> K getKey(Map<K, V> map, int value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void readFile() {
        try (FileReader reader = new FileReader(file)) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                //StringBuilder string = new StringBuilder();
                text = new StringBuilder();
                text.append(buf);
                //findRightNumbers(string.toString());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFile() {
        file.delete();
    }

    public void findRightNumbers() {
        readFile();
        String[] nums = text.toString().split("\n");
        StringBuilder rightNumbers = new StringBuilder();
        for(String num : nums) {
            if(num.matches("\\d{3}+-+\\d{3}+-+\\d{4}") || num.matches("\\(+\\d{3}+\\)+ +\\d{3}+-+\\d{4}")) {
                rightNumbers.append(num);
                rightNumbers.append("\n");
            }
        }
        this.rightNumbers = rightNumbers.toString();
    }

    public void findUsers() {
        readFile();
        String[] usersInfo = text.toString().split("\n");
        List<User> users = new ArrayList<User>();
        for(String userInfo : usersInfo) {
            if(userInfo.matches("[a-zA-Z]+ +\\d+")) {
                String[] words = userInfo.split(" ");
                User user = new User(words[0], Integer.valueOf(words[1]));
                users.add(user);
            }
        }
        writeJsonFile(users, "user.json");
    }

    public void findWords() {
        readFile();
        String[] words = text.toString().split("\\s+");
        /*List asList = Arrays.asList(array);
Set<String> mySet = new HashSet<String>(asList);

for(String s: mySet){
 System.out.println(s + " " + Collections.frequency(asList,s));
}*/
        List listOfWords = Arrays.asList(words);
        //Set<String> setOfWords = new HashSet<String>(listOfWords);
        Map<String, Integer> amountOfWords = new HashMap<>();
        int maxAmount = 0;
        for(String word : words) {
            if(!amountOfWords.containsKey(word)) {
                amountOfWords.put(word, Collections.frequency(listOfWords, word));
                if(amountOfWords.get(word) > maxAmount) {
                    maxAmount = amountOfWords.get(word);
                }
            }
        }
        createStringWithAmountOfWords(amountOfWords, maxAmount);
    }

    public String getRightNumbers() {
        return rightNumbers;
    }

    public String getWordsInfo() {
        return wordsInfo;
    }
}
