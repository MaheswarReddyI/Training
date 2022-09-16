/*
 * Copyright (c) 2022.  - All Rights Reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited-
 * @Author - indukurimr(Indukuri Maheswar Reddy).
 */

package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;


/**
 * This class performs wc command options in linux
 */

public class wc{
    public static void main(String[] args){
        if (args.length==1){
            wcl(args[0]);
            wcw(args[0]);
            wcc(args[0]);
            System.out.println(args[0]);
        }
        else{

            // Switch case to perform wc options.
            switch (args[0]) {

                case "-l":
                    wcl(args[1]);
                    break;
                case "-w":
                    wcw(args[1]);
                    break;
                case "-L":
                    wll(args[1]);
                    break;
                case "-c":
                    wcc(args[1]);
                    break;
                default:
                    System.out.println("Enter correct wc command");
            }
        }
    }

    /**
     * It prints Number of lines present in the given file on to the console
     * @param args -It is used to take the input file path
     */
    public static void wcl(String args){
        int lc=0;
        BufferedReader reader;
        try{
            reader=new BufferedReader(new FileReader(args));
            String curLine=reader.readLine();
            while (curLine!=null){
                lc++;
                curLine=reader.readLine();
            }
            System.out.print("       "+lc+"     ");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * It prints Number of words present in the given file on to the console
     * @param args -It is used to take the input file path
     */
    public static void wcw(String args){
        int wc=0;
        BufferedReader reader;
        try{
            reader=new BufferedReader(new FileReader(args));
            String curLine=reader.readLine();
            while (curLine!=null){
                String[] words =curLine.split(" ");
                wc+=words.length;
                curLine=reader.readLine();
            }
            System.out.print(wc+"     ");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * It prints Number of characters or bytes present in the given file on to the console
     * @param args -It is used to take the input file path
     */
    public static void wcc(String args){
        int c=0;
        try{
            FileInputStream r=new FileInputStream(args);
            while (r.read() !=-1){
                c+=1;
            }
            System.out.print(c+" ");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * It prints length of the longest line present in the given file on to the console.
     * @param args -It is used to take the input file path.
     */
    public static void wll(String args){
        BufferedReader reader;
        try{
            reader=new BufferedReader(new FileReader(args));
            String curLine=reader.readLine();
            int l=0;
            while (curLine!=null){
                if (curLine.length()>l){
                    l=curLine.length();
                }
                curLine=reader.readLine();
            }
            System.out.print("       "+l+"     ");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}