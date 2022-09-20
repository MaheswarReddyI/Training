/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -IndukuriMr(Indukuri Maheswar Reddy).
 */

package Day6;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class converts JSON of fakestore API to xml file
 */
public class JsonToXml {


    public static void main(String[] args) throws JSONException {

        // create variable loc that store location of the Sample.json file
        String loc = "/users/azuga/desktop/fakestore3.json";

        String result;
        try {

            // read byte data from the Sample.json file and convert it into String
            result = new String(Files.readAllBytes(Paths.get(loc)));
            //Convert JSON to XML
            result=result.substring(2,result.length()-2);
            String[] arr=result.split("},\\{");
             // This method converts json object to xml string
            StringBuilder xml=new StringBuilder();
            for (String str : arr){
                str="{"+str+"}";
                xml.append("\n"+"<root>"+"\n").append(convertToXML(str, "root")).append("\n"+"</root>"+"\n");
            }
            String root="roots";
            xml= new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<" + root + ">" + xml + "</" + root + ">");
            FileWriter file = new FileWriter("/users/azuga/desktop/jtox.xml");

            // use write() method of File to write XML data into XMLData.txt
            String xm=new String(xml);
            file.write(xm);
            file.flush();
            System.out.println("Your XML data is successfully written into jtox.xml");

            // close FileWriter
            file.close();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    // create convertToXML() method for converting JSOn data into XML
    public static String convertToXML(String jsonString, String root) throws JSONException {    // handle JSONException

        // create instance of JSONObject by passing jsonString to the constructor
        JSONObject jsonObject = new JSONObject(jsonString);

        // use XML.toString() method to convert JSON into XML and store the result into a string
        String xml = XML.toString(jsonObject);

        // pass the XML data to the main() method
        return xml;
    }
}