package com.example.kanuledatawebsite.entities;

import com.example.kanuledatawebsite.services.FeatureService;
import com.example.kanuledatawebsite.plotclasses.ScatterPlot;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Class for pair of features for displaying
 */
public class FeaturePair {
    @Getter@Setter Feature feature1;
    @Getter@Setter Feature feature2;
    @Getter@Setter Feature feature1Summarized;
    @Getter@Setter Feature feature2Summarized;
    @Getter@Setter String plotPath;
    @Getter@Setter String plotPathSummarized;

    /**
     * On creation gets feature object for both feature names
     * @param featurename1 name of first feature
     * @param featurename2 name of second feature
     * @param featureService feature service either binaer or normal
     */
    public FeaturePair (String featurename1,String featurename2,FeatureService featureService){
        this.feature1 = featureService.getFeature(featurename1);
        this.feature2 = featureService.getFeature(featurename2);
        this.feature1Summarized =featureService.getSummarizedFeature(featurename1);
        this.feature2Summarized =featureService.getSummarizedFeature(featurename2);

    }

    /**
     * creates a scatterplot and returns the url to the chart
     * @param request http request for creating the servlet
     * @return url to chart
     * @throws IOException because image is saved
     */
    public String createPlot(HttpServletRequest request) throws IOException {
        ScatterPlot scatterPlot =new ScatterPlot(feature1,feature2);

        return scatterPlot.saveAsJpgServlet("",request);

    }
    /**
     * creates a scatterplot for the summarized features and returns the url to the chart
     * @param request http request for creating the servlet
     * @return url to chart
     * @throws IOException because image is saved
     */
    public String createPlotSummarized(HttpServletRequest request) throws IOException {
        ScatterPlot scatterPlotSummarized =new ScatterPlot(feature1Summarized,feature2Summarized);
        return scatterPlotSummarized.saveAsJpgServlet("_summarized",request);
    }


}
