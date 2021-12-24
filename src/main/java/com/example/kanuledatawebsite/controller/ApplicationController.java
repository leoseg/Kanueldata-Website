package com.example.kanuledatawebsite.controller;
import com.example.kanuledatawebsite.entities.Feature;
import com.example.kanuledatawebsite.entities.FeaturePair;
import com.example.kanuledatawebsite.services.DatabaseService;
import com.example.kanuledatawebsite.services.FeatureService;
import com.example.kanuledatawebsite.visualizer.ScatterPlot;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/KanueleData")
public class ApplicationController {

    @Resource
    @Qualifier("normal")
    FeatureService featureServiceNormal;

    @Resource
    @Qualifier("binaer")
    FeatureService featureServiceBinaer;

    @Resource
    DatabaseService databaseService;

    @GetMapping(value="/selectfeatures")
    public String showFeatureSelecters(Model model) throws SQLException {

        model.addAttribute("columNames",databaseService.getColumns("featuredata"));
        model.addAttribute("PlotInfo",new PlotInfo());
        return "columindex";
    }
    @PostMapping(value="/plot")
    public String showPlotNormal(@ModelAttribute PlotInfo plotInfo,Model model) throws IOException {
        FeaturePair featurePair;
        if(plotInfo.getType().equals("normal")){
            featurePair = new FeaturePair(plotInfo.getFeature1(),plotInfo.getFeature2(),featureServiceNormal);
        }else{
            featurePair = new FeaturePair(plotInfo.getFeature1(),plotInfo.getFeature2(),featureServiceBinaer);
        }
        featurePair.createPlot();
        featurePair.createPlotSummarized();
        model.addAttribute("imagepath",featurePair.getPlotPath());
        model.addAttribute("imagepathSummarized",featurePair.getPlotPathSummarized());
        return "plot";
    }
}
