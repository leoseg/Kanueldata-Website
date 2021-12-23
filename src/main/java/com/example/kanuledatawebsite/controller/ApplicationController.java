package com.example.kanuledatawebsite.controller;
import com.example.kanuledatawebsite.entities.Feature;
import com.example.kanuledatawebsite.entities.FeaturePair;
import com.example.kanuledatawebsite.services.DatabaseService;
import com.example.kanuledatawebsite.services.FeatureService;
import com.example.kanuledatawebsite.visualizer.ScatterPlot;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

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

        model.addAttribute("columNames",databaseService.getColumns("FeatureData"));
        return "columindex";
    }
    @PostMapping(value="/plotnormal")
    public String showPlotNormal(@RequestBody Map<String, String> json) throws IOException {
        FeaturePair featurePair = new FeaturePair(json.get("featurename1"),json.get("featurename2"),featureServiceNormal);
        featurePair.createPlot();
        featurePair.createPlotSummarized();
        return "plotnormal";
    }
}
