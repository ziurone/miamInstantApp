package com.example.miaminstantapp.injection.modules

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.miaminstantapp.view.UserFiltersFragment
import com.example.miaminstantapp.domain.actions.*
import com.example.miaminstantapp.domain.repositories.*
import com.example.miaminstantapp.injection.qualifiers.AppContext
import com.example.miaminstantapp.injection.qualifiers.ViewModelKey
import com.example.miaminstantapp.persistence.UserMoneySharedPreferences
import com.example.miaminstantapp.view.*
import com.example.miaminstantapp.viewmodel.*
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
    fun providesBranchesRepository(repository: BranchRepository): IBranchRepository = repository

    @Provides
    fun providesShopRepository(repository: ShopRepository): IShopRepository = repository

    @Provides
    fun providesMarketRecipeRepository(repository: MarketRecipesRepository): IMarketRecipesRepository = repository

    @Provides
    fun providesMarketIngredientRepository(repository: MarketIngredientRepository): IMarketIngredientRepository = repository

    @Provides
    fun providesShopArticleRepository(repository: ShopArticleRepository): IShopArticleRepository = repository

    @Provides
    fun providesUserIngredientsOnRecipeRepository(repository: UserRecipeIngredientRepository): IUserRecipeIngredientRepository = repository

    @Provides
    fun providesRecipeBookRepository(repository: RecipeBookRepository): IRecipeBookRepository = repository

    @Provides
    fun providesRecipeBookRecipeIngredientRepository(repository: RecipeBookRecipeIngredientRepository): IRecipeBookRecipeIngredientRepository = repository

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
    fun providesFetchShopsAction(action: FetchShopsAction): IFetchShopsAction = action

    @Provides
    fun providesSearchRecipesAction(action: SearchRecipesAction): ISearchRecipesAction = action

    @Provides
    fun providesGetDoableRecipeWithIdAction(action: GetDoableRecipeByIdAction): IGetDoableRecipeByIdAction = action

    @Provides
    fun providesAddRecipeAction(action: AddRecipeAction): IAddRecipeAction = action

    @Provides
    fun providesFetchShopArticlesAction(action: FetchShopArticlesByShopByShopAction): IFetchShopArticlesByShopAction = action

    @Provides
    fun providesFetchShopsPurchaseAction(action: FetchShopsPurchaseAction): IFetchShopsPurchaseAction = action

    @Provides
    fun providesFetchRecipeBookRecipesAction(action: FetchRecipeBookRecipesAction): IFetchRecipeBookRecipesAction = action

    @Provides
    fun providesAddUserAddressAction(action: AddUserAddressAction): IAddUserAddressAction = action

    @Provides
    fun providesFetchRecipeSearchCriteriaAction(action: FetchSearchRecipeCriteriaAction): IFetchSearchRecipeCriteriaAction = action

    @Provides
    fun providesFetchUserAddressAction(action: FetchCurrentUserAddressAction): IFetchCurrentUserAddressAction = action

    @Provides
    fun providesDoRecipesAction(action: DoRecipesAction): IDoRecipesAction = action

    @Provides
    @IntoMap
    @ViewModelKey(IUserIngredientsViewModel::class)
    fun providesUserIngredientsViewModelIntoMap(viewModel: UserIngredientsViewModel): ViewModel = viewModel

    @Provides
    fun providesUserResources(fragment: UserFiltersFragment, viewModelFactory: ViewModelFactory): IUserIngredientsViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IUserIngredientsViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(IDoableRecipesViewModel::class)
    fun provideDoableRecipesViewModelIntoMap(viewModel: DoableRecipesViewModel): ViewModel = viewModel

    @Provides
    fun providesDoableRecipes(fragment: DoableRecipesListFragment, viewModelFactory: ViewModelFactory): IDoableRecipesViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IDoableRecipesViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(IDoableRecipeDetailViewModel::class)
    fun provideDoableRecipeViewModelIntoMap(viewModel: DoableRecipeDetailViewModel): ViewModel = viewModel

    @Provides
    fun providesDoableRecipe(fragment: DoableRecipeFragment, viewModelFactory: ViewModelFactory): IDoableRecipeDetailViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IDoableRecipeDetailViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(ITicketViewModel::class)
    fun proviesTicketViewModelIntoMap(viewModel: TicketViewModel): ViewModel = viewModel

    @Provides
    fun providesTicket(fragment: ShopPurchaseTicketFragment, viewModelFactory: ViewModelFactory): ITicketViewModel = ViewModelProviders.of(fragment, viewModelFactory)[ITicketViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(ITicketArticlesViewModel::class)
    fun providesTicketArticlesViewModelIntoMap(viewModel: TicketArticlesViewModel): ViewModel = viewModel

    @Provides
    fun providesTicketArticles(fragment: TicketArticlesFragment, viewModelFactory: ViewModelFactory): ITicketArticlesViewModel = ViewModelProviders.of(fragment, viewModelFactory)[ITicketArticlesViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(IRecipeBookViewModel::class)
    fun providesRecipeBookViewModelIntoMap(viewModel: RecipeBookViewModel): ViewModel = viewModel

    @Provides
    fun providesRecipeBook(fragment: RecipeBookFragment, viewModelFactory: ViewModelFactory): IRecipeBookViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IRecipeBookViewModel::class.java]

}