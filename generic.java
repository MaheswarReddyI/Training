/*
 * copyright (c) 2022.   -  All Rights Reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited-
 * @Author - Indukuri Maheswar Reddy ( indukurimr )
 * @version - java 11
 */


package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/** pipeCommand Class is created to mimic the functions of pipe command
 * with cat, ls, head, tail, grep, sort and wc commands
 */

public class generic {

    // array lists were created for processing input and outputs
    public static ArrayList<String> lines = new ArrayList<>();
    public static ArrayList<String> result = new ArrayList<>();
    public static ArrayList<String> head = new ArrayList<>();

    /**
     * Method of cat with a string input returns the content
     * of given file in an arrayList
     *
     * @param f   - String type for storing the name/path of the file
     * @return    - it returns the String ArrayList of file content
     */
    public static ArrayList<String> cat(String f) {
        try {
            File file = new File(f);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {

                // every readerLine is adding to the ArrayList
                lines.add(line);
            }
        } catch (IOException ignored) {
        }
        return lines;
    }
    /**
     * Method 'ls' gives the List of files present in the given input directory and the
     * result is returned through an ArrayList
     *
     * @param s  - string s which has directory path is taken as input
     * @return   - ArrayList of the files in directory
     */
    public static ArrayList<String> ls(String s) {

        File file = new File(s);
        File[] folder = file.listFiles();
        String element;
        if (!(folder == null)) {
            for (File value : folder) {
                element = value.getName();

                // checking for hidden files
                if (!value.isHidden()) {
                    lines.add(element);
                }
            }
        }
        return lines;
    }

    /**
     * Method wc takes the input of ArrayList to imitate the functionality of WC linux command
     * the result is directly printed in the console
     *
     * @param s -  ArrayList of the content is passed to the wc method
     */
    public static void wc(ArrayList<String> s)
    {
        int line = 0;
        int charC = 0;
        int word = 0;
        for (String ele : s) {
            line++;

            // the line is split into the array of words with " "
            String[] words = ele.split(" ");
            for (String value : words) {
                word += value.length();

                // every word is converted to charArray to get the char Count
                char[] ch = value.toCharArray();
                for (int k = 0; k < ch.length; k++) {
                    charC += ch.length;
                }
            }

        }
        System.out.println(line +"\t"+word+"\t"+charC);
    }
    /**
     * Method head with inputs of arrayList and len process them to give the result of the
     * head command in linux and the result is returned through an ArrayList
     *
     * @param s     - ArrayList of the content is given to get its head
     * @param len   - number of head-lines required from the content
     * @return      -  ArrayList of the head-lines as per len
     */
    public static  ArrayList<String> head(ArrayList<String> s, int len) {
        for(int i=0;i<len;i++) {
            String element = s.get(i);

            // lines are adding into arrayList head
            head.add(element);
        }
        lines.clear();
        lines = head;
        return lines;
    }

    /**
     * Method grep takes inputs of arraylist and the word to search in the content of the list
     * to imitate the functionality of grep command in linux
     *
     * @param str     -  ArrayList of the content is given to get its grep
     * @param word     -  the string to search in the content
     * @return      -  ArrayList of the lines that contains required word
     */
    public static  ArrayList<String> grep(ArrayList<String> str, String word) {
        for (String element : str) {
            String[] words = element.split(" ");
            for (String word1 : words) {

                //checking for the line that contains the required word1
                if (word1.equals(word)) {
                    head.add(element);
                }
            }
        }
        lines.clear();
        lines = head;
        return lines;
    }
    /**
     * Method tail with inputs of arrayList and len process them to give the result of the
     * tail command in linux and the result is returned through an ArrayList
     *
     * @param s   - ArrayList of the content is given to get its tail
     * @param len - number of tail-lines required from the content
     * @return    - ArrayList of the tail-lines as per len
     */
    public static  ArrayList<String> tail(ArrayList<String> s, int len) {

        // for loop iterating from the sizeOf ArrayList - given length to arrayList length to get the tail part of the input
        for(int i=(s.size() - len);i<s.size();i++) {
            String element = s.get(i);
            head.add(element);
        }
        lines.clear();
        lines = head;
        return lines;
    }

    /**
     * Sort method is created to sort the contents of the arrayList that is given as input
     * by using simple collection sort method
     *
     * @param s - ArrayList of the content is given to get its sorted list
     */
    public static void sort(ArrayList<String> s) {
        Collections.sort(s);
    }
    /**
     * @param args - It is used to take the input from user as command line arguments
     */
    public static void main(String[] args) {

        // input argument is split into string array with '|'
        String[] str = args[0].split(" \\| ");
        /*
          for loop iterates through the above String array for every index and check for the commands
          given in if-else loops and invokes the respected methods
         */
        for (String s : str) {
            if (s.contains("cat")) {
                String[] ch = s.split(" ");

                // the method's result is stored in the result arrayList
                result = cat(ch[1]);
            } else if (s.contains("head")) {
                String[] he = s.split(" ");

                //String type is changed into int type using parseInt method
                int len = Integer.parseInt(he[2]);
                result = head(result, len);
            } else if (s.contains("tail")) {
                String[] he = s.split(" ");
                int len = Integer.parseInt(he[2]);
                result = tail(result, len);
            } else if (s.contains("wc")) {

                // calling the wc method with result as argument, it gives void as return type
                wc(result);
                result.clear();
            } else if (s.contains("sort")) {
                sort(result);
            } else if (s.contains("ls")) {
                String[] li = s.split(" ");
                result = ls(li[1]);
            } else if (s.contains("grep")) {
                String[] g = s.split(" ");
                result = grep(result, g[2]);
            }
        }
        for (String element : result) {

            // printing the content in the result arrayList
            System.out.println(element);
        }
    }
}
