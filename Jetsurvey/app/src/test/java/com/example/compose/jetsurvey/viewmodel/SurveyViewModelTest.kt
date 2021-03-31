package com.example.compose.jetsurvey.viewmodel

import com.example.compose.jetsurvey.survey.PhotoUriManager
import com.example.compose.jetsurvey.survey.QuestionState
import com.example.compose.jetsurvey.survey.SurveyRepository
import com.example.compose.jetsurvey.survey.SurveyState
import com.example.compose.jetsurvey.survey.SurveyViewModel
import io.uniflow.android.test.TestViewObserver
import io.uniflow.android.test.createTestObserver
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class SurveyViewModelTest : ViewModelTest() {

    lateinit var viewModel: SurveyViewModel
    lateinit var tester: TestViewObserver
    val surveyRepository = SurveyRepository

    @Before
    fun setup() {
        viewModel = SurveyViewModel(surveyRepository, mock(PhotoUriManager::class.java))
        tester = viewModel.createTestObserver()
    }

    @Test
    fun `init state`(): Unit = runBlocking {
        val survey = surveyRepository.getSurvey()

        // Create the default questions state based on the survey questions
        val questions: List<QuestionState> = survey.questions.mapIndexed { index, question ->
            val showPrevious = index > 0
            val showDone = index == survey.questions.size - 1
            QuestionState(
                question = question,
                questionIndex = index,
                totalQuestionsCount = survey.questions.size,
                showPrevious = showPrevious,
                showDone = showDone
            )
        }

        assert(tester.states.values.first() is SurveyState.Questions)
    }
}