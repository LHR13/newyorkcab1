package service;


import com.lhr13.newyorkcab.dao.DayDAO;
import com.lhr13.newyorkcab.pojo.Cab;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import scala.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class WeekBoomDay implements Serializable {
    @Autowired
    private DayDAO dayDAO;

    public Map<String, Long> run() throws Exception {
        JavaRDD<Cab> cabrecord = new CatchData().CatchData();

        JavaRDD<Cab> wash = cabrecord.filter((Function<Cab, Boolean>) cab
                -> cab.getPickup_datatime() != "null"
                && cab != null);

        JavaRDD<String> day = wash.map((Function<Cab, String>) cab
                -> cab.getPickup_datatime().substring(0,10));


        JavaRDD<String> day2 = day.map(new Function<String, String>() {
            @Override
            public String call(String dates) throws Exception {
                int numday = weeknum(dates);
                if (numday == 1) {
                    return "1";
                }else if (numday == 2) {
                    return "2";
                }else if (numday == 3) {
                    return "3";
                }else if (numday == 4) {
                    return "4";
                }else if (numday == 5) {
                    return "5";
                }else if (numday == 6) {
                    return "6";
                }else {
                    return "7";
                }
            }
            private int weeknum(String dates) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                Date d=null;
                try {
                    d=f.parse(dates);
                } catch (ParseException e) {

                    e.printStackTrace();
                }
                cal.setTime(d);
                int w=cal.get(Calendar.DAY_OF_WEEK)-1;
                if(w==0) w=7;
                return w;
            }
        });

        Map<String, Long> map = day2.countByValue();

        return map;

    }
}
