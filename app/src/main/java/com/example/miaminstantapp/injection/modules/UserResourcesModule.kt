package com.example.miaminstantapp.injection.modules

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.miaminstantapp.domain.actions.*
import com.example.miaminstantapp.domain.actions.recipebook.FetchRecipeBookRecipesAction
import com.example.miaminstantapp.domain.repositories.*
import com.example.miaminstantapp.injection.qualifiers.AppContext
import com.example.miaminstantapp.injection.qualifiers.ViewModelKey
import com.example.miaminstantapp.persistence.CatalogRecipeTotalTimeFilterPreference
import com.example.miaminstantapp.persistence.IsFirstTimeInAppPreference
import com.example.miaminstantapp.persistence.UserMoneySharedPreferences
import com.example.miaminstantapp.view.*
import com.example.miaminstantapp.view.ingredients.viewmodels.IngredientAutocompleteViewModel
import com.example.miaminstantapp.view.recipe_book.viewmodels.RecipeBookRecipeDetailViewModel
import com.example.miaminstantapp.view.recipe_book.viewmodels.RecipeBookToDoRecipesViewModel
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailFragment
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailIngredientsListViewModel
import com.example.miaminstantapp.view.recipes_catalog.CatalogRecipeFiltersFragment
import com.example.miaminstantapp.view.recipes_catalog.CatalogRecipeFiltersViewModel
import com.example.miaminstantapp.viewmodel.*
import com.example.miaminstantapp.viewmodel.recipebook.RecipeBookRecipeIngredientsViewModel
import com.example.miaminstantapp.viewmodel.userfilters.AddAlergiesViewModel
import com.example.miaminstantapp.viewmodel.userfilters.AddUserDietsViewModel
import com.example.miaminstantapp.viewmodel.userfilters.IAddUserDietsViewModel
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
    fun providesDietsRepository(repository: DietRepository): IDietRepository = repository

    @Provides
    fun providesShopRepository(repository: ShopRepository): IShopRepository = repository

    @Provides
    fun providesMarketRecipeRepository(repository: CatalogRecipesRepository): ICatalogRecipesRepository = repository

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
    fun providesExcludedIngredientRepository(repository: ExcludedIngredientRepository): IExcludedIngredientRepository = repository

    @Provides
    fun providesCatalogRecipeTotalMinutesRepository(repository: CatalogRecipeTotalTimeMinutesRepository): ICatalogRecipeTotalTimeMinutesRepository = repository

    @Provides
    fun providesUserMoneySharedPreferences(@AppContext context: Context): UserMoneySharedPreferences = UserMoneySharedPreferences(context.getSharedPreferences("RxPrefs", Context.MODE_PRIVATE))

    @Provides
    fun providesCatalogRecipeTotalMinutesFilterPreferences(@AppContext context: Context): CatalogRecipeTotalTimeFilterPreference = CatalogRecipeTotalTimeFilterPreference(context.getSharedPreferences("RxPrefs", Context.MODE_PRIVATE))

    @Provides
    fun providesUserFirstTimeInAppPreference(@AppContext context: Context): IsFirstTimeInAppPreference = IsFirstTimeInAppPreference(context.getSharedPreferences("RxPrefs", Context.MODE_PRIVATE))

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
    fun providesSearchRecipesAction(legacyAction: SearchRecipesAction): ISearchRecipesAction = legacyAction

    @Provides
    fun providesUserFirstTimeInAppAction(action: IsFirstTimeOpeningAppAction): IIsFirstTimeOpeningAppAction = action

    @Provides
    fun providesGetDoableRecipeWithIdAction(action: GetCatalogRecipeByIdAction): IGetCatalogRecipeByIdAction = action

    @Provides
    fun providesAddRecipeAction(action: AddRecipeAction): IAddRecipeAction = action

    @Provides
    fun providesFetchShopArticlesAction(action: FetchShopArticlesByShopByShopAction): IFetchShopArticlesByShopAction = action

    @Provides
    fun providesFetchShopsPurchaseAction(action: FetchShoppingCartListAction): IFetchShopsPurchaseAction = action

    @Provides
    fun providesFetchRecipeBookRecipesAction(action: FetchRecipeBookRecipesAction): IFetchRecipeBookRecipesAction = action

    @Provides
    fun providesAddUserAddressAction(action: AddUserAddressAction): IAddUserAddressAction = action

    @Provides
    fun providesFetchRecipeSearchCriteriaAction(action: FetchSearchRecipeCriteriaAction): IFetchSearchRecipeCriteriaAction = action

    @Provides
    fun providesFetchUserAddressAction(action: FetchCurrentUserAddressAction): IFetchCurrentUserAddressAction = action

    @Provides
    fun providesDoRecipesAction(action: DoPurchaseAction): IDoPurchaseAction = action

    @Provides
    fun providesFetchShopArticlesQuantityAction(action: FetchShopArticlesQuantityAction): IFetchShopArticlesQuantityAction = action

    @Provides
    @IntoMap
    @ViewModelKey(IDispensaryViewModel::class)
    fun providesUserIngredientsViewModelIntoMap(viewModel: DispensaryViewModel): ViewModel = viewModel

    @Provides
    fun providesUserResources(fragment: DispensaryFragment, viewModelFactory: ViewModelFactory): IDispensaryViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IDispensaryViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(ICatalogRecipesListViewModel::class)
    fun provideDoableRecipesViewModelIntoMap(viewModel: CatalogRecipesListViewModel): ViewModel = viewModel

    @Provides
    fun providesDoableRecipes(fragment: CatalogRecipesListFragment, viewModelFactory: ViewModelFactory): ICatalogRecipesListViewModel = ViewModelProviders.of(fragment, viewModelFactory)[ICatalogRecipesListViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(ICatalogRecipeDetailViewModel::class)
    fun provideDoableRecipeViewModelIntoMap(viewModel: CatalogRecipeDetailViewModel): ViewModel = viewModel

    @Provides
    fun providesDoableRecipe(detailFragment: CatalogRecipeDetailFragment, viewModelFactory: ViewModelFactory): ICatalogRecipeDetailViewModel = ViewModelProviders.of(detailFragment, viewModelFactory)[ICatalogRecipeDetailViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(IShoppingCartListViewModel::class)
    fun proviesTicketViewModelIntoMap(viewModel: ShoppingCartListViewModel): ViewModel = viewModel

    @Provides
    fun providesTicket(fragment: ShoppingCartListFragment, viewModelFactory: ViewModelFactory): IShoppingCartListViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IShoppingCartListViewModel::class.java]

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

    @Provides
    @IntoMap
    @ViewModelKey(ISplashScreenViewModel::class)
    fun providesSplashScreenViewModelIntoMap(viewModel: SplashScreenViewModel): ViewModel = viewModel

    @Provides
    fun providesSplashScreen(activity: SplashScreenActivity, viewModelFactory: ViewModelFactory): ISplashScreenViewModel = ViewModelProviders.of(activity, viewModelFactory)[ISplashScreenViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(IAddUserDietsViewModel::class)
    fun providesAddUserDietsViewModelIntoMap(viewModel: AddUserDietsViewModel): ViewModel = viewModel

    @Provides
    fun providesAddUserDiets(fragment: AddUserDietsFragment, viewModelFactory: ViewModelFactory) : IAddUserDietsViewModel = ViewModelProviders.of(fragment, viewModelFactory)[IAddUserDietsViewModel::class.java]

    @Provides
    @IntoMap
    @ViewModelKey(AddAlergiesViewModel::class)
    fun providesAddAlergiesViewModelIntoMap(viewModel: AddAlergiesViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(AddAddressViewModel::class)
    fun providesAddAddressViewModelIntoMap(viewModel: AddAddressViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(EditUserIngredientViewModel::class)
    fun providesEditUserIngredientsViewModelIntoMap(viewModel: EditUserIngredientViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(CatalogRecipeDetailIngredientsListViewModel::class)
    fun providesCatalogRecipeDetailIngredientsListViewModelIntoMap(viewModel: CatalogRecipeDetailIngredientsListViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(IngredientAutocompleteViewModel::class)
    fun providesIngredientAutocompleteViewModelIntoMap(viewModel: IngredientAutocompleteViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(RecipeBookToDoRecipesViewModel::class)
    fun providesRecipeBookToDoRecipesViewModelIntoMap(viewModel: RecipeBookToDoRecipesViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(RecipeBookRecipeDetailViewModel::class)
    fun providesRecipeBookDetailIntoMap(viewModel: RecipeBookRecipeDetailViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(RecipeBookRecipeIngredientsViewModel::class)
    fun providesRecipeBookRecipeIngredientsIntoMap(viewModel: RecipeBookRecipeIngredientsViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(CatalogRecipeFiltersViewModel::class)
    fun providesCatalogRecipeFiltersIntoMap(viewModel: CatalogRecipeFiltersViewModel): ViewModel = viewModel
}