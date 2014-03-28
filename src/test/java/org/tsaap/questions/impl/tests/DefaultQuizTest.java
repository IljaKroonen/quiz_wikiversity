package org.tsaap.questions.impl.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.Question;
import org.tsaap.questions.impl.DefaultQuestion;
import org.tsaap.questions.impl.DefaultQuiz;

/**
 * Test class for DefaultQuizTest.
 * 
 * @author Cassim Ketfi
 * 
 */
public class DefaultQuizTest {
	
	private DefaultQuiz mDefaultQuiz;
	
	/**
	 * Initialization run before each test.
	 */
	@Before
	public final void setUp() {
		mDefaultQuiz = new DefaultQuiz();
	}
	
	/**
	 * Tests if the list of questions is handled correctly.
	 */
	@Test
	public final void attributesTest() {
		Question mDefaultQuestion = new DefaultQuestion();
		List<Question> questionList = new ArrayList<Question>();
		mDefaultQuiz.addQuestion(mDefaultQuestion);
		questionList.add(mDefaultQuestion);
		assertEquals(questionList, mDefaultQuiz.getQuestionList());
	}
}
