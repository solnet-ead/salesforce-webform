package nz.co.solnet.salesforce.webform.service;

import java.util.Arrays;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import nz.co.solnet.salesforce.webform.model.ContactForm;
import nz.co.solnet.salesforce.webform.model.sf.AuthToken;
import nz.co.solnet.salesforce.webform.model.sf.CreateResponse;
import nz.co.solnet.salesforce.webform.model.sf.Lead;

@Service
public class SalesforceService {
	private static final Logger log = LoggerFactory.getLogger(SalesforceService.class);

	@Value("${salesforce.api.url.token}")
	private String propUrlToken;

	@Value("${salesforce.api.path.lead}")
	private String propUrlPathLead;

	@Value("${salesforce.sobjects.lead.leadsource}")
	private String propUrlLeadSource;

	@Value("${salesforce.sobjects.lead.rating}")
	private String propUrlLeadRating;

	@Autowired
	private RestTemplate template;

	@Autowired
	private HttpEntity<MultiValueMap<String, String>> authRequest;

	/**
	 * Create a new Lead object in Salesforce asynchronously.
	 * 
	 * @param form
	 *            data used to create the Lead.
	 * @return An async handle wrapped around Salesforce Lead creation response.
	 */
	@Async
	public Future<CreateResponse> addInquiryAsync(ContactForm form) {
		return new AsyncResult<CreateResponse>(addInquiry(form));
	}

	/**
	 * Create a new Lead object in Salesforce.
	 * 
	 * @param form
	 *            data used to create the Lead.
	 * @return Salesforce Lead creation response.
	 */
	public CreateResponse addInquiry(ContactForm form) {
		CreateResponse response = new CreateResponse();

		/*
		 * Obtain OAUTH access credentials. There is really no need to call this
		 * each time, since tokens last a while, but this will have to do for
		 * now since we have no where to store previously obtained tokens.
		 */
		AuthToken token = getAuthToken();
		if (token == null) {
			response.setSuccess(false);
			response.addError("Unable to obtain auth token");
			return response;
		}

		try {
			/* POST new Lead to Salesforce. */
			return template.exchange(new StringBuilder(token.getInstanceUrl()).append(propUrlPathLead).toString(),
					HttpMethod.POST, new HttpEntity<Lead>(createLead(form), createAPIHeader(token.getAccessToken())),
					CreateResponse.class).getBody();
		} catch (Exception e) {
			log.error("Error creating lead", e);
			response.setSuccess(false);
			response.addError(e.getLocalizedMessage());
		}
		return response;
	}

	/**
	 * Retrieve OAUTH access credentials that can be used to access the
	 * Salesforce API.
	 * 
	 * @return AuthToken instance.
	 */
	public AuthToken getAuthToken() {
		try {
			/* Request access token from Salesforce. */
			return template.exchange(propUrlToken, HttpMethod.POST, authRequest, AuthToken.class).getBody();
		} catch (Exception e) {
			log.error("Error obtaining auth token", e);
		}
		return null;
	}

	/**
	 * Convert ContactForm (UI captured user data) into a new Lead (Salesforce
	 * entity) object.
	 * 
	 * @param form
	 *            user submitted form.
	 * @return Salesforce lead object.
	 */
	private Lead createLead(ContactForm form) {
		Lead lead = new Lead();
		lead.setCompany(form.getCompany());
		lead.setDescription(form.getMessage());
		lead.setEmail(form.getEmail());
		lead.setFirstName(form.getFirstname());
		lead.setLastName(form.getLastname());
		lead.setPhone(form.getPhone());
		lead.setLeadSource(propUrlLeadSource);
		lead.setRating(propUrlLeadRating);
		return lead;
	}

	/**
	 * Create HTTP header with OAUTH access credentials set.
	 * 
	 * @param authToken
	 *            OAUTH access credentials.
	 * @return HttpHeaders instance.
	 */
	private static final HttpHeaders createAPIHeader(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", new StringBuilder("Bearer ").append(authToken).toString());
		return headers;
	}
}
