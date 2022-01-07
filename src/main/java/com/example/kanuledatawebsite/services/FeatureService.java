package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.entities.Feature;

/**
 * Service for getting features
 */
public interface FeatureService {

    /**
     * Get feature
     * @param featureName Name of feature to get
     * @return Feature
     */
    Feature getFeature(String featureName);

    /**
     * Get feature where data is summarized for each patient
     * @param featureName Name of feature to get
     * @return Feature
     */
    Feature getSummarizedFeature(String featureName);

}
