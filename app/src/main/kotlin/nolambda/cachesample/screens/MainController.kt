package nolambda.cachesample.screens

import android.view.View
import android.widget.TextView
import butterknife.BindView
import nolambda.cachesample.R
import nolambda.cachesample.commons.AbsController
import nolambda.cachesample.navigator.AppNavigator
import javax.inject.Inject

class MainController : AbsController() {

    @Inject lateinit var navigator: AppNavigator

    @BindView(R.id.main_txt_hello) lateinit var textView: TextView

    override fun getLayoutResId(): Int = R.layout.controller_main

    override fun onViewBound(bindingResult: View) {
        component.inject(this)

        textView.setOnClickListener {
            navigator.goToDetail()
        }
    }
}
