package org.tsaap.questions.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.tsaap.questions.UserResponseException;

/**
 * Test class for UserResponseException.
 * 
 * @author David Lericolais
 * 
 */
public class UserResponseExceptionTest {

	/**
	 * Verify the exception is created correctly.
	 */
	@Test
	public final void attributesTest() {

		String exceptionMessage = "Exception";

		UserResponseException userResponseException = new UserResponseException(exceptionMessage);

		assertEquals(exceptionMessage, userResponseException.getMessage());
	}

}
