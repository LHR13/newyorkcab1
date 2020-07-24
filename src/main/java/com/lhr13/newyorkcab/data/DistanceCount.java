package com.lhr13.newyorkcab.data;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.springframework.stereotype.Component;
import scala.Serializable;

import java.util.Map;

@Component
public class DistanceCount implements Serializable {

    public Map<String, Long> run() throws Exception {
        SparkConf conf = new SparkConf().setAppName("NewYarkCab2").setMaster("local[4]");
        System.setProperty("hadoop.home.dir", "/usr/local/hadoop");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<Cab> cabrecord = new CatchData().CatchData(sc);

        JavaRDD<Cab> wash = cabrecord.filter((Function<Cab, Boolean>) cab
                -> cab != null &&
                cab.getTrip_distance() != "null" );

        JavaRDD<String> dtdistinct = wash.map((Function<Cab, String>) cab -> String.valueOf(Math.round(Double.valueOf(cab.getTrip_distance()))));

        Map<String, Long> spmap = dtdistinct.countByValue();

        sc.close();
        return spmap;
    }
}
