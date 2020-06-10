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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/find")
public class FindController {
    @Autowired
    private DayDAO dayDAO;
    @Autowired
    private LorSDAO lorSDAO;
    @Autowired
    private WbdDAO wbdDAO;
    @Autowired
    private MctDAO mctDAO;

    @RequestMapping("/dayfindAll")
    @ResponseBody
    public List<Day> dayFindAll() {
        List all = dayDAO.findAll();
        Collections.sort(all, new Comparator<Day>() {
            @Override
            public int compare(Day o1, Day o2) {
                int diff = Integer.valueOf(o1.getDay()) - Integer.valueOf(o2.getDay());
                if (diff > 0) {
                    return 1;
                }else if (diff < 0) {
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        return all;
    }

    @RequestMapping("/lorsfindAll")
    @ResponseBody
    public List<LorS> lorsFindAll() {
        return lorSDAO.findAll();
    }

    @RequestMapping("/wbdfindAll")
    @ResponseBody
    public List<Wbd> wbdfindAll() {
        List all = wbdDAO.findAll();
        Collections.sort(all, new Comparator<Wbd>() {
            @Override
            public int compare(Wbd o1, Wbd o2) {
                int diff = Integer.valueOf(o1.getWeekday()) - Integer.valueOf(o2.getWeekday());
                if (diff > 0) {
                    return 1;
                }else if (diff < 0) {
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        return all;
    }

    @RequestMapping("/mctfindAll")
    @ResponseBody
    public List<Mct> mctfindAll() {
        List all = mctDAO.findAll();
        Collections.sort(all, new Comparator<Mct>() {
            @Override
            public int compare(Mct o1, Mct o2) {
                int diff = Integer.valueOf(o1.getHour()) - Integer.valueOf(o2.getHour());
                if (diff > 0) {
                    return 1;
                }else if (diff < 0) {
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        return all;
    }
}
