package de.drippinger.serviceExtractor.exception;

/**
 * ExtractorException
 *
 * @author Dennis Rippinger
 */
public class ExtractorException extends Exception {

	public ExtractorException(String message) {
		super(message);
	}

	public ExtractorException(String message, Throwable cause) {
		super(message, cause);
	}

}
