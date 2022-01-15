package com.example.kanuledatawebsite.plottests;

import com.example.kanuledatawebsite.entities.Feature;
import com.example.kanuledatawebsite.plotclasses.FeaturePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FeaturePairTests {

    @Autowired
    FeaturePair featurePair;

    @BeforeEach
    public void setupFeaturePair(){
        HashMap<String, List<Double>> feature1SummarizedValues = new HashMap<>();
        feature1SummarizedValues.put("geblockt", Arrays.asList(15.0));
        feature1SummarizedValues.put("Biesalski", Arrays.asList(8.0));
        feature1SummarizedValues.put("keine kanuele", Arrays.asList(4.0,5.0));
        HashMap<String, List<Double>> feature2SummarizedValues = new HashMap<>();
        feature2SummarizedValues.put("geblockt", Arrays.asList(11.0));
        feature2SummarizedValues.put("Biesalski", Arrays.asList(7.0));
        feature2SummarizedValues.put("keine kanuele", Arrays.asList(4.0,8.0));
        HashMap<String, List<Double>> feature1Values = new HashMap<>();
        feature1Values.put("geblockt", Arrays.asList(7.0,9.0,29.0));
        feature1Values.put("Biesalski", Arrays.asList(7.0,0.9));
        feature1Values.put("keine kanuele", Arrays.asList(2.0,3.0,7.0,6.0,3.0,5.0));
        HashMap<String, List<Double>> feature2Values = new HashMap<>();
        feature2Values.put("geblockt", Arrays.asList(7.0,11.0,16.0));
        feature2Values.put("Biesalski", Arrays.asList(7.0,2.0));
        feature2Values.put("keine kanuele", Arrays.asList(2.0,4.0,7.0,34.0,4.0,5.0));
        Feature feature1 = new Feature();
        Feature feature2 = new Feature();
        Feature feature1Summarized = new Feature();
        Feature feature2Summarized = new Feature();
        feature1.setFeatureName("feature1");
        feature1.setLabelvaluemap(feature1Values);
        feature2.setFeatureName("feature2");
        feature2.setLabelvaluemap(feature2Values);
        feature1Summarized.setFeatureName("feature1");
        feature1Summarized.setLabelvaluemap(feature1SummarizedValues);
        feature1Summarized.setFeatureName("feature2");
        feature2Summarized.setLabelvaluemap(feature2SummarizedValues);
        featurePair.setFeature1(feature1 );
        featurePair.setFeature2(feature2);
        featurePair.setFeature1Summarized(feature1Summarized);
        featurePair.setFeature2Summarized(feature2Summarized);
    }

    @Test
    public void testCreatePlot() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("www.test.com");
        request.setRequestURI("/testplot");
        String imageurl = featurePair.createPlot(request);
        assertThat(imageurl).matches("^/chart.*jpeg$");


    }

    @Test
    public void testCreatePlotSummarized() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("www.test.com");
        request.setRequestURI("/testplot");
        String imageurl = featurePair.createPlotSummarized(request);
        assertThat(imageurl).matches("^/chart.*jpeg$");

    }
}
