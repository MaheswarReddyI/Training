/*
 * Copyright (c) 2022.  - All Rights Reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited-
 * @Author - indukurimr(Indukuri Maheswar Reddy).
 */


package Day2;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Arrays;
import java.util.Date;

/**
 * This ls class performs ls options of linux commands
 */

public class ls{
    public static void main(String[] args){

        // Switch case to perform ls options
        switch (args[0]) {

            case "-a":
                lsa(args[1]);
                break;
            case "-x":
                lsx(args[1]);
                break;
            case "-r":
                lsr(args[1]);
                break;
            case "-1":
                ls1(args[1]);
                break;
            case "-m":
                lsm(args[1]);
                break;
            case "-la":
                lsla(args[1]);
                break;
            case "-l":
                lsl(args[1]);
                break;
            default:
                System.out.println("Enter correct ls command");
        }
    }
    /**
     * It prints the files and directories present in the present working directory
     * separated by comma on to the console
     * @param s - It is used to take path of the directory
     */
    public static void lsm(String s){
        File f = new File(s);
        String[] newArr = f.list();
        if (newArr != null) {
            Arrays.sort(newArr);
        }
        if (newArr != null) {
            for(String i : newArr){
                if (i.charAt(0)!='.'){
                    System.out.print(i+", ");
                }
            }
        }
    }
    /**
     * It prints the files and directories present in the present working directory
     * in separate line on to the console
     * @param s - It is used to take path of the directory
     */
    public static void ls1(String s){
        File f = new File(s);
        String[] newArr = f.list();
        if (newArr != null) {
            Arrays.sort(newArr);
        }
        if (newArr != null) {
            for(String i : newArr){
                if (i.charAt(0)!='.'){
                    System.out.println(i);
                }
            }
        }
    }
    /**
     * It prints the files and directories present in the present working directory
     * in reverse sorted order on to the console
     * @param s - It is used to take path of the directory
     */
    public static void lsr(String s){
        File f = new File(s);
        String[] newArr = f.list();
        if (newArr != null) {
            Arrays.sort(newArr);
        }
        int i= 0;
        if (newArr != null) {
            i = newArr.length-1;
        }
        while (i>=0){
            if (newArr != null && newArr[i].charAt(0) != '.') {
                System.out.printf(newArr[i] + "\t");
            }
            i=i-1;
        }
    }
    /**
     * It prints the files and directories present in the present working directory on to the console
     * @param s - It is used to take path of the directory
     */
    public static void lsx(String s){
        File f = new File(s);
        String[] newArr = f.list();
        Arrays.sort(newArr != null ? newArr : new String[0]);
        if (newArr != null) {
            for(String i : newArr){
                if (i.charAt(0)!='.'){
                    System.out.printf(i+"\t");
                }
            }
        }
    }
    /**
     * It prints the files and directories present in the present working directory
     * including hidden files on to the console
     * @param s - It is used to take path of the directory
     */
    public static void lsa(String s){
        File f = new File(s);
        String[] newArr = f.list();
        if (newArr != null) {
            Arrays.sort(newArr);
        }
        if (newArr != null) {
            for(String i : newArr){
                System.out.printf(i+"\t");
            }
        }
    }
    /**
     * It prints all the files and directories present in the present working directory
     * in a long listing format on to the console
     * @param s - It is used to take path of the directory
     */
    public static void lsla(String s){
        File f = new File(s);
        File[] arr=f.listFiles();
        try {
            if (arr != null) {
                for (File f1 : arr) {
                    Path p = Path.of(f1.getPath());
                    PosixFileAttributes ats = Files.readAttributes(p, PosixFileAttributes.class);
                    System.out.print(PosixFilePermissions.toString(ats.permissions()) + " ");
                    System.out.print(ats.owner().getName() + "   ");
                    System.out.print(ats.group().getName() + "   ");
                    System.out.print(ats.size() / 1024 + "kb   ");
                    System.out.print(new Date(f1.lastModified()) + "   ");
                    System.out.print(f1.getName());
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * It prints the files and directories present in the present working directory
     * in a long listing format on to the console
     * @param s - It is used to take path of the directory
     */
    public static void lsl(String s){
        File f = new File(s);
        File[] arr=f.listFiles();
        try {
            if (arr != null) {
                for (File f1 : arr) {
                    if (!f1.isHidden()){
                        Path p = Path.of(f1.getPath());
                        PosixFileAttributes ats = Files.readAttributes(p, PosixFileAttributes.class);
                        System.out.print(PosixFilePermissions.toString(ats.permissions()) + " ");
                        System.out.print(ats.owner().getName() + "   ");
                        System.out.print(ats.group().getName() + "   ");
                        System.out.print(ats.size() / 1024 + "kb   ");
                        System.out.print(new Date(f1.lastModified()) + "   ");
                        System.out.print(f1.getName());
                        System.out.println();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
