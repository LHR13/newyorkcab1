package com.lhr13.newyorkcab.data;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.rdd.RDD;

import java.io.Serializable;

public class Center implements Serializable {
    public void run() throws Exception {
        SparkConf conf = new SparkConf().setAppName("NewYarkCab2").setMaster("local");
        System.setProperty("hadoop.home.dir", "/usr/local/hadoop");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<Cab> cabrecord = new CatchData().CatchData(sc);

        JavaRDD<Cab> wash = cabrecord.filter((Function<Cab, Boolean>) cab ->
                (!cab.getPickup_longitude().equals("0")
                        && !cab.getPickup_latitude().equals("0")
                        && cab.getPickup_longitude() != "null"
                        && cab.getPickup_latitude() != "null"
                        && cab != null));

        JavaRDD<String> point = wash.map((Function<Cab, String>) cab -> cab.getPickup_latitude() + "," + cab.getPickup_longitude());

        JavaRDD<Vector> pickpoint = point.map(new Function<String, Vector>() {
            @Override
            public Vector call(String s) throws Exception {
                String[] arr = s.split(",");
                double[] pt = new double[2];
                for (int i = 0; i < arr.length; i++) {
                    pt[i] = new Double(arr[i]);
                }
                return Vectors.dense(pt);
            }
        });

        RDD<Vector> rdd = pickpoint.rdd();

        KMeansModel kMeansModel = KMeans.train(rdd, 5, 10);
        for (Vector center : kMeansModel.clusterCenters()) {
            System.out.println(center);
        }
    }
}
