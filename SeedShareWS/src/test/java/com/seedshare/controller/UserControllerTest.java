package com.seedshare.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.seedshare.controller.user.UserControllerImpl;
import com.seedshare.entity.User;
import com.seedshare.repository.UserRepository;
import com.seedshare.service.user.UserServiceImpl;
import com.seedshare.utils.IntegrationTestUtil;

/**
 * Test class for the {@link com.seedshare.controller.user.UserController}
 *
 * @author joao.silva
 * @author eduardo.barbosa
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = {UserControllerImpl.class, UserServiceImpl.class})
@ContextConfiguration
@WebAppConfiguration
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private UserRepository userRepository;

	private User validUser;
	@Before
	public void setup() {
		this.validUser = new User("04303145076", "João Pedro", "jpedross1999@gmail.com", "12345678", false);
		given(this.userRepository.save(any(User.class))).willReturn(this.validUser);
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	@Test
    public void createValidUser() throws Exception {
		User returnUser = new User(this.validUser.getCpf(), this.validUser.getName(), this.validUser.getEmail(), this.validUser.getPassword(), this.validUser.getIsLegalPerson());
		returnUser.clearPrivateData();
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(this.validUser))
        	)
            .andExpect(status().isCreated())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithoutName() throws Exception {
		User invalidUserWithName = new User("04303145076", null, "jpedross1999@gmail.com", "12345678", false);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(invalidUserWithName))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithNothingExceptionName() throws Exception {
		User invalidUserWithNothing = new User(null,"Eduardo Barbosa Viegas", null,null,null);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(invalidUserWithNothing))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithInvalidCPF() throws Exception {
		User userWithInvalidCPF = new User("04103145076", "João Pedro", "jpedross1999@gmail.com", "12345678", false);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(userWithInvalidCPF))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithInvalidEmail() throws Exception {
		User userWithInvalidEmail = new User("04303145076", "Eduardo Barbosa Viegas", "eduardo@", "12345678", false);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(userWithInvalidEmail))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithInvalidPassword() throws Exception {
		User userWithInvalidPassword = new User("04303145076", "João Pedro", "jpedross1999@gmail.com", "123", false);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(userWithInvalidPassword))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
}
