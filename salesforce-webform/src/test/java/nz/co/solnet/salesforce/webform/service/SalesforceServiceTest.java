package nz.co.solnet.salesforce.webform.service;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import nz.co.solnet.salesforce.webform.Application;
import nz.co.solnet.salesforce.webform.model.ContactForm;
import nz.co.solnet.salesforce.webform.model.sf.AuthToken;
import nz.co.solnet.salesforce.webform.model.sf.CreateResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class SalesforceServiceTest {

	@Autowired
	private SalesforceService service;

	@Test
	public void addInquiry() {
		ContactForm form = new ContactForm();
		form.setCompany("jUnit");
		form.setEmail("test@junit.com");
		form.setFirstname("Jay");
		form.setLastname("Unit");
		form.setMessage("This is not a real lead.");
		form.setPhone("0800 83 83 83");
		CreateResponse response = service.addInquiry(form);
		assertNotNull(response);
		assertTrue(response.isSuccess());
	}

	@Test
	public void testGetToken() {
		AuthToken token = service.getAuthToken();
		assertNotNull(token);
	}
}
