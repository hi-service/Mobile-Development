package com.hiservice.mobile.screen.afterlogin.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.ArticleModelWrapper
import com.hiservice.mobile.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailArticleViewModel(
    private val repository: Repository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<ArticleModelWrapper>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<ArticleModelWrapper>>
        get() = _uiState

    fun getArticleById(articleID: Int){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getClickedArticleByID(articleID))
        }
    }
}