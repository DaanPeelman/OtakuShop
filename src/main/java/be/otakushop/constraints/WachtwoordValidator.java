package be.otakushop.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WachtwoordValidator implements ConstraintValidator<Wachtwoord, String> {
	private final static String WACHTWOORD_REGEX = "^(?=.*?[a-z])(?=.*?[0-9]).{6,}$";
	
	@Override
	public void initialize(Wachtwoord wachtwoord) {
	}
	
	@Override
	public boolean isValid(String wachtwoord, ConstraintValidatorContext context) {
		if(wachtwoord == null || wachtwoord.equalsIgnoreCase("")) {
			return true;
		}
		
		return wachtwoord.matches(WACHTWOORD_REGEX);
	}
}
