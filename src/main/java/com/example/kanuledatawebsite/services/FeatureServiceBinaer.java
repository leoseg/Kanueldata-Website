package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.dao.Featuredata;
import com.example.kanuledatawebsite.entities.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Service(value="binaer")
public class FeatureServiceBinaer implements FeatureService{

    @Resource
    Featuredata featuredata;

    @Override
    public Feature getFeature(String featureName) {
        Feature feature = featuredata.GetFeature(featureName);
        HashMap<String, ArrayList<Float>> featureLabelvaluemap = feature.getLabelvaluemap();
        feature.setLabelvaluemap(transformLabels(featureLabelvaluemap));
        return feature;
    }

    @Override
    public Feature getSummarizedFeature(String featureName) {
        Feature feature = featuredata.GetSummarizedFeature(featureName);
        HashMap<String, ArrayList<Float>> featureLabelvaluemap = feature.getLabelvaluemap();
        feature.setLabelvaluemap(transformLabels(featureLabelvaluemap));
        return feature;

    }

    private HashMap<String,ArrayList<Float>> transformLabels(HashMap<String, ArrayList<Float>> labelvalueMap){
        labelvalueMap.computeIfAbsent("kanuele",k->new ArrayList<>()).addAll(labelvalueMap.get("Biesalski"));
        labelvalueMap.computeIfAbsent("kanuele",k->new ArrayList<>()).addAll(labelvalueMap.get("geblockt"));
        labelvalueMap.remove("Biesalski");
        labelvalueMap.remove("geblockt");
        return labelvalueMap;
    }
}
