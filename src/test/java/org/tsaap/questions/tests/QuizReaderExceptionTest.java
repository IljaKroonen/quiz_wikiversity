package org.tsaap.questions.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.tsaap.questions.QuizReaderException;

/**
 * Test class for QuizReaderException.
 * 
 * @author David Lericolais
 * 
 */
public class QuizReaderExceptionTest {

	/**
	 * Verify the exception is created correctly.
	 */
	@Test
	public final void attributesTest() {

		String exceptionMessage = "Exception";

		QuizReaderException quizReaderException = new QuizReaderException(exceptionMessage);

		assertEquals(exceptionMessage, quizReaderException.getMessage());
	}

}
