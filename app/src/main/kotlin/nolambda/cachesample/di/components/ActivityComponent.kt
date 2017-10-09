package nolambda.cachesample.di.components

import dagger.Subcomponent
import nolambda.cachesample.di.modules.ActivityModule
import nolambda.cachesample.di.modules.NavigatorModule
import nolambda.cachesample.navigator.AppNavigator
import nolambda.cachesample.navigator.IntentNavigator

@Subcomponent(modules = arrayOf(
        ActivityModule::class,
        NavigatorModule::class
))
interface ActivityComponent {

    fun appNavigator(): AppNavigator
    fun intentNavigator(): IntentNavigator

    /* --------------------------------------------------- */
    /* > Subcomponent */
    /* --------------------------------------------------- */

    fun controllerComponent(): ControllerComponent.Builder

    /* --------------------------------------------------- */
    /* > Builder */
    /* --------------------------------------------------- */

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): ActivityComponent.Builder
        fun navigatorModule(navigatorModule: NavigatorModule): ActivityComponent.Builder
        fun build(): ActivityComponent
    }
}
