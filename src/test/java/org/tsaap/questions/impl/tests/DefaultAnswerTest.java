package org.tsaap.questions.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.impl.DefaultAnswer;

/**
 * Test class for DefaultAnswer.
 * 
 * @author David Lericolais
 * 
 */
public class DefaultAnswerTest {

	private DefaultAnswer mDefaultAnswer;

	/**
	 * Initialization run before each test.
	 */
	@Before
	public final void setUp() {

		mDefaultAnswer = new DefaultAnswer();
	}

	/**
	 * Tests for all getters and setters of the class.
	 */
	@Test
	public final void attributesTest() {

		mDefaultAnswer.setTextValue("TextValue");
		assertEquals("TextValue", mDefaultAnswer.getTextValue());

		final float percentCredit = 0.5f;
		mDefaultAnswer.setPercentCredit(percentCredit);
		assertEquals(percentCredit, mDefaultAnswer.getPercentCredit(), 0f);

		mDefaultAnswer.setIdentifier("Identifier");
		assertEquals("Identifier", mDefaultAnswer.getIdentifier());

		mDefaultAnswer.setFeedback("Feedback");
		assertEquals("Feedback", mDefaultAnswer.getFeedBack());

	}

	/**
	 * Test for the hashCode function.
	 */
	@Test
	public final void hashCodeTest() {

		mDefaultAnswer.setIdentifier("Identifier");

		String identifier = "Identifier";
		int hashCode = identifier.hashCode();
		assertEquals(hashCode, mDefaultAnswer.hashCode());
	}

	/**
	 * Test for the equals function.
	 */
	@Test
	public final void equalsTest() {

		mDefaultAnswer.setIdentifier("Identifier1");

		DefaultAnswer defaultAnswer2 = new DefaultAnswer();
		defaultAnswer2.setIdentifier("Identifier2");

		// Same object
		assertTrue(mDefaultAnswer.equals(mDefaultAnswer));

		// Null object
		assertFalse(mDefaultAnswer.equals(null));

		// Different object
		assertFalse(mDefaultAnswer.equals(new Integer(1)));

		// Other question with a different identifier
		assertFalse(mDefaultAnswer.equals(defaultAnswer2));

		defaultAnswer2.setIdentifier("Identifier1");
		// Other question with the same identifier
		assertTrue(mDefaultAnswer.equals(defaultAnswer2));
	}

}
