package com.lhr13.newyorkcab.controller;

import com.lhr13.newyorkcab.dao.*;
import com.lhr13.newyorkcab.data.*;
import com.lhr13.newyorkcab.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
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
    @Autowired
    private GptDAO gptDAO;
    @Autowired
    private DCDAO dcDAO;


    @RequestMapping("/bd")
    public String hello() throws Exception {
        Map<String, Long> map = new BoomDay().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            Day day = new Day();
            day.setDay(m.getKey());
            day.setCount(m.getValue());
            dayDAO.save(day);
        }
        return "success.html";
    }

    @RequestMapping("/lors")
    public String lors() throws Exception {
        Map<String, Long> map = new LongOrShort().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            LorS lorS = new LorS();
            lorS.setLongorshort(m.getKey());
            lorS.setCount(m.getValue());
            lorSDAO.save(lorS);
        }
        return "success.html";
    }

    @RequestMapping("/wbd")
    public String wbd() throws Exception {
        Map<String, Long> map = new WeekBoomDay().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            Wbd wbd = new Wbd();
            wbd.setWeekday(m.getKey());
            wbd.setCount(m.getValue());
            wbdDAO.save(wbd);
        }
        return "success.html";
    }

    @RequestMapping("/mct")
    public String mct() throws Exception {
        Map<String, Long> map = new MoreCustomerTime().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            Mct mct = new Mct();
            mct.setHour(m.getKey());
            mct.setCount(m.getValue());
            mctDAO.save(mct);
        }
        return "success.html";
    }

    @RequestMapping("/gpt")
    public String gpt() throws Exception {
        Map<String, Long> map = new GetPlatoonTime().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            String passagernum = m.getKey().split(",")[0];
            String time = m.getKey().split(",")[1];
            Gpt gpt = new Gpt();
            gpt.setPassagernum(passagernum);
            gpt.setTime(time);
            gpt.setCount(m.getValue());
            gptDAO.save(gpt);
        }
        return "success.html";
    }

    @RequestMapping("/dcct")
    public String dcct() throws Exception {
        Map<String, Long> map = new DistanceCount().run();
        for (Map.Entry<String, Long> m : map.entrySet()) {
            DcCt dcCt = new DcCt();
            dcCt.setDistance(m.getKey());
            dcCt.setCount(m.getValue());
            dcDAO.save(dcCt);
        }
        return "success.html";
    }

    @RequestMapping("/cte")
    public String cte() throws Exception {
        List<List<Double>> lists = new Center().run();
    }

    @RequestMapping("/ce")
    public String ce() {
        return "success.html";
    }

}
