package util;

import error.Error;

import java.util.ArrayList;

public class ErrorManager {
    public static String getErrorMessages(ArrayList<Error> errors) {
        StringBuilder messages = new StringBuilder();

        for (Error error : errors) {
            messages.append(error.getMsg().concat("|"));
        }

        return messages.toString();
    }
}
