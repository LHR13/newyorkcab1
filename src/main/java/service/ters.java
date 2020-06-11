package service;

import com.lhr13.newyorkcab.dao.DayDAO;
import com.lhr13.newyorkcab.pojo.Day;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ters {
    @Autowired
    public DayDAO dayDAO;

    public int sss(String dates) {
//        Day day = new Day();
//        day.setDay("fsdljf");
//        day.setCount((long) 123);
//        System.out.println(day.toString());
//        dayDAO.save(day);

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
    public static void main(String[] args) throws Exception {

//        new BoomDay().run();
//        new LongOrShort().run();
//        new MoreCustomer().run();
//        new HotAera().run();
//        System.out.println(new ters().sss("2020-06-11"));
//        new WeekBoomDay().run();
//        new MoreCustomerTime().run();
        new GetPlatoonTime().run();

    }
}
