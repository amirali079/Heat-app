package HeatApp.exception;

public class UsernameExistException extends RuntimeException {
    public UsernameExistException(String username){
        super("the "+username+" username already exist!");
    }
}