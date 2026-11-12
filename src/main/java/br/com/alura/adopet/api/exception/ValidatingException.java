package br.com.alura.adopet.api.exception;

public class ValidatingException extends RuntimeException{
    public ValidatingException(String message){
        super(message);
    }
}
