package com.example.kanuledatawebsite.dao;
import com.example.kanuledatawebsite.entities.Feature;
import com.example.kanuledatawebsite.mapper.FeatureExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class FeaturedataImpl implements Featuredata{


    @Resource
    FeatureExtractor extractor;
    NamedParameterJdbcTemplate parameterTemplate;

    public FeaturedataImpl(NamedParameterJdbcTemplate parameterTemplate){
        this.parameterTemplate = parameterTemplate;
    }

    @Override
    public Feature GetFeature(String featurename) {
        final String sqlStatement= "" +
                "SELECT :featurename, \"patientdata\".\"Status nach\" " +
                "FROM featuredata " +
                "INNER JOIN patientdata ON \"featuredata\".\"PatNr\" = \"patientdata\".\"PatNr\"";


        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("featurename",String.format("\"featuredata\".\"%s\"",featurename));
        return parameterTemplate.query(sqlStatement,params,this.extractor);

    }


    @Override
    public Feature GetSummarizedFeature(String featurename) {
        final String sql_statement= "" +
                "SELECT AVG(:featurename), \"patientdata\".\"Status nach\", \"featuredata\".\"PatNr\" " +
                "FROM featuredata " +
                "INNER JOIN patientdata ON \"featuredata\".\"PatNr\" = \"patientdata\".\"PatNr\" "+
                "GROUP BY \"featuredata\".\"PatNr\",\"patientdata\".\"Status nach\"";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("featurename",String.format("\"featuredata\".\"%s\"",featurename));
        return parameterTemplate.query(sql_statement,params,this.extractor);
    }
}
