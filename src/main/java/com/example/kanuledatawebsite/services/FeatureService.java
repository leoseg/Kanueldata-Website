package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.entities.Feature;

public interface FeatureService {

    Feature getFeature(String featureName);

    Feature getSummarizedFeature(String featureName);

}
