package com.example.kanuledatawebsite.dataaccesslayer;

import com.example.kanuledatawebsite.entities.Feature;

public interface FeaturedataDao {



    Feature GetFeature (String featurename);

    Feature GetSummarizedFeature(String featurename);


}