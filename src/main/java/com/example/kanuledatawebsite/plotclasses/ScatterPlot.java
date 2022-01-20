package com.example.kanuledatawebsite.plotclasses;

import com.example.kanuledatawebsite.entities.Feature;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.xy.XYDataset;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Class for creating the scatter plot
 */
public class ScatterPlot{
    JFreeChart chart;
    String title;

    /**
     * Constructer which creates the chart with the scatterplot of two features
     * @param feature1 Feature 1 corresponding to x axis
     * @param feature2 Feature 2 corresponding to y axis
     */
    public ScatterPlot(Feature feature1, Feature feature2) {

        XYDataset xyDataset = new XYDatasetImpl(feature1.getLabelvaluemap(), feature2.getLabelvaluemap());
        this.title = feature1.getFeatureName()+"_"+feature2.getFeatureName();
        this.chart = ChartFactory.createScatterPlot(title, feature1.getFeatureName(), feature2.getFeatureName(), xyDataset);

    }

    /**
     * Creates a jpg out of the plot and saves it as url
     * @param request request to get the url for saving the jpg
     * @return url of the chart
     * @throws IOException because an image writing operation is performed
     */
    public String saveAsJpgServlet(HttpServletRequest request) throws IOException {
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
        return request.getContextPath() + "/chart?filename=" + fileName;

    }
}
