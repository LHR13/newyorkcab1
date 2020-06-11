package service;

import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.springframework.stereotype.Component;
import scala.Serializable;

import java.util.Map;

@Component
public class DistanceCount implements Serializable {

    public Map<String, Long> run() throws Exception {
        JavaRDD<Cab> cabrecord = new CatchData().CatchData();

        JavaRDD<Cab> wash = cabrecord.filter((Function<Cab, Boolean>) cab
                -> cab != null &&
                cab.getTrip_distance() != "null" &&
                cab.getTrip_time_in_secs() != "null");

        JavaRDD<String> dtdistinct = wash.map(new Function<Cab, String>() {
            @Override
            public String call(Cab cab) throws Exception {
                return String.valueOf(Math.round(Double.valueOf(cab.getTrip_distance())));
            }
        });

        Map<String, Long> spmap = dtdistinct.countByValue();
        return spmap;
    }
}
