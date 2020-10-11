package com.example.miaminstantapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.miaminstantapp.TestConstants.GENERIC_ERROR
import com.example.miaminstantapp.domain.actions.IFetchSuggestedIngredientsAction
import com.example.miaminstantapp.TestConstants.INGREDIENT_LIST
import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserIngredientsViewModelTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var subject: DispensaryViewModel

    private val fetchSuggestedIngredientsLivedata: MutableLiveData<IFetchSuggestedIngredientsAction.Result> = MutableLiveData()

    @Mock
    private lateinit var observer: Observer<IDispensaryViewModel.State>

    @Mock
    private lateinit var fetchSuggestedIngredientsUseCase: IFetchSuggestedIngredientsAction

    private fun givenASetupSubject() {
        BDDMockito.given(fetchSuggestedIngredientsUseCase.getLiveData()).willReturn(fetchSuggestedIngredientsLivedata)
        subject = DispensaryViewModel(fetchSuggestedIngredientsUseCase)
        subject.getState().observeForever { observer }
    }

    @Test
    fun `GIVEN a list of ingredients is retrieved WHEN suggested ingredients are fetched THEN state of view model to Success`() {
        // GIVEN
        givenASetupSubject()
        BDDMockito.given(fetchSuggestedIngredientsUseCase.fetch()).will{
            setUseCaseResult(IFetchSuggestedIngredientsAction.Result.Success(
                IngredientsListResponse(INGREDIENT_LIST)))
        }

        // WHEN
        subject.loadVolumeUnits()

        //THEN
        Truth.assertThat(subject.getState().value).isEqualTo(IDispensaryViewModel.State.FetchSuggestedIngredientsSuccess(INGREDIENT_LIST))
    }

    @Test
    fun `GIVEN the state never change WHEN suggested ingredients are requested THEN state of view model is Loading`() {
        // GIVEN
        givenASetupSubject()
        BDDMockito.given(fetchSuggestedIngredientsUseCase.fetch()).will{}

        // WHEN
        subject.loadVolumeUnits()

        //THEN
        Truth.assertThat(subject.getState().value).isEqualTo(IDispensaryViewModel.State.Loading)
    }

    @Test
    fun `GIVEN aan error is retrieved WHEN suggested ingredients are requested THEN state of view model is Error`() {
        // GIVEN
        givenASetupSubject()
        BDDMockito.given(fetchSuggestedIngredientsUseCase.fetch()).will{setUseCaseResult(IFetchSuggestedIngredientsAction.Result.Error(GENERIC_ERROR))}

        // WHEN
        subject.loadVolumeUnits()

        //THEN
        Truth.assertThat(subject.getState().value).isEqualTo(IDispensaryViewModel.State.Error(GENERIC_ERROR))
    }

    private fun setUseCaseResult(result: IFetchSuggestedIngredientsAction.Result) {
        fetchSuggestedIngredientsLivedata.value = result
    }

}