/*
* copyright (c) 2022.   -  All Rights Reserved
* Unauthorized copying or redistribution of this file in source and binary forms via any medium
* is strictly prohibited-
* @Author - Indukuri Maheswar Reddy ( indukurimr )
* @version - java 11
*/


package Day7;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This Class Visualizes the Data fetched from API as Line Chart
 */

public class lineChart extends ApplicationFrame {

    public lineChart(String applicationTitle , String chartTitle ) throws IOException {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Object Ids","Price of items",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset( ) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        List<Float> price=new ArrayList<>();
        List<Float> objectId=new ArrayList<>();
        String loc = "/users/azuga/desktop/fakestore3.json";
        String result = new String(Files.readAllBytes(Paths.get(loc)));
        JSONArray arr=new JSONArray(result);
        for (Object obj : arr) {
            JSONObject jo = (JSONObject) obj;
            Iterator<?> keys;
            keys = jo.keys();
            while (keys.hasNext()) {
                String s = (String) keys.next();
                if (s.equals("price")) {
                    price.add(Float.parseFloat((String) jo.get(s)));
                } else if (s.equals("id")) {
                    objectId.add(Float.parseFloat((String) jo.get(s)));
                }
            }
        }
        for(int i=0;i<10;i++){
            dataset.addValue( price.get(i) , "Price" , ""+(i+1) );
        }
        return dataset;
    }

    public static void main( String[ ] args ) throws IOException {
        lineChart chart = new lineChart(
                "Line Chart for FakestoreAPI" ,
                "Price vs Object ID");

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
