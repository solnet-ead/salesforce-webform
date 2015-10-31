package nz.co.solnet.salesforce.webform;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync
@PropertySource("classpath:/config/application.properties")
public class Application {
	private static final String AUTH_GRANT_TYPE = "password";

	@Value("${salesforce.app.client.id}")
	private String propAppClientId;

	@Value("${salesforce.app.client.secret}")
	private String propAppClientSecret;

	@Value("${salesforce.user.name}")
	private String propUsername;

	@Value("${salesforce.user.password}")
	private String propPassword;

	@Value("${salesforce.user.securitytoken}")
	private String propSecurityToken;

	/**
	 * Application wide shared RestTemplate.
	 * 
	 * @return RestTemplate instance.
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}

	/**
	 * HTTP request entity that when posted, will request for an OAUTH token.
	 * 
	 * @return HttpEntity instance.
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public HttpEntity<MultiValueMap<String, String>> authRequest() {
		HttpHeaders headers = new HttpHeaders();

		/* Specify content content negotiation headers. */
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		/*
		 * Specify request payload, using username-password OAUTH authentication
		 * flow. Reference:
		 * https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/
		 * api_rest/intro_understanding_username_password_oauth_flow.htm
		 */
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", AUTH_GRANT_TYPE);
		params.add("client_id", propAppClientId);
		params.add("client_secret", propAppClientSecret);
		params.add("username", propUsername);
		params.add("password", new StringBuilder(propPassword).append(propSecurityToken).toString());

		return new HttpEntity<MultiValueMap<String, String>>(params, headers);
	}

	/**
	 * Application entry point for spring boot with embedded servlet container.
	 * 
	 * @param args
	 *            main function args.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
