package com.lhr13.newyorkcab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/show")
public class ShowController {

    @GetMapping("/boomday")
    public String boom() {
        return "BoomDay.html";
    }

    @GetMapping("/longorshort")
    public String los() {
        return "LongOrShort.html";
    }

    @GetMapping("/weekboomday")
    public String wbd() {
        return "WeekBoomDay.html";
    }

    @GetMapping("/morecustomertime")
    public String mct() {
        return "MoreCustomerTime.html";
    }

    @GetMapping("/getplatoontime")
    public String gpt() {
        return "GetPlatoonTime.html";
    }

    @GetMapping("/welcome")
    public String wel() {
        return "welcome.html";
    }

}
