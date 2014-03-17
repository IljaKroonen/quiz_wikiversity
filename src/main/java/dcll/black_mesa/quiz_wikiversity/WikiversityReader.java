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
	public void parse(final Reader reader) throws IOException,
			QuizReaderException {
		// TODO Auto-generated method stub
	}

	/**
	 * Setter for the content handler of this reader.
	 * 
	 * @return The content handler.
	 */
	public final QuizContentHandler getQuizContentHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
