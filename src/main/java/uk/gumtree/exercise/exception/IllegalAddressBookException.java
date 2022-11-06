package uk.gumtree.exercise.exception;

public class IllegalAddressBookException extends RuntimeException {

    public IllegalAddressBookException(Exception e) {
        super(e);
    }

    public IllegalAddressBookException(String message){
        super(message);
    }
}
