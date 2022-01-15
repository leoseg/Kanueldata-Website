package com.example.kanuledatawebsite.servicetests;
import com.example.kanuledatawebsite.entities.Feature;
import com.example.kanuledatawebsite.services.FeatureServiceBinaer;
import com.example.kanuledatawebsite.services.FeatureServiceNormal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class FeatureServiceTests {

    @Autowired
    FeatureServiceBinaer featureServiceBinaer;

    @Autowired
    FeatureServiceNormal featureServiceNormal;

    @Test
    public void testGetSummarizedFeaturesBinaer(){
        HashMap<String, List<Double>> exspectedValues = new HashMap<>();
        exspectedValues.put("kanuele", Arrays.asList(8.0,15.0));
        exspectedValues.put("keine kanuele", Arrays.asList(4.0,5.0));

        Feature feature = featureServiceBinaer.getSummarizedFeature("feature1");
        assertThat(feature.getFeatureName()).isEqualTo("feature1");
        assertThat(feature.getLabelvaluemap()).isEqualTo(exspectedValues);

    }
    @Test
    public void testGetFeaturesBinaer(){
        HashMap<String, List<Double>> exspectedValues = new HashMap<>();
        exspectedValues.put("kanuele", Arrays.asList(7.0,9.0,7.0,9.0,29.0));
        exspectedValues.put("keine kanuele", Arrays.asList(2.0,3.0,7.0,6.0,4.0,5.0));

        Feature feature = featureServiceBinaer.getFeature("feature1");
        assertThat(feature.getFeatureName()).isEqualTo("feature1");
        assertThat(feature.getLabelvaluemap()).isEqualTo(exspectedValues);

    }
    @Test
    public void testGetSummarizedFeatures(){
        HashMap<String,List<Double>> exspectedValues = new HashMap<>();
        exspectedValues.put("geblockt", Arrays.asList(15.0));
        exspectedValues.put("Biesalski", Arrays.asList(8.0));
        exspectedValues.put("keine kanuele", Arrays.asList(4.0,5.0));
        Feature feature = featureServiceNormal.getSummarizedFeature("feature1");
        assertThat(feature.getFeatureName()).isEqualTo("feature1");
        assertThat(feature.getLabelvaluemap()).isEqualTo(exspectedValues);
    }
    @Test
    public void testGetFeatures(){
        HashMap<String, List<Double>> exspectedValues = new HashMap<>();
        exspectedValues.put("geblockt", Arrays.asList(7.0,9.0,29.0));
        exspectedValues.put("Biesalski", Arrays.asList(7.0,9.0));
        exspectedValues.put("keine kanuele", Arrays.asList(2.0,3.0,7.0,6.0,4.0,5.0));

        Feature feature = featureServiceNormal.getFeature("feature1");
        assertThat(feature.getFeatureName()).isEqualTo("feature1");
        assertThat(feature.getLabelvaluemap()).isEqualTo(exspectedValues);

    }
}
