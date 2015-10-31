package nz.co.solnet.salesforce.webform.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import nz.co.solnet.salesforce.webform.service.SalesforceService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ContactUsControllerTest {

	private MockMvc mvc;

	@InjectMocks
	private ContactUsController controller;

	@Mock
	private SalesforceService mockService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void formView() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/contact").accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
	}

	@Test
	public void formSubmit() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/contact").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk());
	}
}
