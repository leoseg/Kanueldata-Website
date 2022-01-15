package com.example.kanuledatawebsite;

import com.example.kanuledatawebsite.controller.PlotKanueleDataController;
import com.example.kanuledatawebsite.controller.UserLoginController;
import com.example.kanuledatawebsite.plotclasses.FeaturePair;
import com.example.kanuledatawebsite.services.FeatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;



@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class KanuledataWebsiteApplicationTests {


    @Autowired
    private FeaturePair featurePair;

    @Autowired
    @Qualifier("normal")
    private FeatureService featureServiceNormal;

    @Autowired
    @Qualifier("binaer")
    private FeatureService featureServiceBinaer;

    @Autowired
    private PlotKanueleDataController plotKanueleDataController;

    @Autowired
    private UserLoginController userLoginController;


    @Test
    void contextLoads() {
       assertNotNull(featurePair);
       assertNotNull(featureServiceBinaer);
       assertNotNull(featureServiceNormal);
       assertNotNull(plotKanueleDataController);
       assertNotNull(userLoginController);
    }

}
