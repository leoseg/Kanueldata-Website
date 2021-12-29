package com.example.kanuledatawebsite.controller;
import com.example.kanuledatawebsite.entities.FeaturePair;
import com.example.kanuledatawebsite.entities.PlotInfo;
import com.example.kanuledatawebsite.services.DatabaseService;
import com.example.kanuledatawebsite.services.FeatureService;
import org.jfree.chart.servlet.DisplayChart;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

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

    @Bean
    public ServletRegistrationBean MyServlet() {
        return new ServletRegistrationBean<>(new DisplayChart(),"/chart");
    }

    @GetMapping(value="/selectfeatures")
    public String showFeatureSelecters(Model model) throws SQLException {

        model.addAttribute("columNames",databaseService.getColumns("featuredata"));
        model.addAttribute("PlotInfo",new PlotInfo());
        return "columindex";
    }
    @PostMapping(value="/plot")
    public String showPlotNormal(@ModelAttribute PlotInfo plotInfo, Model model, HttpServletRequest request) throws IOException {
        FeaturePair featurePair;
        if(plotInfo.getType().equals("normal")){
            featurePair = new FeaturePair(plotInfo.getFeature1(),plotInfo.getFeature2(),featureServiceNormal);
        }else{
            featurePair = new FeaturePair(plotInfo.getFeature1(),plotInfo.getFeature2(),featureServiceBinaer);
        }
        String imageUrl = featurePair.createPlot(request);
        String imageUrlSummarized = featurePair.createPlotSummarized(request);
        model.addAttribute("imagepath",imageUrl);
        model.addAttribute("imagepathSummarized",imageUrlSummarized);
        return "plot";
    }
}
