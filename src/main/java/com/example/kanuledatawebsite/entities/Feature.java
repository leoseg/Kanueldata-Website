package com.example.kanuledatawebsite.entities;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class Feature {
    /**
     * Name of Feature
     */
    @Getter@Setter String FeatureName;
    /**
     * List with a label of the patient and all corresponding datapoints
     */
    @Getter@Setter HashMap<String, List<Double>> Labelvaluemap;

}
