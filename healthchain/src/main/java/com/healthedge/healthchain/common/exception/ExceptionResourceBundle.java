package com.healthedge.healthchain.common.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class ExceptionResourceBundle {
    private ResourceBundle resourceBundle;

    private static ExceptionResourceBundle ourInstance = new ExceptionResourceBundle();

    public static ExceptionResourceBundle getInstance() {
        return ourInstance;
    }

    private ExceptionResourceBundle() {
        resourceBundle = ResourceBundle.getBundle("errorMessage", Locale.getDefault());
    }

    public String getErrorMessage (String errorCode) {
        return resourceBundle.getString(errorCode);
    }
}
