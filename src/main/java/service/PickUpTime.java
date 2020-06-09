package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class PickUpTime implements Serializable {

    @Autowired
    private BoomDay boomDay;

    public void pickUp() throws Exception {
        boomDay.run();


    }
}
