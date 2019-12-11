package com.example.miaminstantapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.miaminstantapp.TestConstants.GENERIC_ERROR
import com.example.miaminstantapp.domain.usecases.IFetchSuggestedIngredientsUseCase
import com.example.miaminstantapp.TestConstants.INGREDIENT_LIST
import com.example.miaminstantapp.domain.dtos.SuggestedIngredientsResponse
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

    private lateinit var subject: UserIngredientsViewModel

    private val fetchSuggestedIngredientsLivedata: MutableLiveData<IFetchSuggestedIngredientsUseCase.Result> = MutableLiveData()

    @Mock
    private lateinit var observer: Observer<IUserIngredientsViewModel.State>

    @Mock
    private lateinit var fetchSuggestedIngredientsUseCase: IFetchSuggestedIngredientsUseCase

    private fun givenASetupSubject() {
        BDDMockito.given(fetchSuggestedIngredientsUseCase.getLiveData()).willReturn(fetchSuggestedIngredientsLivedata)
        subject = UserIngredientsViewModel(fetchSuggestedIngredientsUseCase)
        subject.getState().observeForever { observer }
    }

    @Test
    fun `GIVEN a list of ingredients is retrieved WHEN suggested ingredients are fetched THEN state of view model to Success`() {
        // GIVEN
        givenASetupSubject()
        BDDMockito.given(fetchSuggestedIngredientsUseCase.fetch()).will{
            setUseCaseResult(IFetchSuggestedIngredientsUseCase.Result.Success(
                SuggestedIngredientsResponse(INGREDIENT_LIST)))
        }

        // WHEN
        subject.fetchSuggestedIngredients()

        //THEN
        Truth.assertThat(subject.getState().value).isEqualTo(IUserIngredientsViewModel.State.FetchSuggestedIngredientsSuccess(INGREDIENT_LIST))
    }

    @Test
    fun `GIVEN the state never change WHEN suggested ingredients are requested THEN state of view model is Loading`() {
        // GIVEN
        givenASetupSubject()
        BDDMockito.given(fetchSuggestedIngredientsUseCase.fetch()).will{}

        // WHEN
        subject.fetchSuggestedIngredients()

        //THEN
        Truth.assertThat(subject.getState().value).isEqualTo(IUserIngredientsViewModel.State.Loading)
    }

    @Test
    fun `GIVEN aan error is retrieved WHEN suggested ingredients are requested THEN state of view model is Error`() {
        // GIVEN
        givenASetupSubject()
        BDDMockito.given(fetchSuggestedIngredientsUseCase.fetch()).will{setUseCaseResult(IFetchSuggestedIngredientsUseCase.Result.Error(GENERIC_ERROR))}

        // WHEN
        subject.fetchSuggestedIngredients()

        //THEN
        Truth.assertThat(subject.getState().value).isEqualTo(IUserIngredientsViewModel.State.Error(GENERIC_ERROR))
    }

    private fun setUseCaseResult(result: IFetchSuggestedIngredientsUseCase.Result) {
        fetchSuggestedIngredientsLivedata.value = result
    }

}