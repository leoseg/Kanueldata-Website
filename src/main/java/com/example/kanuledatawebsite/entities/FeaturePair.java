package com.example.kanuledatawebsite.entities;

import com.example.kanuledatawebsite.entities.Feature;
import com.example.kanuledatawebsite.services.FeatureService;
import com.example.kanuledatawebsite.visualizer.ScatterPlot;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class FeaturePair {
    @Getter@Setter Feature feature1;
    @Getter@Setter Feature feature2;
    @Getter@Setter Feature feature1Summarized;
    @Getter@Setter Feature feature2Summarized;
    @Getter@Setter String plotPath;
    @Getter@Setter String plotPathSummarized;

    public FeaturePair (String featurename1,String featurename2,FeatureService featureService){
        this.feature1 = featureService.getFeature(featurename1);
        this.feature2 = featureService.getFeature(featurename2);
        this.feature1Summarized =featureService.getSummarizedFeature(featurename1);
        this.feature2Summarized =featureService.getSummarizedFeature(featurename2);

    }
    public void createPlot() throws IOException {
        ScatterPlot scatterPlot =new ScatterPlot(feature1,feature2);

        this.plotPath = scatterPlot.saveAsJpg("");

    }

    public void createPlotSummarized() throws IOException {
        ScatterPlot scatterPlotSummarized =new ScatterPlot(feature1Summarized,feature2Summarized);
        this.plotPathSummarized= scatterPlotSummarized.saveAsJpg("_summarized");
    }


}
