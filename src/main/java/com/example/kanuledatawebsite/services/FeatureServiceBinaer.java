package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.dataaccesslayer.FeaturedataDao;
import com.example.kanuledatawebsite.entities.Feature;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Gets features for the binary categorization ("kanuele", "no kanuele")
 */
@Service(value="binaer")
public class FeatureServiceBinaer implements FeatureService{

    @Resource
    FeaturedataDao featuredataDao;

    @Override
    public Feature getFeature(String featureName) {
        Feature feature = featuredataDao.GetFeature(featureName);
        HashMap<String, ArrayList<Float>> featureLabelvaluemap = feature.getLabelvaluemap();
        feature.setLabelvaluemap(transformLabels(featureLabelvaluemap));
        return feature;
    }

    @Override
    public Feature getSummarizedFeature(String featureName) {
        Feature feature = featuredataDao.GetSummarizedFeature(featureName);
        HashMap<String, ArrayList<Float>> featureLabelvaluemap = feature.getLabelvaluemap();
        feature.setLabelvaluemap(transformLabels(featureLabelvaluemap));
        return feature;

    }

    /**
     * Transforms orginal labels from normal categorization to binaer categorization
     * @param labelvalueMap HashMap to do transforming
     * @return new HashMap with new values
     */
    private HashMap<String,ArrayList<Float>> transformLabels(HashMap<String, ArrayList<Float>> labelvalueMap){
        labelvalueMap.computeIfAbsent("kanuele",k->new ArrayList<>()).addAll(labelvalueMap.get("Biesalski"));
        labelvalueMap.computeIfAbsent("kanuele",k->new ArrayList<>()).addAll(labelvalueMap.get("geblockt"));
        labelvalueMap.remove("Biesalski");
        labelvalueMap.remove("geblockt");
        return labelvalueMap;
    }
}
