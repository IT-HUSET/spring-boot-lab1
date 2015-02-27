package se.ithuset.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ithuset.pojos.DataStuff;
import se.ithuset.repositories.DataRepository;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private DataRepository repo;


    @RequestMapping("/myrest")
    public DataStuff getData() {
        DataStuff ds = new DataStuff();
        ds.setId(5);
        ds.setData("Hello IT-HUSET!");
        return  ds;
    }

    @RequestMapping("/fromdatabase")
    public List<DataStuff> retrieveFromDatabase() {
        return repo.findAll();
    }

}
