/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -IndukuriMr(Indukuri Maheswar Reddy).
 */

package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

/**
 * This class converts csv file of fakestore API into html file
 */

public class CsvToHtml {
    public static void main(String[] args) {


        String csvFile = "/Users/azuga/Desktop/fakestore3.csv";
        String outputFile = "/Users/azuga/Desktop/CsvToHtml.html";

        // read lines of csv to a string array list
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String s3 = currentLine.replaceAll("\"","");
                String s=s3.replaceAll("https\\://fakestoreapi.com","<img src=https\\://fakestoreapi.com");
                String s1=s.replaceAll("jpg","jpg style=\"width:100px;height:100px;\" >");

                lines.add(s1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //embrace <td> and <tr> for lines and columns
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, "<tr><td>" + lines.get(i) + "</td></tr>");
            lines.set(i, lines.get(i).replaceAll(",", "</td><td>"));
        }

        // embrace <table> and </table>
        lines.set(0, "<table border>" + lines.get(0));
        lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "</table>");

        // output result
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            System.out.println("successful");
        } catch (IOException e) {
            System.out.println("something went wrong");
            e.printStackTrace();
        }
    }
}