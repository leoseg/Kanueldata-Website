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

//maps sql query to featurenitty
@Component
public class FeatureExtractor implements ResultSetExtractor<Feature> {

    @Resource
    Feature feature;

    @Override
    public Feature extractData(ResultSet rs) throws SQLException, DataAccessException{
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

    private HashMap<String, ArrayList<Float>>  CreateLabelvalueMap(ResultSet rs) throws SQLException{
        HashMap<String,ArrayList<Float>> LabelvalueMap = new HashMap<>();
        while( rs.next()){
            LabelvalueMap.computeIfAbsent(rs.getString("Status nach"),k -> new ArrayList<>()).add(rs.getFloat(this.feature.getFeatureName()));
        }
        return LabelvalueMap;
    }


}
