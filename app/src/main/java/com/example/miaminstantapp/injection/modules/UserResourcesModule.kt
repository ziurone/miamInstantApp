package com.example.miaminstantapp.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.miaminstantapp.UserFiltersFragment
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import com.example.miaminstantapp.domain.repositories.IngredientRepository
import com.example.miaminstantapp.domain.usecases.FetchSuggestedIngredientsUseCase
import com.example.miaminstantapp.domain.usecases.IFetchSuggestedIngredientsUseCase
import com.example.miaminstantapp.injection.qualifiers.ViewModelKey
import com.example.miaminstantapp.viewmodel.IUserIngredientsViewModel
import com.example.miaminstantapp.viewmodel.UserIngredientsViewModel
import com.example.miaminstantapp.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class UserResourcesModule {

    @Provides
    fun providesIngredientRepository(repository: IngredientRepository): IIngredientRepository = repository

    @Provides
    fun providesFetchSuggestedIngredientsUseCase(useCase: FetchSuggestedIngredientsUseCase): IFetchSuggestedIngredientsUseCase = useCase

    @Provides
    @IntoMap
    @ViewModelKey(IUserIngredientsViewModel::class)
    fun providesuserIngredientsViewModelIntoMap(viewModel: UserIngredientsViewModel): ViewModel = viewModel

    @Provides
    fun providesUserResources(fragment: UserFiltersFragment, viewModelFactory: ViewModelFactory): IUserIngredientsViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IUserIngredientsViewModel::class.java]

}