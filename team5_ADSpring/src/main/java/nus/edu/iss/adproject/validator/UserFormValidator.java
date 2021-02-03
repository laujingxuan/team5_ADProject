package nus.edu.iss.adproject.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import nus.edu.iss.adproject.nonEntityModel.UserForm;


@Component
public class UserFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("test");
		UserForm u = (UserForm) target;
		String password = u.getPassword();
		String numRegex   = ".*[0-9].*";
		String LAlphaRegex = ".*[A-Z].*";
		String SAlphaRegex = ".*[a-z].*";
		if ((password.matches(numRegex)&&(password.matches(LAlphaRegex)||password.matches(SAlphaRegex)))==false) {
			errors.rejectValue("password", "password", "Password needs to contain number and alphabets");
		}
		
		if (u.getPassword().equals(u.getConfPassword())==false) {
			errors.rejectValue("password", "password", "Password is inconsistent");
		}
	}
}