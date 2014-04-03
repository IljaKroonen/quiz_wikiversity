package dcll.black_mesa.quiz_wikiversity.tests;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;
import org.tsaap.questions.Quiz;
import org.tsaap.questions.QuizReader;
import org.tsaap.questions.QuizReaderException;

import dcll.black_mesa.quiz_wikiversity.WikiversityReader;

/**
 * Test class for WikiversityReader.
 * 
 * @author David Lericolais
 * 
 */
public class WikiversityReaderTest {

	private QuizReader mWikiversityReader;

	/**
	 * Initialization run before each test.
	 */
	@Before
	public final void setUp() {

		mWikiversityReader = new WikiversityReader();
	}
	
	/**
	 * Test for the parse function.
	 */
	@Test
	public final void parseTest() {

		/*String questionString = "{Question\n"
				+ "|type=\"()\"}\n"
				+ "+ The correct answer.\n"
				+ "- Distractor.\n"
				+ "- Distractor.\n"
				+ "- Distractor.";*/
		
		String questionString = "{Question\n"
				+ "|type=\"[]\"}\n"
				+ "+ Correct answer.\n"
				+ "- Incorrect answer.\n"
				+ "+ Correct answer.\n"
				+ "- Incorrect answer.";
		Reader reader = new StringReader(questionString);
		
		try {
			mWikiversityReader.parse(reader);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
			return;
		} catch (QuizReaderException e) {
			e.printStackTrace();
			fail();
			return;
		}
		
		Quiz quiz = mWikiversityReader.getQuizContentHandler().getQuiz();
		
		for (Question question:quiz.getQuestionList()) {
			
			//System.out.println("Question : " + question.getTitle());
			assertNotNull(question.getTitle());
			
			//System.out.println("Type : " + question.getQuestionType());
			assertNotNull(question.getQuestionType());
			
			List<AnswerBlock> answerBlockList = question.getAnswerBlockList();
			assertNotNull(answerBlockList);
			
			for (AnswerBlock answerBlock:answerBlockList) {
				
				List<Answer> answerList = answerBlock.getAnswerList();
				assertNotNull(answerList);
				
				for (Answer answer:answerList) {
					
					//System.out.print("Answer : " + answer.getTextValue());
					assertNotNull(answer.getTextValue());
					
					//System.out.println(" - Credit : " + answer.getPercentCredit());
					assertNotNull(answer.getPercentCredit());
				}
			}
		}
	}
}
