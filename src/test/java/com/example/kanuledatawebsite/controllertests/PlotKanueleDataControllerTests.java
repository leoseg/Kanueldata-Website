package com.example.kanuledatawebsite.controllertests;
import com.example.kanuledatawebsite.controller.PlotKanueleDataController;
import com.example.kanuledatawebsite.dataaccesslayer.UserRepository;
import com.example.kanuledatawebsite.entities.PlotInfo;
import com.example.kanuledatawebsite.plotclasses.FeaturePair;
import com.example.kanuledatawebsite.services.DatabaseService;
import com.example.kanuledatawebsite.services.FeatureService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



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

    @MockBean
    FeaturePair featurePair;

    @Test
    @WithMockUser
    public void testShowFeatureSelecters() throws Exception {

        this.mockMvc
                .perform(get("/KanueleData/selectfeatures"))
                .andExpect(status().isOk())
                .andExpect(view().name("columindex"))
                .andExpect(model().attribute("PlotInfo", Matchers.isA(PlotInfo.class)))
                .andExpect(model().attribute("columNames",Matchers.equalTo(databaseService.getColumns("featuredata"))));
    }

    @Test
    @WithMockUser
    public void testShowPlot() throws Exception{
        when(featurePair.createPlot(any(HttpServletRequest.class))).thenReturn("imageurl");
        when(featurePair.createPlotSummarized(any(HttpServletRequest.class))).thenReturn("imageurlsummarized");
        this.mockMvc
                .perform(post("/KanueleData/plot")
                        .param("feature1","feature1Name")
                        .param("feature2","feature2Name")
                        .param("type","normal"))
                .andExpect(status().isOk())
                .andExpect(view().name("plot"))
                .andExpect(model().attribute("imagepath","imageurl"))
                .andExpect(model().attribute("imagepathSummarized","imageurlsummarized"));
        verify(featurePair,times(1)).setFeaturePair(eq("feature1Name"),eq("feature2Name"),any(FeatureService.class));
        verify(featurePair,times(1)).createPlot(any());
        verify(featurePair,times(1)).createPlotSummarized(any());

    }

    @Test
    public void testUnauthorizedRequest() throws Exception{
        this.mockMvc
                .perform(get("/KanueleData/selectfeatures"))
                .andExpect(status().is(302));
    }




}
