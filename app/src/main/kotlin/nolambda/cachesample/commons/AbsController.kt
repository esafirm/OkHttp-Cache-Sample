package nolambda.cachesample.commons

import com.esafirm.conductorextra.butterknife.BinderController
import nolambda.cachesample.di.components.ActivityComponent
import nolambda.cachesample.di.components.ControllerComponent
import nolambda.cachesample.di.helpers.HasComponent
import nolambda.cachesample.di.modules.ControllerModule

abstract class AbsController : BinderController() {

    @Suppress("UNCHECKED_CAST")
    protected val component: ControllerComponent by lazy {
        if (activity == null) {
            throw IllegalStateException("Not attached to Activity")
        }
        if ((activity is HasComponent<*>).not()) {
            throw IllegalStateException("Activity should implement HasComponent<Component>")
        }
        activity.let { it as HasComponent<ActivityComponent> }
                .getComponent()
                .controllerComponent()
                .controllerModule(ControllerModule(this))
                .build()
    }
}
