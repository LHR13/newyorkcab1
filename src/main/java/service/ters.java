package service;

import com.lhr13.newyorkcab.dao.DayDAO;
import com.lhr13.newyorkcab.pojo.Day;
import org.springframework.beans.factory.annotation.Autowired;

public class ters {
    @Autowired
    public DayDAO dayDAO;

    public void sss() {
        Day day = new Day();
        day.setId((long)1);
        day.setDay("333");
        day.setCount((long) 123);
        System.out.println(dayDAO.findAll());
        System.out.println(day.toString());
        dayDAO.save(day);
    }
    public static void main(String[] args) throws Exception {

//        new BoomDay().run();
//        new LongOrShort().run();
        new MoreCustomer().run();
//        new HotAera().run();


    }
}
