package com.ctfs.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ValidationException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2816201046700746462L;

	public ValidationException(String message) {
        /*super(String.format("Failed to validate against the expected result.\n" +
                "--------------------------\n" +
                "Expected: \"%s\"\n" +
                "Received: \"%s\"", _expected, _received)
        );*/
        super(message);
    }

    public ValidationException(String message, Exception cause) {
        super(message, cause);
    }

    public ValidationException(By locator, String _received){
        super(String.format("Failed to validate the value against the expected result.\n" +
                "--------------------------\n" +
                "Element: \"%s\"\n" +
                "Expected Element Value: \"%s\"", locator.toString(), _received)
        );
    }

    public ValidationException(String _expected, String _received){
        super(String.format("Failed to validate the value against the expected result.\n" +
                "--------------------------\n" +
                "Expected: \"%s\"\n" +
                "Actual Value: \"%s\"", _expected, _received)
        );
    }

    public ValidationException(String _expected, String _received, String _xpath){
        super(String.format("Failed to validate the value against the expected result.\n" +
                "--------------------------\n" +
                "Element: \"%s\"\n"  +
                "Expected: \"%s\"\n" +
                "Actual Value: \"%s\"", _xpath, _expected, _received)
        );
    }

    public ValidationException(String _expected, String _received, WebElement _element){
        super(String.format("Failed to validate the value against the expected result.\n" +
                "--------------------------\n" +
                "Element: \"%s\"\n"  +
                "Expected: \"%s\"\n" +
                "Actual Value: \"%s\"", _element, _expected, _received)
        );
    }

}

