package nz.co.solnet.salesforce.webform.model.sf;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Lead {

	@JsonProperty("FirstName")
	private String firstName;

	@JsonProperty("LastName")
	private String lastName;

	@JsonProperty("Company")
	private String company;

	@JsonProperty("Phone")
	private String phone;

	@JsonProperty("Email")
	private String email;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("LeadSource")
	private String leadSource;

	@JsonProperty("Rating")
	private String rating;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return StringUtils.isBlank(company) ? "n/a" : company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return new StringBuilder("Website inquiry from ").append(firstName).append(" ").append(lastName)
				.append(" [auto-generated lead]\n--------------------\n").append(description).toString();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

}
