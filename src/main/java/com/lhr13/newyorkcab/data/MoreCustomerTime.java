package com.lhr13.newyorkcab.data;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Serializable;

import java.util.Map;

public class MoreCustomerTime implements Serializable {
    public Map<String, Long> run() throws Exception {
        SparkConf conf = new SparkConf().setAppName("NewYarkCab2").setMaster("local[4]");
        System.setProperty("hadoop.home.dir", "/usr/local/hadoop");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<Cab> cabrecord = new CatchData().CatchData(sc);

        JavaRDD<Cab> wash = cabrecord.filter((Function<Cab, Boolean>) cab -> cab.getPickup_datatime() != "null" &&
                cab != null);

        JavaRDD<String> more = wash.map((Function<Cab, String>) cab -> cab.getPickup_datatime().substring(11,13));

        Map<String, Long> morePassagers = more.countByValue();

        sc.close();

        return morePassagers;

    }
}
