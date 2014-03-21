package org.tsaap.questions.impl.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.impl.DefaultAnswer;

public class DefaultAnswerTest {

	private DefaultAnswer mDefaultAnswer;

	@Before
	public void setUp() {

		mDefaultAnswer = new DefaultAnswer();
	}

	@Test
	public void attributesTest() {

		mDefaultAnswer.setTextValue("TextValue");
		assertEquals("TextValue", mDefaultAnswer.getTextValue());
		
		mDefaultAnswer.setPercentCredit(0.5f);
		assertEquals(0.5f, mDefaultAnswer.getPercentCredit(), 0f);
		
		mDefaultAnswer.setIdentifier("Identifier");
		assertEquals("Identifier", mDefaultAnswer.getIdentifier());
		
		mDefaultAnswer.setFeedback("Feedback");
		assertEquals("Feedback", mDefaultAnswer.getFeedBack());
		
	}
	
	@Test
	public void hashCodeTest(){
		
		mDefaultAnswer.setIdentifier("Identifier");
		int hashCode = mDefaultAnswer.hashCode();
		assertEquals(hashCode, mDefaultAnswer.hashCode());
	}
	
	@Test
	public void equalsTest(){
		
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
