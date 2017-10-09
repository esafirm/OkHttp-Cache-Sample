package nolambda.cachesample.commons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.internal.LifecycleHandler
import nolambda.cachesample.MainApp
import nolambda.cachesample.di.components.ActivityComponent
import nolambda.cachesample.di.helpers.HasComponent
import nolambda.cachesample.di.modules.ActivityModule
import nolambda.cachesample.di.modules.NavigatorModule
import nolambda.cachesample.navigator.AppNavigator


class MainActivity : AppCompatActivity(), HasComponent<ActivityComponent> {

    private lateinit var router: Router
    private lateinit var overlayRouter: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )

        val main = ChangeHandlerFrameLayout(this).apply {
            layoutParams = params
        }

        val overlay = ChangeHandlerFrameLayout(this).apply {
            layoutParams = params
        }

        val lifecycleHandler = LifecycleHandler.install(this)
        router = lifecycleHandler.getRouter(main, savedInstanceState)
        overlayRouter = lifecycleHandler.getRouter(overlay, savedInstanceState)

        setContentView(FrameLayout(this).apply {
            layoutParams = params
            addView(main)
            addView(overlay)
        })

        activityComponent.appNavigator().setupContent()
    }

    override fun onBackPressed() {
        if (!overlayRouter.handleBack() && !router.handleBack()) {
            super.onBackPressed()
        }
    }

    /* --------------------------------------------------- */
    /* > Component */
    /* --------------------------------------------------- */

    override fun getComponent(): ActivityComponent {
        return activityComponent
    }

    private val activityComponent: ActivityComponent by lazy {
        MainApp.appComponent!!
                .activityComponent()
                .activityModule(ActivityModule(this))
                .navigatorModule(NavigatorModule(AppNavigator(router, overlayRouter)))
                .build()
    }
}
