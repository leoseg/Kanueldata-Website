package com.example.kanuledatawebsite.visualizer;

import com.example.kanuledatawebsite.entities.Feature;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
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

    public void saveAsJpg(String filenameAddition) throws IOException {
        ChartUtils.saveChartAsPNG(new File("src\\main\\resources\\static\\"+this.title+filenameAddition),chart,400,600);
    }
}
