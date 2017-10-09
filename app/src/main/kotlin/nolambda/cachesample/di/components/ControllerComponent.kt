package nolambda.cachesample.di.components

import dagger.Subcomponent
import nolambda.cachesample.di.modules.ControllerModule
import nolambda.cachesample.screens.ApiExampleController
import nolambda.cachesample.screens.DetailController
import nolambda.cachesample.screens.MainController

@Subcomponent(modules = arrayOf(ControllerModule::class))
interface ControllerComponent {

    /* --------------------------------------------------- */
    /* > Injects */
    /* --------------------------------------------------- */

    fun inject(mainController: MainController)
    fun inject(detailController: DetailController)
    fun inject(apiExampleController: ApiExampleController)

    /* --------------------------------------------------- */
    /* > Builders */
    /* --------------------------------------------------- */

    @Subcomponent.Builder
    interface Builder {
        fun controllerModule(controllerModule: ControllerModule): ControllerComponent.Builder
        fun build(): ControllerComponent
    }
}
