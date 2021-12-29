package com.example.kanuledatawebsite.visualizer;

import com.example.kanuledatawebsite.entities.Feature;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.xy.XYDataset;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class ScatterPlot{
    JFreeChart chart;
    String title;
    public ScatterPlot(Feature feature1, Feature feature2) {

        XYDataset xyDataset = new XYDatasetImpl(feature1.getLabelvaluemap(), feature2.getLabelvaluemap());
        this.title = feature1.getFeatureName()+"_"+feature2.getFeatureName();
        this.chart = ChartFactory.createScatterPlot(title, feature1.getFeatureName(), feature2.getFeatureName(), xyDataset);

    }

    public String saveAsJpgServlet(String filenameAddition, HttpServletRequest request) throws IOException {
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
        String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
        return chartURL;
//        String filepath_to_images= ("src/main/resources/static/images/");
//
//        File file = new File(filepath_to_images+ this.title+filenameAddition+".png");
//        if (!file.exists()) file.createNewFile();
//        ChartUtils.saveChartAsPNG(file,chart,400,600);
//        return this.title+filenameAddition+".png";
    }
}
