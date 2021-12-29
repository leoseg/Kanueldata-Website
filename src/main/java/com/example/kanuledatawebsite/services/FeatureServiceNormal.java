package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.dao.FeaturedataDao;
import com.example.kanuledatawebsite.entities.Feature;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
