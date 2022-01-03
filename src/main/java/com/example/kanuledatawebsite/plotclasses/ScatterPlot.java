package com.example.kanuledatawebsite.plotclasses;

import com.example.kanuledatawebsite.entities.Feature;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.xy.XYDataset;

import javax.servlet.http.HttpServletRequest;
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
    }
}
