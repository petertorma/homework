/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.codingmentor.annotations.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }
 
    public BadRequestException(String string) {
        super(string);
    }
}
