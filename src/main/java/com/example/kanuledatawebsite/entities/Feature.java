package com.example.kanuledatawebsite.entities;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class Feature {

    @Getter@Setter String FeatureName;
    @Getter@Setter HashMap<String,ArrayList<Float>> Labelvaluemap;

}
