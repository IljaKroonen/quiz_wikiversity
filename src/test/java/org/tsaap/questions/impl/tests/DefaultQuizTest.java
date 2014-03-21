package org.tsaap.questions.impl.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.Question;
import org.tsaap.questions.impl.DefaultQuestion;
import org.tsaap.questions.impl.DefaultQuiz;

public class DefaultQuizTest {
	
	private DefaultQuiz mDefaultQuiz;
	
	@Before
	public void setUp() {
		mDefaultQuiz = new DefaultQuiz();
	}

	@Test
	public void attributesTest() {
		Question mDefaultQuestion = new DefaultQuestion();
		List<Question> questionList = new ArrayList<Question>();
		mDefaultQuiz.addQuestion(mDefaultQuestion);
		questionList.add(mDefaultQuestion);
		assertEquals(questionList,mDefaultQuiz.getQuestionList());
	}
}
