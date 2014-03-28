package org.tsaap.questions.impl.tests;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.Answer;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultAnswerBlock;

/**
 * Test class for DefaultAnswerBlock.
 * 
 * @author Cassim Ketfi
 * 
 */
public class DefaultAnswerBlockTest {

	private DefaultAnswerBlock mDefaultAnswerBlock;
	
	/**
	 * Initialization run before each test.
	 */
	@Before
	public final void setUp() {
		mDefaultAnswerBlock = new DefaultAnswerBlock();
	}
	/**
	 * Tests if the answer list is handled correctly.
	 */
	@Test
	public final void attributesTest() {
		Answer mDefaultAnswer = new DefaultAnswer();
		List<Answer> answerList = new ArrayList<Answer>();
		mDefaultAnswerBlock.addAnswer(mDefaultAnswer);
		answerList.add(mDefaultAnswer);
		assertEquals(answerList, mDefaultAnswerBlock.getAnswerList());
	}

}
