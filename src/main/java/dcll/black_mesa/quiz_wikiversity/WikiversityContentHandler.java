/*
 * Copyright 2013 Tsaap Development Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package dcll.black_mesa.quiz_wikiversity;

import org.apache.log4j.Logger;
import org.tsaap.questions.Question;
import org.tsaap.questions.QuestionType;
import org.tsaap.questions.QuizContentHandler;
import org.tsaap.questions.TextBlock;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultAnswerBlock;
import org.tsaap.questions.impl.DefaultQuestion;
import org.tsaap.questions.impl.DefaultQuiz;

/*
 * TODO This code is valid for a Gift reader. It will probably need modifications to adapt it as a handler for the
 * Wikiversity reader.
 */

/**
 * @author franck Silvestre
 */
public class WikiversityContentHandler implements QuizContentHandler {

	/**
	 * Get the quiz.
	 * 
	 * @return The quiz.
	 */
	public final DefaultQuiz getQuiz() {
		return quiz;
	}

	/**
	 * Receive notification of the beginning of a quiz.
	 */
	public final void onStartQuiz() {
		quiz = new DefaultQuiz();
	}

	/**
	 * Receive notification of the end of a quiz.
	 */
	public void onEndQuiz() {
	}

	/**
	 * Receive notification of the beginning of a question.
	 */
	public final void onStartQuestion() {
		currentQuestion = new DefaultQuestion();
		currentQuestion.setQuestionType(QuestionType.MultipleChoice);
	}

	/**
	 * Receive notification of the end of a question.
	 */
	public final void onEndQuestion() {
		postProcess(currentQuestion);
		quiz.addQuestion(currentQuestion);
		currentQuestion = null;
	}

	/**
	 * Receive notification of the beginning of a title.
	 */
	public final void onStartTitle() {
		currentTitle = new StringBuffer();
	}

	/**
	 * Receive notification of the end of a title.
	 */
	public final void onEndTitle() {
		currentQuestion.setTitle(currentTitle.toString());
		currentTitle = null;
	}

	/**
	 * Receive notification of the beginning of an answer fragment.
	 */
	public final void onStartAnswerBlock() {
		currentAnswerBlock = new DefaultAnswerBlock();
		answerCounter = 0;
	}

	/**
	 * Receive notification of the end of an answer fragment.
	 */
	public final void onEndAnswerBlock() {
		currentQuestion.addAnswerBlock(currentAnswerBlock);
		currentAnswerBlock = null;
	}

	/**
	 * Receive notification of the beginning of an answer.
	 * 
	 * @param prefix
	 *            Prefix of the answer.
	 */
	public final void onStartAnswer(final String prefix) {
		currentAnswer = new DefaultAnswer();
		currentAnswer.setIdentifier(String.valueOf(answerCounter++));
		if ("=".equals(prefix)) {
			currentAnswer.setPercentCredit(100f);
			currentQuestion.setQuestionType(QuestionType.ExclusiveChoice);
		} else {
			currentAnswer.setPercentCredit(0f);
		}
	}

	/**
	 * Receive notification of the end of an answer.
	 */
	public final void onEndAnswer() {
		currentAnswerBlock.addAnswer(currentAnswer);
		currentAnswer = null;
	}

	/**
	 * Notification of the beginning of a credit specification.
	 */
	public final void onStartAnswerCredit() {
		answerCreditIsBeenBuilt = true;
	}

	/**
	 * Notification of the end of a credit specification.
	 */
	public final void onEndAnswerCredit() {
		answerCreditIsBeenBuilt = false;
	}

	/**
	 * Receive notification of the beginning feedback.
	 */
	public final void onStartAnswerFeedBack() {
		feedbackIsBeenBuilt = true;
	}

	/**
	 * Receive notification of the end of a feedback.
	 */
	public final void onEndAnswerFeedBack() {
		feedbackIsBeenBuilt = false;
	}

	/**
	 * Receive notification of a new string.
	 * 
	 * @param str
	 *            The received string.
	 */
	public final void onString(final String str) {
		String trimedStr = str.trim();
		if (currentTitle != null) {
			currentTitle.append(trimedStr);
			logger.debug("currentTitle | " + currentTitle.toString());
		} else if (answerCreditIsBeenBuilt) {
			currentAnswer.setPercentCredit(new Float(trimedStr));
		} else if (feedbackIsBeenBuilt) {
			currentAnswer.setFeedback(trimedStr);
		} else if (currentAnswer != null) {
			currentAnswer.setTextValue(trimedStr);
		} else if (currentQuestion != null && currentAnswerBlock == null) {
			logger.debug("Text fragment | " + str);
			currentQuestion.addTextBlock(new TextBlock() {
				public String getText() {
					return str;
				}
			});
		}
	}

	private void postProcess(final Question question) {
		logger.debug("Post processing of the current question");
	}

	private static Logger logger = Logger.getLogger(WikiversityContentHandler.class);

	private DefaultQuiz quiz;
	private DefaultQuestion currentQuestion;
	private DefaultAnswerBlock currentAnswerBlock;
	private DefaultAnswer currentAnswer;
	private StringBuffer currentTitle;
	private boolean answerCreditIsBeenBuilt;
	private boolean feedbackIsBeenBuilt;
	private int answerCounter;
}
