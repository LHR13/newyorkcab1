package com.lhr13.newyorkcab.data;

import com.lhr13.newyorkcab.dao.DayDAO;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    public DayDAO dayDAO;

    public void sss() {
//        Day day = new Day();
//        day.setDay("fsdljf");
//        day.setCount((long) 123);
//        System.out.println(day.toString());
//        dayDAO.save(day);
//
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        Date d=null;
//        try {
//            d=f.parse(dates);
//        } catch (ParseException e) {
//
//            e.printStackTrace();
//        }
//        cal.setTime(d);
//        int w=cal.get(Calendar.DAY_OF_WEEK)-1;
//        if(w==0) w=7;
//        return w;

//        double a = 1.8;
//        return a - (int)a;
        double[] s = new double[]{123.456, 456.456};
        Vector vector = Vectors.dense(s);
        System.out.println(vector.apply(0));
        System.out.println(vector.toDense());
        System.out.println(vector);


    }
    public static void main(String[] args) throws Exception {

//        new BoomDay().run();
//        new LongOrShort().run();
//        new MoreCustomer().run();
//        new HotAera().run();
//        new test().sss();
//        new WeekBoomDay().run();
//        new MoreCustomerTime().run();
//        new GetPlatoonTime().run();
//        new DistanceCount().run();
        new GeoCenter().run();
    }
}
