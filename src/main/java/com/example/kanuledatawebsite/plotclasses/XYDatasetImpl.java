package com.example.kanuledatawebsite.plotclasses;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Extends XYSeriesCollection so arraylists can be used for dataset creation
 */
@Resource
public class XYDatasetImpl extends XYSeriesCollection
{
    /**
     * Adds a pair of lists to the series of the dataset
     * @param values1 x values
     * @param values2 y values
     * @param Label label for the series
     */
    private void addListPair(List<Double> values1, List<Double> values2, String Label){
        XYSeries xySeries = new XYSeries(Label);
        for(int i=0;i<values1.size();i++){
            xySeries.add(values1.get(i), values2.get(i));
        }
        this.addSeries(xySeries);
    }

    /**
     * creates of two hashmap the corresponding series, both should have form of key:arraylist and
     * have the same keys (for scatterplots)
     * @param labelValueMap first labelvaluemap with x values
     * @param labelValueMap2 second labelvaluemap with y values
     */
    public XYDatasetImpl (HashMap<String, List<Double>> labelValueMap, HashMap<String, List<Double>> labelValueMap2){
        for(String key :labelValueMap.keySet()){
            this.addListPair(labelValueMap.get(key),labelValueMap2.get(key), key);
        }
    }
}
