package org.myapp.chat_api.models.error;

public class AppExUsersException extends Exception {
    private static final String EXCEPTION_MESSAGE  = "Invalid operation for users";

    public AppExUsersException() {
        super(EXCEPTION_MESSAGE );
    }

    public AppExUsersException(String message) {
        super(message);
    }
}
