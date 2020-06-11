package service;

import com.lhr13.newyorkcab.dao.DayDAO;
import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import scala.Serializable;

import java.util.Map;

public class BoomDay implements Serializable {
    @Autowired
    private DayDAO dayDAO;

    public Map<String, Long> run() throws Exception {
        JavaRDD<Cab> cabrecord = new CatchData().CatchData();


        JavaRDD<Cab> wash = cabrecord.filter(new Function<Cab, Boolean>() {
            @Override
            public Boolean call(Cab cab) throws Exception {
                return cab.getPickup_datatime() != "null" && cab != null;
            }
        });

        JavaRDD<String> day = wash.map(new Function<Cab, String>() {
            @Override
            public String call(Cab cab) throws Exception {
                return cab.getPickup_datatime().substring(8,10);
            }
        });

//        PairFunction<String, String, Long> keyData = (PairFunction<String, String, Long>) s -> new Tuple2(s, (long)1);
//
//        JavaPairRDD<String, Long> dayPairs = day.mapToPair(keyData);
//
//        JavaPairRDD<String, Long> endPairs = dayPairs.reduceByKey(new Function2<Long, Long, Long>() {
//            @Override
//            public Long call(Long aLong, Long aLong2) throws Exception {
//                return aLong + aLong2;
//            }
//        });

        Map<String, Long> map = day.countByValue();
        return map;

//        SQLContext sqlContext = new SQLContext(sc);
//        String url = "jdbc:mysql://localhost:3306/day";
//        Properties connectionProperties = new Properties();
//        connectionProperties.put("user", "root");
//        connectionProperties.put("password", "lhr13");
//        connectionProperties.put("driver", "com.mysql.jdbc.Driver");
//
//        JavaRDD<Row> mapRDD = endPairs.map(new Function<Tuple2<String, Long>, Row>() {
//            @Override
//            public Row call(Tuple2<String, Long> stringLongTuple2) throws Exception {
//                return RowFactory.create(stringLongTuple2._1, stringLongTuple2._2);
//            }
//        });
//
//        List structFields = new ArrayList();
//        structFields.add(DataTypes.createStructField("day", DataTypes.StringType, true));
//        structFields.add(DataTypes.createStructField("count", DataTypes.LongType, true));
//
//        StructType structType = DataTypes.createStructType(structFields);
//
//        Dataset<Row> dayDF = sqlContext.createDataFrame(mapRDD, structType);
//
//        dayDF.write().mode("append").jdbc(url,"day",connectionProperties);
//
//        endPairs.saveAsTextFile("/media/hadoop/DATA/myJavaProjections/output2");
    }
}
