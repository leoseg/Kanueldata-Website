package com.example.kanuledatawebsite.plotclasses;

import com.example.kanuledatawebsite.entities.Feature;
import com.example.kanuledatawebsite.services.FeatureService;
import com.example.kanuledatawebsite.plotclasses.ScatterPlot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Class for plotting pairs of features as scatterplot for summarized and normal data
 */
@Component

public class FeaturePair {
    @Getter@Setter Feature feature1;
    @Getter@Setter Feature feature2;
    @Getter@Setter Feature feature1Summarized;
    @Getter@Setter Feature feature2Summarized;


    /**
     * Sets the feature pairs (normal and summarized) by using the names and the feature service
     * @param featurename1 name of first feature
     * @param featurename2 name of second feature
     * @param featureService feature service either binaer or normal
     */
    public void setFeaturePair (String featurename1,String featurename2,FeatureService featureService){
        setFeature1(featureService.getFeature(featurename1));
        setFeature2(featureService.getFeature(featurename2));
        setFeature1Summarized(featureService.getSummarizedFeature(featurename1));
        setFeature2Summarized(featureService.getSummarizedFeature(featurename2));

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
