package com.example.miaminstantapp.domain.actions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import com.example.miaminstantapp.TestConstants.GENERIC_ERROR
import com.example.miaminstantapp.TestConstants.INGREDIENT_LIST
import com.google.common.truth.Truth
import io.reactivex.Single
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchSuggestesIngredientsUseCaseTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: IIngredientRepository

    @Mock
    private lateinit var observer: Observer<IFetchSuggestedIngredientsAction.Result>

    private lateinit var subject: FetchSuggestedIngredientsAction

    private val useCaseLivedata: MediatorLiveData<IFetchSuggestedIngredientsAction.Result> = MediatorLiveData()

    private fun givenASetUpSubject() {
        useCaseLivedata.observeForever { observer }
        subject = FetchSuggestedIngredientsAction(repository)
    }

    @Test
    fun `GIVEN a list of ingredients is retrieve WHEN the suggested ingredients are asked THEN a successful result is returned`() {
        givenASetUpSubject()
        BDDMockito.given(repository.getSuggestedIngredients()).willReturn(Single.just(getIngredientListResponse()))

        subject.fetch()

        Truth.assertThat(subject.getLiveData().value).isEqualTo(IFetchSuggestedIngredientsAction.Result.Success(getIngredientListResponse()))
    }

    @Test
    fun `GIVEN an error happens WHEN the suggested ingredients are asked THEN an error result is returned`() {
        givenASetUpSubject()
        BDDMockito.given(repository.getSuggestedIngredients()).willReturn(Single.error(Exception(GENERIC_ERROR)))

        subject.fetch()

        Truth.assertThat(subject.getLiveData().value).isEqualTo(IFetchSuggestedIngredientsAction.Result.Error(GENERIC_ERROR))
    }


    private fun getIngredientListResponse(): IngredientsListResponse {
        return IngredientsListResponse(INGREDIENT_LIST)
    }

}