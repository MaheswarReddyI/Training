/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -IndukuriMr(Indukuri Maheswar Reddy).
 */

package Day6;

import au.com.bytecode.opencsv.CSVReader;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class converts csv file of fakestore API to pdf file
 */


public class CsvToPdf {
    public static void main(String[] args) throws IOException, DocumentException {
        //Read input CSV file in Java
        String inputCSVFile = "/Users/azuga/Desktop/fakestore3.csv";
        CSVReader read = new CSVReader(new FileReader(inputCSVFile));
        String [] nextLine;
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream("/Users/azuga/Desktop/csv-pdf.pdf"));
        document.open();
        PdfPTable Tables = new PdfPTable(8);

        //Fixing Column widths
        float[] columnWidths = new float[]{30f, 20f, 65f, 25f,15f, 30f, 20f, 28f};
        Tables.setWidths(columnWidths);
        PdfPCell pdfPCell;
        while ((nextLine = read.readNext()) != null) {
            int i=0;
            while(i<=7) {
                pdfPCell = new PdfPCell(new Phrase(nextLine[i]));
                Tables.addCell(pdfPCell);
                i++;
            }
        }
        //Attach table to PDF and close the document
        document.add(Tables);
        document.close();
    }
}
