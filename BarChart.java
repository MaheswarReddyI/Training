/*
 * copyright (c) 2022.   -  All Rights Reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited-
 * @Author - Indukuri Maheswar Reddy ( indukurimr )
 * @version - java 11
 */


package Day7;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * Barchart class produces Bar Chart for the data fetched from fakestoreAPI i.e., Object id VS price
 */


public class BarChart extends ApplicationFrame {



    public BarChart(String applicationTitle , String chartTitle ) throws IOException {
        super( applicationTitle );
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Object Id's",
                "Price",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    /**
     * This method Takes input of data fetched from API and returns Dataset object
     * @return It returns Dataset object
     * @throws IOException
     */
    private CategoryDataset createDataset( ) throws IOException {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
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
            //noinspection rawtypes
            dataset.addValue( price.get(i) , (Comparable) (i+1) , ""+(i+1) );
        }

        return dataset;
    }

    public static void main( String[ ] args ) throws IOException {
        BarChart chart = new BarChart("Bar Chart", "Price of Object id's");
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
