package dcll.black_mesa.quiz_wikiversity.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;
import org.tsaap.questions.QuestionType;
import org.tsaap.questions.Quiz;

import dcll.black_mesa.quiz_wikiversity.WikiversityContentHandler;

/**
 * Test class for WikiversityContentHandler.
 * 
 * @author David Lericolais
 * 
 */
public class WikiversityContentHandlerTest {

	private WikiversityContentHandler mContentHandler;

	/**
	 * Initialization run before each test.
	 */
	@Before
	public final void setUp() {

		mContentHandler = new WikiversityContentHandler();
	}

	/**
	 * Test for the getQuiz function.
	 */
	@Test
	public final void getQuizTest() {

		assertNull(mContentHandler.getQuiz());
	}

	/**
	 * Test for the onStartQuiz function.
	 */
	@Test
	public final void onStartQuizTest() {

		mContentHandler.onStartQuiz();
		assertNotNull(mContentHandler.getQuiz());
	}

	/**
	 * Test for the onEndQuiz function.
	 */
	@Test
	public final void onEndQuizTest() {

		mContentHandler.onStartQuiz();
		mContentHandler.onEndQuiz();
		assertNotNull(mContentHandler.getQuiz());
	}

	/**
	 * Test for the onStartQuestion function.
	 */
	@Test
	public final void onStartQuestionTest() {

		mContentHandler.onStartQuiz();
		mContentHandler.onEndQuiz();
		Quiz quiz = mContentHandler.getQuiz();

		int quizSize = quiz.getQuestionList().size();
		assertEquals(0, quizSize);

		mContentHandler.onStartQuestion("()");
		
		mContentHandler.onStartQuestion("[]");
		
		quizSize = quiz.getQuestionList().size();
		assertEquals(0, quizSize);
	}

	/**
	 * Test for the onEndQuestion function.
	 */
	@Test
	public final void onEndQuestionTest() {

		mContentHandler.onStartQuiz();
		mContentHandler.onEndQuiz();

		Quiz quiz = mContentHandler.getQuiz();

		int quizSize = quiz.getQuestionList().size();
		assertEquals(0, quizSize);

		mContentHandler.onStartQuestion("()");
		mContentHandler.onEndQuestion();
		
		mContentHandler.onStartQuestion("[]");
		mContentHandler.onEndQuestion();
		
		quizSize = quiz.getQuestionList().size();
		assertEquals(2, quizSize);
		
		Question question = quiz.getQuestionList().get(0);
		assertEquals(question.getQuestionType(), QuestionType.ExclusiveChoice);
		
		question = quiz.getQuestionList().get(1);
		assertEquals(question.getQuestionType(), QuestionType.MultipleChoice);
	}

	/**
	 * Test for the onStartTitle function.
	 */
	@Test
	public final void onStartTitleTest() {

		mContentHandler.onStartQuiz();
		mContentHandler.onEndQuiz();

		mContentHandler.onStartQuestion("[]");
		mContentHandler.onStartTitle();
		mContentHandler.onEndQuestion();

		Quiz quiz = mContentHandler.getQuiz();

		List<Question> questionsList = quiz.getQuestionList();
		Question question = questionsList.get(0);

		String title = question.getTitle();

		assertNull(title);
	}

	/**
	 * Test for the onEndTitle function.
	 */
	@Test
	public final void onEndTitleTest() {

		mContentHandler.onStartQuiz();
		mContentHandler.onEndQuiz();

		mContentHandler.onStartQuestion("[]");
		mContentHandler.onStartTitle();
		mContentHandler.onEndTitle();
		mContentHandler.onEndQuestion();

		Quiz quiz = mContentHandler.getQuiz();

		List<Question> questionsList = quiz.getQuestionList();
		Question question = questionsList.get(0);

		String title = question.getTitle();

		assertNotNull(title);
	}
	
	/**
	 * Test for the onStartAnswerBlock function.
	 */
	@Test
	public final void onStartAnswerBlockTest() {

		mContentHandler.onStartQuiz();
		mContentHandler.onEndQuiz();

		mContentHandler.onStartQuestion("[]");
		
		mContentHandler.onStartAnswerBlock();
		
		mContentHandler.onEndQuestion();

		Quiz quiz = mContentHandler.getQuiz();

		List<Question> questionsList = quiz.getQuestionList();
		Question question = questionsList.get(0);
		List<AnswerBlock> answerBlockList = question.getAnswerBlockList();
		
		assertEquals(0, answerBlockList.size());
	}
	
	/**
	 * Test for the onEndAnswerBlock function.
	 */
	@Test
	public final void onEndAnswerBlockTest() {

		mContentHandler.onStartQuiz();
		mContentHandler.onEndQuiz();

		mContentHandler.onStartQuestion("()");
		
		mContentHandler.onStartAnswerBlock();
		mContentHandler.onEndAnswerBlock();
		
		mContentHandler.onEndQuestion();
		
		mContentHandler.onStartQuestion("[]");
		
		mContentHandler.onStartAnswerBlock();
		
		mContentHandler.onStartAnswer("+");
		mContentHandler.onEndAnswer();
		
		mContentHandler.onStartAnswer("-");
		mContentHandler.onEndAnswer();
		
		mContentHandler.onEndAnswerBlock();
		
		mContentHandler.onEndQuestion();

		Quiz quiz = mContentHandler.getQuiz();

		List<Question> questionsList = quiz.getQuestionList();
		
		Question question = questionsList.get(0);
		List<AnswerBlock> answerBlockList = question.getAnswerBlockList();
		assertEquals(1, answerBlockList.size());
		
		question = questionsList.get(1);
		List<Answer> answerList = question.getAnswerBlockList().get(0).getAnswerList();
		
		final float expectecMax = 100f;
		final float expectecMin = 0f;
		assertEquals(expectecMax, answerList.get(0).getPercentCredit(), 0f);
		assertEquals(expectecMin, answerList.get(1).getPercentCredit(), 0f);
	}
	
	/**
	 * Test for the onStartAnswer function.
	 */
	@Test
	public final void onStartAnswerTest() {

		mContentHandler.onStartQuiz();
		mContentHandler.onEndQuiz();

		mContentHandler.onStartQuestion("[]");
		
		mContentHandler.onStartAnswerBlock();
		
		mContentHandler.onStartAnswer("+");
		
		mContentHandler.onEndAnswerBlock();
		
		mContentHandler.onEndQuestion();

		Quiz quiz = mContentHandler.getQuiz();

		List<Question> questionsList = quiz.getQuestionList();
		Question question = questionsList.get(0);
		List<AnswerBlock> answerBlockList = question.getAnswerBlockList();
		
		List<Answer> answerList = answerBlockList.get(0).getAnswerList();
		
		assertEquals(0, answerList.size());
	}
	
	/**
	 * Test for the onEndAnswer function.
	 */
	@Test
	public final void onEndAnswerTest() {

		mContentHandler.onStartQuiz();
		mContentHandler.onEndQuiz();

		mContentHandler.onStartQuestion("[]");
		
		mContentHandler.onStartAnswerBlock();
		
		mContentHandler.onStartAnswer("+");
		mContentHandler.onEndAnswer();
		
		mContentHandler.onEndAnswerBlock();
		
		mContentHandler.onEndQuestion();

		Quiz quiz = mContentHandler.getQuiz();

		List<Question> questionsList = quiz.getQuestionList();
		Question question = questionsList.get(0);
		List<AnswerBlock> answerBlockList = question.getAnswerBlockList();
		
		List<Answer> answerList = answerBlockList.get(0).getAnswerList();
		
		assertEquals(1, answerList.size());
	}

	/**
	 * Test for the onString function.
	 */
	@Test
	public final void onStringTest() {

		final float expectedAnswerCredit = 10.0f;
		final String expectedQuestionTitle = "Title";
		final String expectedAnswerFeedback = "Feedback";
		final String expectedAnswerText = "Answer Text";
		final String expectedQuestionTextBlock = "Question text block";

		mContentHandler.onStartQuiz();

		mContentHandler.onStartQuestion("[]");

		// Title
		mContentHandler.onStartTitle();
		mContentHandler.onString(expectedQuestionTitle);
		mContentHandler.onEndTitle();

		mContentHandler.onStartAnswerBlock();

		mContentHandler.onStartAnswer("+");

		// Answer credit
		mContentHandler.onStartAnswerCredit();
		mContentHandler.onString(Float.toString(expectedAnswerCredit));
		mContentHandler.onEndAnswerCredit();

		// Answer feedback
		mContentHandler.onStartAnswerFeedBack();
		mContentHandler.onString(expectedAnswerFeedback);
		mContentHandler.onEndAnswerFeedBack();

		// Answer text value
		mContentHandler.onString(expectedAnswerText);

		mContentHandler.onEndAnswer();

		// Should not do anything, used to get 100% test coverage
		mContentHandler.onString("");
		
		mContentHandler.onEndAnswerBlock();

		// Question text block
		mContentHandler.onString(expectedQuestionTextBlock);

		mContentHandler.onEndQuestion();
		
		// Should not do anything, used to get 100% test coverage
		mContentHandler.onString("");
				
		mContentHandler.onEndQuiz();

		Quiz quiz = mContentHandler.getQuiz();

		List<Question> questionsList = quiz.getQuestionList();

		Question question = questionsList.get(0);
		Answer answer = question.getAnswerBlockList().get(0).getAnswerList().get(0);

		String title = question.getTitle();
		assertEquals(expectedQuestionTitle, title);

		float answerCredit = answer.getPercentCredit();

		assertEquals(expectedAnswerCredit, answerCredit, 0f);

		String answerFeedback = answer.getFeedBack();
		assertEquals(expectedAnswerFeedback, answerFeedback);

		String answerTextValue = answer.getTextValue();
		assertEquals(expectedAnswerText, answerTextValue);

		String questionTextBlock = question.getTextBlockList().get(0).getText();
		assertEquals(expectedQuestionTextBlock, questionTextBlock);

	}
}