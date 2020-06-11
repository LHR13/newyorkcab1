package com.lhr13.newyorkcab.controller;

import com.lhr13.newyorkcab.dao.*;
import com.lhr13.newyorkcab.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    @Autowired
    private GptDAO gptDAO;

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

    @RequestMapping("/gptfindAll")
    @ResponseBody
    public List<Gpt> gptfindAll() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "passagernum"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "time"));
        return gptDAO.findAll(Sort.by(orders));
//        List all = gptDAO.findAll();
//        Collections.sort(all, new Comparator<Gpt>() {
//            @Override
//            public int compare(Gpt o1, Gpt o2) {
//                int diff = Integer.valueOf(o1.getPassagernum()) - Integer.valueOf(o2.getPassagernum());
//                if (diff > 0) {
//                    return 1;
//                }else if (diff < 0) {
//                    return -1;
//                }else {
//                    return 0;
//                }
//            }
//        });
//        return all;
    }
}
