package pl.fkpsystem.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pl.fkpsystem.model.Volunteer;
import pl.fkpsystem.service.AdminService;
import static org.springframework.test
        .web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test
        .web.servlet.result.MockMvcResultMatchers.view;


import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = true)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Before
    public void setUp(){
        List<Volunteer> volunteers = new ArrayList<>();
        Volunteer volunteer1=new Volunteer();
        volunteer1.setName("Karol");
        Volunteer volunteer2=new Volunteer();
        volunteer2.setName("Andrzej");
        Volunteer volunteer3=new Volunteer();
        volunteer3.setName("Anna");
        volunteers.add(volunteer1);
        volunteers.add(volunteer2);
        volunteers.add(volunteer3);

        when(this.adminService.getAllVolunteers()).thenReturn(volunteers);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void volunteerList() throws Exception {
        mockMvc.perform(get("/admin/volunteerList"))
                .andExpect(model().attributeExists("volunteerList"))
                .andExpect(view().name("admin/volunteerList"))
                .andDo(MockMvcResultHandlers.print());
    }
}