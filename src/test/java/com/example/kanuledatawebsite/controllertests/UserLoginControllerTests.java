package com.example.kanuledatawebsite.controllertests;

import com.example.kanuledatawebsite.controller.UserLoginController;
import com.example.kanuledatawebsite.dataaccesslayer.UserRepository;
import com.example.kanuledatawebsite.entities.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserLoginController.class)
public class UserLoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private DataSource dataSource;



    @Test
    public void testViewStartPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("startpage"));
    }

    @Test
    public void testShowRegistrationForm() throws Exception{
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration_form"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", Matchers.equalTo(new User())));
    }

    @Test
    public void testProcessRegistter() throws Exception{
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);


        this.mockMvc.perform(post("/process_register")
                        .param("userName","testuser")
                        .param("email","testemail@googlemail.com")
                        .param("password","testpassword")
                        .param("id","1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("register_success"));
        verify(userRepository,times(1)).save(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getUserName()).isEqualTo("testuser");
        assertThat(userArgumentCaptor.getValue().getEmail()).isEqualTo("testemail@googlemail.com");
        assertThat(new BCryptPasswordEncoder().matches("testpassword",userArgumentCaptor.getValue().getPassword())).isTrue();


    }

    @Test
    public void testViewLoginpage() throws Exception{
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }


}
