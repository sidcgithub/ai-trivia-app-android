package com.triviagenai.triviagen.trivia.domain.model

import com.triviagenai.triviagen.trivia.data.model.Question
import com.triviagenai.triviagen.trivia.data.model.Round
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TriviaQuestionTest {

    @Test
    fun testMapToTriviaQuestions() {
        val triviaRound = Round.TriviaRound(
            category = "Geography",
            questions = listOf(
                Question(
                    question = "What is the capital of France?",
                    options = listOf("Paris", "London", "Berlin", "Madrid"),
                    answer = 0
                ),
                Question(
                    question = "What is the capital of Spain?",
                    options = listOf("Paris", "London", "Berlin", "Madrid"),
                    answer = 3
                )
            )
        )

        val triviaQuestions = triviaRound.mapToTriviaQuestions()

        assertEquals(2, triviaQuestions.size)
        assertEquals("What is the capital of France?", triviaQuestions[0].question)
        assertEquals("What is the capital of Spain?", triviaQuestions[1].question)
    }
}