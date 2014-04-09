package dcll.black_mesa.quiz_wikiversity;

import java.io.IOException;
import java.io.Reader;

import org.tsaap.questions.QuizContentHandler;
import org.tsaap.questions.QuizReader;
import org.tsaap.questions.QuizReaderException;

/**
 * Reader for the wikiversity format.
 * 
 * @author Ilja Kroonen
 * 
 */
public class WikiversityReader implements QuizReader {

	private QuizContentHandler mContentHandler;

	/**
	 * Default constructor for WikiversityReader.
	 */
	public WikiversityReader() {

		mContentHandler = new WikiversityContentHandler();
	}

	/**
	 * Parses a quiz in the wikiversity format.
	 * 
	 * @param reader
	 *            The reader for the input.
	 * @throws IOException
	 *             Exception thrown by the reader passed as a parameter.
	 * @throws QuizReaderException
	 *             A parsing error occured.
	 */
	public final void parse(final Reader reader) throws IOException, QuizReaderException {

		String title, type;
		int prefix;

		mContentHandler = (WikiversityContentHandler) getQuizContentHandler();
		mContentHandler.onStartQuiz();

		skip(reader, '{');

		while (true) {

			// Get the title
			title = getString(reader);

			// Get the question type
			skip(reader, "|type=\"");
			type = getQuestionType(reader);

			mContentHandler.onStartQuestion(type);

			mContentHandler.onStartTitle();
			mContentHandler.onString(title);
			mContentHandler.onEndTitle();

			mContentHandler.onStartAnswerBlock();

			skip(reader, "\"}\n");

			prefix = reader.read();
			while (prefix != -1) {

				if (prefix == '{') {
					
					break;
				}
				
				System.out.println("Answer found");

				mContentHandler.onStartAnswer(String.valueOf((char) prefix));

				// Set answer text
				mContentHandler.onString(getString(reader));

				mContentHandler.onEndAnswer();

				prefix = reader.read();
			}

			mContentHandler.onEndAnswerBlock();

			mContentHandler.onEndQuestion();

			if (prefix == -1) {
				break;
			}
		}

		mContentHandler.onEndQuiz();
	}

	/**
	 * Getter for the content handler of this reader.
	 * 
	 * @return The content handler.
	 */
	public final QuizContentHandler getQuizContentHandler() {

		return mContentHandler;
	}

	private void skip(final Reader reader, final char symbol) throws IOException, QuizReaderException {

		int currentChar;

		while ((currentChar = reader.read()) != -1) {

			if (currentChar == symbol) {

				return;
			}
		}

		throw new QuizReaderException("Expected symbol " + symbol + " was not found");
	}

	private void skip(final Reader reader, final String symbol) throws IOException, QuizReaderException {

		for (int i = 0; i < symbol.length(); i++) {

			skip(reader, symbol.charAt(i));
		}
	}

	private String getString(final Reader reader) throws IOException, QuizReaderException {

		String ret = "";
		int currentChar = reader.read();

		while (currentChar != -1 && currentChar != '\n') {

			ret += (char) currentChar;
			currentChar = reader.read();
		}

		if (ret.equals("")) {

			throw new QuizReaderException("Empty string read");
		}

		return ret;
	}

	private String getQuestionType(final Reader reader) throws IOException, QuizReaderException {

		char[] type = new char[2];

		if (reader.read(type, 0, 2) == 2) {

			return new String(type);
		}

		throw new QuizReaderException("Question type was expected but not found");
	}
}