package test;

import hu.codingmentor.dto.UserDTO;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void shouldNotRaiseException() {
        UserDTO user = new UserDTO("TestUsername", "Pdsad5=", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseExceptionNullUsername() {
        UserDTO user = new UserDTO(null, "Pdsad5=", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(user);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionShortUsername() {
        UserDTO user = new UserDTO("ab", "Pdsad5=", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(user);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldNotRaiseExceptionBadPassword() {
        UserDTO user = new UserDTO("TestUsername", "password", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(user);
        Assert.assertEquals(3, violations.size());
    }

    @Test
    public void shouldNotRaiseExceptionNullPassword() {
        UserDTO user = new UserDTO("TestUsername", null, "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(user);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionNullFirstName() {
        UserDTO user = new UserDTO("TestUsername", "Pdsad5=", null, "TestLast", "1995-11-12", "2013-12-12");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(user);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldNotRaiseExceptionNullLastName() {
        UserDTO user = new UserDTO("TestUsername", "Pdsad5=", "TestFirst", null, "1995-11-12", "2013-12-12");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(user);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldNotRaiseExceptionNullRegisterBeforeBirth() {
        UserDTO user = new UserDTO("TestUsername", "Pdsad5=", "TestFirst", "TestLast", "1995-12-13", "1913-12-12");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(user);
        Assert.assertEquals(1, violations.size());
    }
}
