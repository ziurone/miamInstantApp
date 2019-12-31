package com.example.miaminstantapp.injection.modules

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.miaminstantapp.UserFiltersFragment
import com.example.miaminstantapp.domain.actions.*
import com.example.miaminstantapp.domain.repositories.*
import com.example.miaminstantapp.injection.qualifiers.AppContext
import com.example.miaminstantapp.injection.qualifiers.ViewModelKey
import com.example.miaminstantapp.persistence.UserMoneySharedPreferences
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
    fun providesUserMoneyRepository(repository: UserMoneyRepository): IUserMoneyRepository = repository

    @Provides
    fun providesUserMoneySharedPreferences(@AppContext context: Context): UserMoneySharedPreferences = UserMoneySharedPreferences(context.getSharedPreferences("RxPrefs", Context.MODE_PRIVATE))

    @Provides
    fun providesFetchSuggestedIngredientsUseCase(useCase: FetchSuggestedIngredientsAction): IFetchSuggestedIngredientsAction = useCase

    @Provides
    fun providesFetchVolumeUnitsAction(action: FetchVolumeUnitsAction): IFetchVolumeUnitsAction = action

    @Provides
    fun providesAddVolumeUnitsAction(action: AddVolumeUnitsAction): IAddVolumeUnitsAction = action

    @Provides
    fun providesAddIngredientAction(action: AddUserIngredientAction): IAddUserIngredientAction = action

    @Provides
    fun providesFetchUserIngredientsAction(action: FetchUserIngredientsAction): IFetchUserIngredientsAction = action

    @Provides
    fun providesGetIngredientsByNameAction(action: GetIngredientsByNameAction): IGetIngredientsByNameAction = action

    @Provides
    fun providesSetUserMoneyAction(action: SetUserMoneyAction): ISetUserMoneyAction = action



    @Provides
    @IntoMap
    @ViewModelKey(IUserIngredientsViewModel::class)
    fun providesUserIngredientsViewModelIntoMap(viewModel: UserIngredientsViewModel): ViewModel = viewModel

    @Provides
    fun providesUserResources(fragment: UserFiltersFragment, viewModelFactory: ViewModelFactory): IUserIngredientsViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IUserIngredientsViewModel::class.java]

}