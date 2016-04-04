

package hu.codingmentor.annotations;

import hu.codingmentor.UserDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DateValidator implements ConstraintValidator<DateAnnotation, UserDTO>{

    @Override
    public void initialize(DateAnnotation constraintAnnotation) {
      
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
      return user.getDateOfBirth().isBefore(user.getRegistrationDate());
    }

}
