package hu.codingmentor.dto;

import hu.codingmentor.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class UserDTOTest {

    private static final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private static final Validator validatior = vf.getValidator();

    @Test
    public void shouldNotRaiseException() {
        UserDTO user = new UserDTO("TestUsername", "Pdsad5=", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(user));
        Assert.assertEquals(0, violations.size());
        vf.close();
    }

    @Test
    public void shouldRaiseExceptionNullUsername() {
        UserDTO user = new UserDTO(null, "Pdsad5=", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(user));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.get(0).getInvalidValue());
        Assert.assertEquals(user.getClass(), violations.get(0).getRootBeanClass());
        vf.close();
    }

    @Test
    public void shouldRaiseExceptionShortUsername() {
        UserDTO user = new UserDTO("ab", "Pdsad5=", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(user));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("ab", violations.get(0).getInvalidValue());
        vf.close();
    }

    @Test
    public void shouldRaiseExceptionBadPassword() {
        UserDTO user = new UserDTO("TestUsername", "password", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(user));
        Assert.assertEquals(3, violations.size());
        Assert.assertEquals("password", violations.get(0).getInvalidValue());
        vf.close();
    }

    @Test
    public void shoultRaiseExceptionShortPassword() {
        UserDTO user = new UserDTO("TestUsername", "Aa=3", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(user));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Aa=3", violations.get(0).getInvalidValue());
        vf.close();
    }

    @Test
    public void shoultRaiseExceptionInvalidPassword() {
        UserDTO user = new UserDTO("TestUsername", "Aa3Aaa", "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(user));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Aa3Aaa", violations.get(0).getInvalidValue());
        vf.close();
    }

    @Test
    public void shouldRaiseExceptionNullPassword() {
        UserDTO user = new UserDTO("TestUsername", null, "TestFirst", "TestLast", "1995-11-12", "2013-12-12");
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(user));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.get(0).getInvalidValue());
        Assert.assertEquals(user.getClass(), violations.get(0).getRootBeanClass());
    }

    @Test
    public void shouldRaiseExceptionNullRegisterBeforeBirth() {
        UserDTO user = new UserDTO("TestUsername", "Pdsad5=", "TestFirst", "TestLast", "1995-12-13", "1913-12-12");
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(user));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(user, violations.get(0).getInvalidValue());
        vf.close();
    }

}
