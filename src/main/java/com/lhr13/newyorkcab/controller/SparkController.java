package com.lhr13.newyorkcab.controller;

import com.lhr13.newyorkcab.dao.DayDAO;
import com.lhr13.newyorkcab.dao.LorSDAO;
import com.lhr13.newyorkcab.dao.MctDAO;
import com.lhr13.newyorkcab.dao.WbdDAO;
import com.lhr13.newyorkcab.pojo.Day;
import com.lhr13.newyorkcab.pojo.LorS;
import com.lhr13.newyorkcab.pojo.Mct;
import com.lhr13.newyorkcab.pojo.Wbd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.BoomDay;
import service.LongOrShort;
import service.MoreCustomerTime;
import service.WeekBoomDay;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spark")
public class SparkController {
    @Autowired
    private DayDAO dayDAO;
    @Autowired
    private LorSDAO lorSDAO;
    @Autowired
    private WbdDAO wbdDAO;
    @Autowired
    private MctDAO mctDAO;


    @RequestMapping("/bd")
    public void hello() throws Exception {
        Map<String, Long> map = new BoomDay().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            Day day = new Day();
            day.setDay(m.getKey());
            day.setCount(m.getValue());
            dayDAO.save(day);
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }

    @RequestMapping("/lors")
    public void lors() throws Exception {
        Map<String, Long> map = new LongOrShort().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            LorS lorS = new LorS();
            lorS.setLongorshort(m.getKey());
            lorS.setCount(m.getValue());
            lorSDAO.save(lorS);
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }

    @RequestMapping("/wbd")
    public void wbd() throws Exception {
        Map<String, Long> map = new WeekBoomDay().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            Wbd wbd = new Wbd();
            wbd.setWeekday(m.getKey());
            wbd.setCount(m.getValue());
            wbdDAO.save(wbd);
        }
    }

    @RequestMapping("/mct")
    public void mct() throws Exception {
        Map<String, Long> map = new MoreCustomerTime().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            Mct mct = new Mct();
            mct.setHour(m.getKey());
            mct.setCount(m.getValue());
            mctDAO.save(mct);
        }
    }
}
