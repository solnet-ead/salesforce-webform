package nz.co.solnet.salesforce.webform.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nz.co.solnet.salesforce.webform.model.ContactForm;
import nz.co.solnet.salesforce.webform.service.SalesforceService;

@Controller
public class ContactUsController {

	@Autowired
	private SalesforceService service;

	/**
	 * Render the form UI.
	 * 
	 * @param contactForm
	 *            model backing the form.
	 * @return form UI view.
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String formView(ContactForm contactForm) {
		return "viewForm";
	}

	/**
	 * Process submitted form.
	 * 
	 * @param contactForm
	 *            the submitted form.
	 * @param bindingResult
	 *            validation result.
	 * @return success view or back to form UI view on validation failure.
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String formSubmit(@Valid ContactForm contactForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "viewForm";
		}

		/*
		 * Push data into salesforce in a fire-and-forget fashion, should be
		 * good enough for now.
		 */
		service.addInquiryAsync(contactForm);

		return "submitForm";
	}
}
