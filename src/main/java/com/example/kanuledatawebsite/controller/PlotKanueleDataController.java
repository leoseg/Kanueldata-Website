package com.example.kanuledatawebsite.controller;
import com.example.kanuledatawebsite.plotclasses.FeaturePair;
import com.example.kanuledatawebsite.entities.PlotInfo;
import com.example.kanuledatawebsite.services.DatabaseService;
import com.example.kanuledatawebsite.services.FeatureService;
import org.jfree.chart.servlet.DisplayChart;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller for all pages referring to the kanueledata
 */
@Controller
@RequestMapping("/KanueleData")
public class PlotKanueleDataController {

    @Resource
    @Qualifier("normal")
    FeatureService featureServiceNormal;

    @Resource
    @Qualifier("binaer")
    FeatureService featureServiceBinaer;

    @Resource
    DatabaseService databaseService;

    @Resource
    FeaturePair featurePair;

    /**
     * Registrates the Servlet for displaying the chart at the website
     * @return new registration bean
     */
    @Bean
    public ServletRegistrationBean MyServlet() {
        return new ServletRegistrationBean<>(new DisplayChart(),"/chart");
    }

    /**
     * Returns page with two multiselects of the features of the data
     * @param model model for thymeleaf page
     * @return name of page
     * @throws SQLException because database is accessed
     */
    @GetMapping(value="/selectfeatures")
    public String showFeatureSelecters(Model model) throws SQLException {

        model.addAttribute("columNames",databaseService.getColumns("featuredata"));
        model.addAttribute("PlotInfo",new PlotInfo());
        return "columindex";
    }

    /**
     *Creates and shows a plot of the values of two features as datapoints in a XY-Plane
     * @param plotInfo info of the plot (feature names and type: normal or binear)
     * @param model model for thymeleaf page
     * @param request http servlet request
     * @return pagename
     * @throws IOException because of featurepair create plot function
     * @throws DataAccessException because database is accessed by featurepair
     */
    @PostMapping(value="/plot")
    public String showPlot(@ModelAttribute PlotInfo plotInfo, Model model, HttpServletRequest request) throws IOException, DataAccessException {
        if(plotInfo.getType().equals("normal")){
            featurePair.setFeaturePair(plotInfo.getFeature1(),plotInfo.getFeature2(),featureServiceNormal);
        }else if (plotInfo.getType().equals("binaer")){
            featurePair.setFeaturePair(plotInfo.getFeature1(),plotInfo.getFeature2(),featureServiceBinaer);
        }
        String imageUrl = featurePair.createPlot(request);
        String imageUrlSummarized = featurePair.createPlotSummarized(request);
        model.addAttribute("imagepath",imageUrl);
        model.addAttribute("imagepathSummarized",imageUrlSummarized);
        return "plot";
    }
}
