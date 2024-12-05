package io.fairboi.mytodoapp.di

import dagger.Subcomponent
import io.fairboi.list.TodoItemsListViewModel
import io.fairboi.mytodoapp.di.modules.ListFeatureModule

@Subcomponent(modules = [ListFeatureModule::class])
internal interface TodoListFeatureComponent {
    val listViewModel: TodoItemsListViewModel
}