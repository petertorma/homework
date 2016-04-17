package hu.codingmentor.dto.test;

import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class MobileDTOTest {

    private static final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private static final Validator validatior = vf.getValidator();

    @Test
    public void shouldRaiseExceptionInvalidPrice() {
        MobileDTO mobile = new MobileDTO("xperia  z", "sony", 0, 1);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(mobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(0, violations.get(0).getInvalidValue());
        vf.close();

    }

    @Test
    public void shouldRaiseExceptionInvalidPiece() {
        MobileDTO mobile = new MobileDTO("xperia z", "sony", 50000, -1);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(mobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(-1, violations.get(0).getInvalidValue());
        vf.close();
    }

    @Test
    public void shouldRaiseExceptionNoType() {
        MobileDTO mobile = new MobileDTO(null, "sony", 5000, 1);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(mobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.get(0).getInvalidValue());
        vf.close();

    }

    @Test
    public void shouldRaiseExceptionCauseTooShortManufacturer() {
        MobileDTO mobile = new MobileDTO("Xperia p", "SO", 40000, 1);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(mobile));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("SO", violations.get(0).getInvalidValue());
        vf.close();
    }

    @Test
    public void shouldNotRaiseException() {
        MobileDTO mobile = new MobileDTO("xperia z", "sony", 4000, 3);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(mobile));
        Assert.assertEquals(0, violations.size());
        vf.close();
    }

}
