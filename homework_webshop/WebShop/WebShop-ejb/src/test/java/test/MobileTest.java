package test;


import hu.codingmentor.dto.MobileDTO;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class MobileTest {
 @Test
    public void shouldRaiseExceptionInvalidPrice() {
        MobileDTO mobile = new MobileDTO("xperia  z", "sony", 0, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(mobile);
        Assert.assertEquals(1, violations.size());
 
    }
 
    @Test
    public void shouldRaiseExceptionInvalidPiece() {
        MobileDTO mobile = new MobileDTO("xperia z", "sony", 50000, -1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(mobile);
        Assert.assertEquals(1, violations.size());
 
    }
 
    @Test
    public void shouldRaiseExceptionNoType() {
        MobileDTO mobile = new MobileDTO(null, "sony", 5000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(mobile);
        Assert.assertEquals(1, violations.size());
 
    }
 
 
    @Test
    public void shouldRaiseExceptionCauseTooShortManufacturer() {
        MobileDTO mobile = new MobileDTO("Xperia p", "SO",40000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(mobile);
        Assert.assertEquals(1, violations.size());
    }
    
    @Test
    public void shouldNotRaiseException() {
        MobileDTO mobile = new MobileDTO("xperia z", "sony",4000, 3);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }
 
}
