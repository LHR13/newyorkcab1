package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.Action;
import java.io.Serializable;

@Component
public class PickUpTime implements Serializable {

    @Autowired
    private Init init;

    public void pickUp() throws Exception {
        init.run();


    }
}
