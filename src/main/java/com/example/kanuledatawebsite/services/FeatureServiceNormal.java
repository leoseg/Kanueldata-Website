package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.dataaccesslayer.FeaturedataDao;
import com.example.kanuledatawebsite.entities.Feature;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Gets feature service with the normal class separation ("geblockt", "no kanuele", "biesalski")
 */
@Service(value="normal")
public class FeatureServiceNormal implements FeatureService{

    @Resource
    FeaturedataDao featuredataDao;

    @Override
    public Feature getFeature(String featureName) {
        return featuredataDao.GetFeature(featureName);
    }

    @Override
    public Feature getSummarizedFeature(String featureName) {
        return featuredataDao.GetSummarizedFeature(featureName);
    }
}
