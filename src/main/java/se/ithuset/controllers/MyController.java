package se.ithuset.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ithuset.pojos.DataStuff;

@RestController
public class MyController {

    @RequestMapping("/myrest")
    public DataStuff getData() {
        DataStuff ds = new DataStuff();
        ds.setId(5);
        ds.setData("Hello IT-HUSET!");
        return  ds;
    }

}
