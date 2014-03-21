package org.tsaap.questions.impl.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.Answer;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultAnswerBlock;

public class DefaultAnswerBlockTest {

	private DefaultAnswerBlock mDefaultAnswerBlock;
	
	@Before
	public void setUp() {
		mDefaultAnswerBlock = new DefaultAnswerBlock();
	}
	@Test
	public void attributesTest() {
		Answer mDefaultAnswer = new DefaultAnswer();
		List<Answer> answerList = new ArrayList<Answer>();
		mDefaultAnswerBlock.addAnswer(mDefaultAnswer);
		answerList.add(mDefaultAnswer);
		assertEquals(answerList,mDefaultAnswerBlock.getAnswerList());
	}

}
