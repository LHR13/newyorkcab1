package com.lhr13.newyorkcab.data;

import com.lhr13.newyorkcab.dao.DayDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    public DayDAO dayDAO;

    public double sss() {
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

        double a = 1.8;
        return a - (int)a;

    }
    public static void main(String[] args) throws Exception {

//        new BoomDay().run();
//        new LongOrShort().run();
//        new MoreCustomer().run();
//        new HotAera().run();
//        System.out.println(new ters().sss());
//        new WeekBoomDay().run();
//        new MoreCustomerTime().run();
//        new GetPlatoonTime().run();
//        new DistanceCount().run();
        new Center().run();
    }
}
