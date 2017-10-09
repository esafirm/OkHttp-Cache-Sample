package nolambda.cachesample.di.components

import android.app.Application
import dagger.Component
import nolambda.cachesample.di.modules.AppModule
import nolambda.cachesample.di.modules.ContextServiceModule
import nolambda.cachesample.di.modules.NetworkModule
import nolambda.cachesample.network.ApiService
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ContextServiceModule::class,
        NetworkModule::class
))
interface AppComponent {

    /* --------------------------------------------------- */
    /* > Singletons */
    /* --------------------------------------------------- */

    fun apiService(): ApiService

    /* --------------------------------------------------- */
    /* > Subcomponent */
    /* --------------------------------------------------- */

    fun activityComponent(): ActivityComponent.Builder

    companion object {
        fun initialize(app: Application): AppComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule((app)))
                        .build()
    }
}
