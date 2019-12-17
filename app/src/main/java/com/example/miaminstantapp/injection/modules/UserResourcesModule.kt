package com.example.miaminstantapp.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.miaminstantapp.UserFiltersFragment
import com.example.miaminstantapp.domain.repositories.IIngredientRepository
import com.example.miaminstantapp.domain.repositories.IVolumeUnitRepository
import com.example.miaminstantapp.domain.repositories.IngredientRepository
import com.example.miaminstantapp.domain.repositories.VolumeUnitRepository
import com.example.miaminstantapp.domain.usecases.*
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
    fun providesVolumeUnitRepository(repository: VolumeUnitRepository): IVolumeUnitRepository = repository

    @Provides
    fun providesIngredientRepository(repository: IngredientRepository): IIngredientRepository = repository

    @Provides
    fun providesFetchSuggestedIngredientsUseCase(useCase: FetchSuggestedIngredientsAction): IFetchSuggestedIngredientsAction = useCase

    @Provides
    fun providesFetchVolumeUnitsAction(action: FetchVolumeUnitsAction): IFetchVolumeUnitsAction = action

    @Provides
    fun providesAddVolumeUnitsAction(action: AddVolumeUnitsAction): IAddVolumeUnitsAction = action

    @Provides
    @IntoMap
    @ViewModelKey(IUserIngredientsViewModel::class)
    fun providesUserIngredientsViewModelIntoMap(viewModel: UserIngredientsViewModel): ViewModel = viewModel

    @Provides
    fun providesUserResources(fragment: UserFiltersFragment, viewModelFactory: ViewModelFactory): IUserIngredientsViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IUserIngredientsViewModel::class.java]

}