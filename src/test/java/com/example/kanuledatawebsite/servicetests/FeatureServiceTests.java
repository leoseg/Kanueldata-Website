package com.example.kanuledatawebsite.servicetests;

import com.example.kanuledatawebsite.entities.Feature;
import com.example.kanuledatawebsite.services.FeatureService;
import com.example.kanuledatawebsite.services.FeatureServiceBinaer;
import com.example.kanuledatawebsite.services.FeatureServiceNormal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FeatureServiceTests {

    @Autowired
    FeatureServiceBinaer featureServiceBinaer;

    @Autowired
    FeatureServiceNormal featureServiceNormal;

    @Test
    public void testGetSummarizedFeaturesBinaer(){
        HashMap<String, List> exspectedValues = new HashMap<>();
        exspectedValues.put("kanuele", Arrays.asList(12.2));
        exspectedValues.put("keine kanuele", Arrays.asList(4.0,5.0));

        Feature feature = featureServiceBinaer.getSummarizedFeature("feature1");
        assertThat(feature.getFeatureName()).isEqualTo("feature1");
        assertThat(feature.getLabelvaluemap()).isEqualTo(exspectedValues);

    }
    @Test
    public void testGetFeaturesBinaer(){
        HashMap<String, List> exspectedValues = new HashMap<>();
        exspectedValues.put("kanuele", Arrays.asList(7.0,9.0,29.0,7.0,9.0));
        exspectedValues.put("keine kanuele", Arrays.asList(2.0,3.0,7.0,6.0,4.0,5.0));

        Feature feature = featureServiceBinaer.getFeature("feature1");
        assertThat(feature.getFeatureName()).isEqualTo("feature1");
        assertThat(feature.getLabelvaluemap()).isEqualTo(exspectedValues);

    }
    @Test
    public void testGetSummarizedFeatures(){

        HashMap<String, List> exspectedValues = new HashMap<>();
        exspectedValues.put("geblockt", Arrays.asList(15.0));
        exspectedValues.put("Biesalski", Arrays.asList(8.0));
        exspectedValues.put("keine kanuele", Arrays.asList(4.0,5.0));

        Feature feature = featureServiceNormal.getSummarizedFeature("feature1");
        assertThat(feature.getFeatureName()).isEqualTo("feature1");
        assertThat(feature.getLabelvaluemap()).isEqualTo(exspectedValues);
    }
    @Test
    public void testGetFeatures(){
        HashMap<String, List> exspectedValues = new HashMap<>();
        exspectedValues.put("geblockt", Arrays.asList(7.0,9.0,29.0));
        exspectedValues.put("Biesalski", Arrays.asList(7.0,9.0));
        exspectedValues.put("keine kanuele", Arrays.asList(2.0,3.0,7.0,6.0,4.0,5.0));

        Feature feature = featureServiceNormal.getFeature("feature1");
        assertThat(feature.getFeatureName()).isEqualTo("feature1");
        assertThat(feature.getLabelvaluemap()).isEqualTo(exspectedValues);

    }
}
