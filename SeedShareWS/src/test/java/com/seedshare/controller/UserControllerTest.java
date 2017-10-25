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
import com.seedshare.entity.address.Address;
import com.seedshare.entity.address.City;
import com.seedshare.entity.address.Country;
import com.seedshare.entity.address.State;
import com.seedshare.entity.user.User;
import com.seedshare.repository.AddressRepository;
import com.seedshare.repository.StateRepository;
import com.seedshare.repository.UserRepository;
import com.seedshare.repository.CityRepository;
import com.seedshare.repository.CountryRepository;
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
	
	@MockBean
	private AddressRepository addressRepository;
	
	@MockBean
	private StateRepository stateRepository;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	private User validUser;
	
	private Address address;
	
	private City city;
	
	private State state;
	
	private Country country;
	
	@Before
	public void setup() {
		this.country = new Country("Teste");
		this.state = new State("Teste", this.country);
		this.city = new City("Teste", this.state);
		this.address = new Address(this.city, 985, "Rio Branco", "Rua Maricás", null, null, 1);
		this.validUser = new User("04303145076", "João Pedro", "jpedross1999@gmail.com", "12345678", false, this.address, 984401159L);
		given(this.userRepository.save(any(User.class))).willReturn(this.validUser);
		given(this.addressRepository.save(any(Address.class))).willReturn(this.address);
		given(this.cityRepository.save(any(City.class))).willReturn(this.city);
		given(this.stateRepository.save(any(State.class))).willReturn(this.state);
		given(this.countryRepository.save(any(Country.class))).willReturn(this.country);
		given(this.userRepository.findOneByEmail(any(String.class))).willReturn(null);
		given(this.userRepository.findOneByCpf(any(String.class))).willReturn(null);

		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	@Test
    public void createInvalidUserWithoutName() throws Exception {
		User invalidUserWithName = new User("04303145076", null, "jpedross1999@gmail.com", "12345678", false, this.address,984401159L);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(invalidUserWithName))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithNothingExceptName() throws Exception {
		User invalidUserWithNothing = new User(null,"Eduardo Barbosa Viegas", null,null,null, this.address,984401159L);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(invalidUserWithNothing))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithInvalidCPF() throws Exception {
		User userWithInvalidCPF = new User("04103145076", "João Pedro", "jpedross1999@gmail.com", "12345678", false, this.address,984401159L);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(userWithInvalidCPF))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithInvalidEmail() throws Exception {
		User userWithInvalidEmail = new User("04303145076", "Eduardo Barbosa Viegas", "eduardo@", "12345678", false, this.address,984401159L);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(userWithInvalidEmail))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithInvalidPassword() throws Exception {
		User userWithInvalidPassword = new User("04303145076", "João Pedro", "jpedross1999@gmail.com", "123", false, this.address,984401159L);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(userWithInvalidPassword))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
	
	@Test
    public void createInvalidUserWithoutAddress() throws Exception {
		User userWithInvalidPassword = new User("04303145076", "João Pedro", "jpedross1999@gmail.com", "123", false, null,984401159L);
		mockMvc.perform(post("/user/register/").with(user("user").password("123456789"))
        		.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
        		.content(IntegrationTestUtil.convertObjectToJsonBytes(userWithInvalidPassword))
        	)
            .andExpect(status().isBadRequest())
            .andReturn();
	}
}
