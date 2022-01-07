package com.example.kanuledatawebsite.mapper;

import com.example.kanuledatawebsite.entities.Feature;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Maps an colum of a dataframe to a feature object
 */
@Component
public class FeatureExtractor implements ResultSetExtractor<Feature> {

    @Resource
    Feature feature;

    /**
     * extracts data from the resultset
     * @param rs resultset of sql query
     * @return feature object
     * @throws SQLException because of evaluating of sql statements
     * @throws DataAccessException because database is accessed
     */
    @Override
    public Feature extractData(ResultSet rs) throws SQLException, DataAccessException{
        this.feature = new Feature();
        ResultSetMetaData rsm = rs.getMetaData();
        for (int i = 1; i <= rsm.getColumnCount(); i++) {
            String columnName = rsm.getColumnName(i);
            if (!Objects.equals(columnName, "Status nach") && !Objects.equals(columnName, "PatNr")){
                this.feature.setFeatureName(columnName);
            }
        }
        this.feature.setLabelvaluemap(this.CreateLabelvalueMap(rs));
        return this.feature;
    }

    /**
     * Transform the colum with the datapoints of the feature to an HashMmap
     * labels are "geblockt", "biesalski" and "kanuele"
     * @param rs resultset of sql query
     * @return HashMap , where each key of the labels correspond to an array of its label
     * @throws SQLException because sql resultset is accessed
     */
    private HashMap<String, ArrayList<Float>>  CreateLabelvalueMap(ResultSet rs) throws SQLException{
        HashMap<String,ArrayList<Float>> LabelvalueMap = new HashMap<>();
        while( rs.next()){
            LabelvalueMap.computeIfAbsent(rs.getString("Status nach"),k -> new ArrayList<>()).add(rs.getFloat(this.feature.getFeatureName()));
        }
        return LabelvalueMap;
    }


}
