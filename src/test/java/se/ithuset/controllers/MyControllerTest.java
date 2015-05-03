package se.ithuset.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import se.ithuset.Application;
import se.ithuset.pojos.DataStuff;
import se.ithuset.repositories.DataRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MyControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private DataRepository repo;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testController() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/myrest")).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }


    @Test
    @Sql({"/test-user-data.sql"})
    public void testDatabaseSetupAndAccess() {
        List<DataStuff> all = repo.findAll();
        Assert.assertTrue(all.size() > 0);
        for (DataStuff dataStuff : all) {
            System.out.println(dataStuff.getData());
        }
    }



}
