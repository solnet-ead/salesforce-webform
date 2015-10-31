package nz.co.solnet.salesforce.webform.model.sf;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateResponse {
	private String id;
	private boolean success;
	private Collection<String> errors;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Collection<String> getErrors() {
		return errors;
	}

	public void setErrors(Collection<String> errors) {
		this.errors = errors;
	}

	public void addError(String error) {
		if (errors == null) {
			errors = new ArrayList<String>();
		}
		errors.add(error);
	}
}
