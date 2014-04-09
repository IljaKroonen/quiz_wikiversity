package org.tsaap.questions.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.Answer;
import org.tsaap.questions.UserAnswerBlock;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultUserAnswerBlock;

/**
 * Test class for DefaultUserAnswerBlock.
 * 
 * @author David Lericolais
 * 
 */
public class DefaultUserAnswerBlockTest {

	private UserAnswerBlock mDefaultUserAnswerBlock;

	/**
	 * Initialization run before each test.
	 */
	@Before
	public final void setUp() {

		mDefaultUserAnswerBlock = new DefaultUserAnswerBlock();
	}

	/**
	 * Tests getter for answer list functionality.
	 */
	@Test
	public final void getAnswerListTest() {

		List<Answer> answers = mDefaultUserAnswerBlock.getAnswerList();

		assertNotNull(answers);

		assertEquals(0, answers.size());

	}
	
	/**
	 * Tests evaluatePercentCredit functionality.
	 */
	@Test
	public final void evaluatePercentCreditTest() {

		List<Answer> answers = mDefaultUserAnswerBlock.getAnswerList();

		DefaultAnswer tempAnswer = new DefaultAnswer();
		tempAnswer.setPercentCredit(1f);
		
		answers.add(tempAnswer);
		
		tempAnswer = new DefaultAnswer();
		tempAnswer.setPercentCredit(3f);
		
		answers.add(tempAnswer);
		
		assertEquals(4f, mDefaultUserAnswerBlock.evaluatePercentCredit(), 0f);

	}

}