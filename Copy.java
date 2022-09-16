/*
 * Copyright (c) 2022.  - All Rights Reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited-
 * @Author - indukurimr(Indukuri Maheswar Reddy).
 */



package Day1;

import java.io.*;

/**
 * This Copy class mimics the cp command in linux
 */
public class Copy{
    public static void main(String[] args) throws IOException{
        FileInputStream r = new FileInputStream(args[0]);
        FileOutputStream w;
        w = new FileOutputStream(args[1]);
        int i;
        while ((i=r.read())!=-1){  //r.read() gives ASCII value of character
            w.write((char)i);
        }
        System.out.println("File copied Successfully");
    }
}
