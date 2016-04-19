package hu.codingmentor.annotations;

import hu.codingmentor.dto.UserDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateAnnotation, UserDTO> {

    @Override
    public void initialize(DateAnnotation constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        if (user.getDateOfBirth() != null) {
            return user.getDateOfBirth().isBefore(user.getRegistrationDate());
        } else {
            return true;
        }

    }

}
