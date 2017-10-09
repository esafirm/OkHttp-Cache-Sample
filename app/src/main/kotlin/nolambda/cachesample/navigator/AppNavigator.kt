package nolambda.cachesample.navigator

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.esafirm.conductorextra.transaction.Routes
import nolambda.cachesample.screens.ApiExampleController
import nolambda.cachesample.screens.DetailController
import nolambda.cachesample.screens.MainController

class AppNavigator(private val router: Router,
                   private val overlayRouter: Router) {

    fun setupContent() {
        router.setRoot(RouterTransaction.with(MainController()))
        goToApiExample()
    }

    fun goToDetail() {
        router.pushController(Routes.simpleTransaction(
                DetailController(),
                HorizontalChangeHandler()
        ))
    }

    fun goToApiExample() {
        router.pushController(Routes.simpleTransaction(
                ApiExampleController(),
                VerticalChangeHandler()
        ))
    }
}
