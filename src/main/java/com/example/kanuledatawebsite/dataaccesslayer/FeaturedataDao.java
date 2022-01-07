package com.example.kanuledatawebsite.dataaccesslayer;

import com.example.kanuledatawebsite.entities.Feature;

/**
 * For accessing the data of the feature dataframes
 */
public interface FeaturedataDao {


    /**
     * Returns the feature object for the given feauturename
     * each value corresponds to one feature
     * @param featurename name of feature
     * @return Feature object
     */
    Feature GetFeature (String featurename);

    /**
     * Returns the feature object for the given feauturename,
     * each value corresponds to one patient
     * @param featurename name of feature
     * @return Feature object
     */
    Feature GetSummarizedFeature(String featurename);


}
