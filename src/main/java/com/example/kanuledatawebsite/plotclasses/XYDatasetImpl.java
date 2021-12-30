package com.example.kanuledatawebsite.plotclasses;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Resource
public class XYDatasetImpl extends XYSeriesCollection
{
    private void addListPair(ArrayList<Float> values1, ArrayList<Float> values2,String Label){
        XYSeries xySeries = new XYSeries(Label);
        for(int i=0;i<values1.size();i++){
            xySeries.add(values1.get(i), values2.get(i));
        }
        this.addSeries(xySeries);
    }

    public XYDatasetImpl (HashMap<String,ArrayList<Float>> labelValueMap,HashMap<String,ArrayList<Float>> labelValueMap2){
        for(String key :labelValueMap.keySet()){
            this.addListPair(labelValueMap.get(key),labelValueMap2.get(key), key);
        }
    }
}
