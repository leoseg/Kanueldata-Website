package com.example.kanuledatawebsite.controllertests;

import com.example.kanuledatawebsite.controller.PlotKanueleDataController;
import com.example.kanuledatawebsite.controller.UserLoginController;
import com.example.kanuledatawebsite.dataaccesslayer.UserRepository;
import com.example.kanuledatawebsite.entities.PlotInfo;
import com.example.kanuledatawebsite.services.DatabaseService;
import com.example.kanuledatawebsite.services.FeatureService;
import com.example.kanuledatawebsite.services.FeatureServiceBinaer;
import com.example.kanuledatawebsite.services.FeatureServiceNormal;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PlotKanueleDataController.class)
public class PlotKanueleDataControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("binaer")
    FeatureService featureServiceBinaer;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    @Qualifier("normal")
    FeatureService featureServiceNormal;

    @MockBean
    DatabaseService databaseService;

    @MockBean
    JdbcTemplate jdbcTemplate;

    @Test
    public void testShowFeatureSelecters() throws Exception {
        this.mockMvc.perform(get("/KanueleData/selectfeatures"))
                .andExpect(status().isOk())
                .andExpect(view().name("columindex"))
                .andExpect(model().attribute("PlotInfo", Matchers.equalTo(new PlotInfo())))
                .andExpect(model().attribute("columNames",Matchers.equalTo(databaseService.getColumns("featuredata"))));
    }


}
