package com.example.kanuledatawebsite.dao;
import com.example.kanuledatawebsite.entities.Feature;
import com.example.kanuledatawebsite.mapper.FeatureExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class FeaturedataDaoImpl implements FeaturedataDao {


    @Resource
    FeatureExtractor extractor;
    NamedParameterJdbcTemplate parameterTemplate;

    public FeaturedataDaoImpl(NamedParameterJdbcTemplate parameterTemplate){
        this.parameterTemplate = parameterTemplate;
    }

    @Override
    public Feature GetFeature(String featurename) {
        final String sqlStatement= "" +
                "SELECT "+String.format("featuredata.%s",featurename)+" AS "+featurename+", \"patientdata\".\"Status nach\" " +
                "FROM featuredata " +
                "INNER JOIN patientdata ON \"featuredata\".\"PatNr\" = \"patientdata\".\"PatNr\"";

        return parameterTemplate.query(sqlStatement,this.extractor);

    }


    @Override
    public Feature GetSummarizedFeature(String featurename) {
        final String sql_statement= "" +
                "SELECT AVG("+String.format("featuredata.%s",featurename)+") AS "+featurename+", \"patientdata\".\"Status nach\", \"featuredata\".\"PatNr\" " +
                "FROM featuredata " +
                "INNER JOIN patientdata ON \"featuredata\".\"PatNr\" = \"patientdata\".\"PatNr\" "+
                "GROUP BY \"featuredata\".\"PatNr\",\"patientdata\".\"Status nach\"";

        return parameterTemplate.query(sql_statement,this.extractor);
    }
}
