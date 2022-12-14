package com.authentication.service.authentication.service;
import com.authentication.service.authentication.model.User;
import com.authentication.service.authentication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Before // Before Each Test Case, ready the mocked data
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void getAllUsersTest(){
        List<User> userList = new ArrayList<User>();
        userList.add(new User(new ObjectId("636dc82375b6a55ee5656cf8"),"Manish Kumar Kushwaha","mkk9313@gmail.com","12345"));
        userList.add(new User(new ObjectId("636dc82375b6a55ee5656cf7"),"Manish Kumar Kushwaha","mkk9313@gmail.com","12345"));
        userList.add(new User(new ObjectId("636dc82375b6a55ee5656cf6"),"Manish Kumar Kushwaha","mkk9313@gmail.com","12345"));

        when(repository.findAll()).thenReturn(userList);

        List<User> users =service.getAll();

        assertEquals(3,users.size());
    }

    @Test
    public  void userSignUpTest() throws Exception {
        User user = new User(new ObjectId("636dc82375b6a55ee5656cf8"),"Manish Kumar Kushwaha","mkk9313@gmail.com","12345");


        when(repository.getUsersByName(user.getUsername())).thenReturn(user);

        Map<String,Object> req = new HashMap<>();
        req.put("id",new ObjectId("636dc82375b6a55ee5656cf1"));
        req.put("username","Manish");
        req.put("password","12345");
        req.put("confirm password","12345");
        req.put("email","mkk9313632001");

        String res = service.signUp(req);

        assertEquals(res,"{\"message\":\"Successfully Created A User\",\"data\":null}");
    }





}
