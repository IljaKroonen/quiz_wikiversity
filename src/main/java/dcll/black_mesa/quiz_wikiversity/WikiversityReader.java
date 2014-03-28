package dcll.black_mesa.quiz_wikiversity;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

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
	public void parse(final Reader reader) throws IOException, QuizReaderException {
		skip(reader, '{');
		String question = getString(reader);
		System.out.println("Question: " + question);
		skip(reader, "|type=\"");
		int type = getQuestionType(reader);
		System.out.println("Type: " + type);
		skip(reader, "\"}\n");
		int prefix;
		while((prefix = getAnswerPrefix(reader)) != -1) {
			System.out.println("Answer found");
			System.out.println("Prefix: " + prefix);
			System.out.println("Answer: " + getString(reader));
		}
		System.out.println("Parsing done");
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

	private void skip(final Reader reader, char symbol) throws IOException, QuizReaderException {
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
		int currentChar;
		while ((currentChar = reader.read()) != -1 && currentChar != '\n') {
			ret += (char) currentChar;
		}
		if (ret.equals("")) {
			throw new QuizReaderException("Empty string read");
		}
		return ret;
	}

	private int getQuestionType(final Reader reader) throws IOException, QuizReaderException {
		int c1 = reader.read();
		int c2 = reader.read();
		if (c1 == '(' && c2 == ')') {
			return 0;
		}
		if (c1 == '[' && c2 == ']') {
			return 1;
		}
		throw new QuizReaderException("Question type was expected but not found");
	}

	private int getAnswerPrefix(final Reader reader) throws IOException, QuizReaderException {
		int c = reader.read();
		if (c == '-') {
			return 0;
		}
		if (c == '+') {
			return 1;
		}
		return -1;
	}
}