package org.tsaap.questions.impl.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;
import org.tsaap.questions.QuestionBlock;
import org.tsaap.questions.QuestionType;
import org.tsaap.questions.TextBlock;
import org.tsaap.questions.UserAnswerBlock;
import org.tsaap.questions.UserResponse;
import org.tsaap.questions.impl.DefaultUserResponse;

/**
 * Test class for DefaultUserResponse.
 * 
 * @author Ilja Kroonen
 * 
 */
public class DefaultUserResponseTest extends Object {
	private UserResponse mDefaultUserResponse = new DefaultUserResponse();

	/**
	 * Tests user identifier functionality.
	 */
	@Test
	public final void userIdentifierTest() {
		String str = "Random string";
		((DefaultUserResponse) mDefaultUserResponse).setUserIdentifier(str);
		assertTrue(mDefaultUserResponse.getUserIdentifier().equals("Random string"));
	}

	/**
	 * Tests for question getter and setter.
	 */
	@Test
	public final void questionTest() {
		Question mockQuestion = new Question() {
			public String getTitle() {
				return null;
			}

			public List<QuestionBlock> getBlockList() {
				return null;
			}

			public List<TextBlock> getTextBlockList() {
				return null;
			}

			public List<AnswerBlock> getAnswerBlockList() {
				return null;
			}

			public QuestionType getQuestionType() {
				return null;
			}
		};
		((DefaultUserResponse) mDefaultUserResponse).setQuestion(mockQuestion);
		assertTrue(mDefaultUserResponse.getQuestion() == mockQuestion);
	}

	/**
	 * Test for userAnswerBlocklist.
	 */
	@Test
	public final void answerBlockListTest() {
		assertTrue(mDefaultUserResponse.getUserAnswerBlockList().isEmpty());
		mDefaultUserResponse.getUserAnswerBlockList().add(new UserAnswerBlock() {
			public List<Answer> getAnswerList() {
				return null;
			}

			public Float evaluatePercentCredit() {
				return 1f;
			}
		});
		assertTrue(mDefaultUserResponse.getUserAnswerBlockList().get(0).evaluatePercentCredit() == 1f);
		assertTrue(mDefaultUserResponse.evaluatePercentCredit() == 1f);
	}
}