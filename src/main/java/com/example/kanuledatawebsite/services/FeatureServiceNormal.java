package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.dao.Featuredata;
import com.example.kanuledatawebsite.entities.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value="normal")
public class FeatureServiceNormal implements FeatureService{

    @Resource
    Featuredata featuredata;

    @Override
    public Feature getFeature(String featureName) {
        return featuredata.GetFeature(featureName);
    }

    @Override
    public Feature getSummarizedFeature(String featureName) {
        return featuredata.GetSummarizedFeature(featureName);
    }
}
