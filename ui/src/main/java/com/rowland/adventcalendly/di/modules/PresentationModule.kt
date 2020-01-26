package com.rowland.adventcalendly.di.modules

import androidx.lifecycle.ViewModel
import com.alexfacciorusso.daggerviewmodel.DaggerViewModelInjectionModule
import com.alexfacciorusso.daggerviewmodel.ViewModelKey
import com.rowland.adventcalendly.presentation.AdventDayViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Created by Rowland on 1/26/2020.
 */

@Module(includes = [DaggerViewModelInjectionModule::class])
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(AdventDayViewModel::class)
    internal abstract fun bindAdventDayViewModel(viewModel: AdventDayViewModel): ViewModel
}