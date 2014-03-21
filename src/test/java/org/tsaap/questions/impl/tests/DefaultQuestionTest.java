package org.tsaap.questions.impl.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.QuestionType;
import org.tsaap.questions.TextBlock;
import org.tsaap.questions.impl.DefaultQuestion;

/**
 * Test class for DefaultQuestion.
 * 
 * @author Ilja Kroonen
 * 
 */
public class DefaultQuestionTest {
	private DefaultQuestion mDefaultQuestion = new DefaultQuestion();

	/**
	 * Test for title functionality.
	 */
	@Test
	public final void titleTest() {
		assertTrue(mDefaultQuestion.getTitle() == null);
		mDefaultQuestion.setTitle("Random title");
		assertTrue(mDefaultQuestion.getTitle().equals("Random title"));
	}

	/**
	 * Tests for QuestionType functionality. This test also covers the QuestionType enum.
	 */
	@Test
	public final void questionTypeTest() {
		QuestionType type = QuestionType.FillInTheBlank;
		// We use this unique opportunity to ensure code coverage for QuestionType
		assertTrue(type.getCode() == 4);
		mDefaultQuestion.setQuestionType(type);
		assertTrue(mDefaultQuestion.getQuestionType() == type);
	}

	/**
	 * Tests for the block management functionality.
	 */
	@Test
	public final void blockTest() {
		assertTrue(mDefaultQuestion.getBlockList().isEmpty());
		TextBlock mockTextBlock = new TextBlock() {
			public String getText() {
				return null;
			}
		};
		mDefaultQuestion.addTextBlock(mockTextBlock);
		assertTrue(mDefaultQuestion.getTextBlockList().get(0) == mockTextBlock);
		AnswerBlock mockAnswerBlock = new AnswerBlock() {
			public List<Answer> getAnswerList() {
				return null;
			}
		};
		mDefaultQuestion.addAnswerBlock(mockAnswerBlock);
		assertTrue(mDefaultQuestion.getAnswerBlockList().get(0) == mockAnswerBlock);
		assertTrue(mDefaultQuestion.getBlockList().get(1) == mockAnswerBlock);
	}
}