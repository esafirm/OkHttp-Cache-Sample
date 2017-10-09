package nolambda.cachesample.screens

import android.view.View
import android.widget.TextView
import butterknife.BindView
import nolambda.cachesample.R
import nolambda.cachesample.commons.AbsController
import nolambda.cachesample.navigator.AppNavigator
import javax.inject.Inject

class DetailController : AbsController() {

    @BindView(R.id.main_txt_yeah) lateinit var txtYeah: TextView

    @Inject lateinit var navigator: AppNavigator

    override fun onSetupComponent() {
        component.inject(this)
    }

    override fun getLayoutResId(): Int = R.layout.controller_detail

    override fun onViewBound(bindingResult: View) {
        txtYeah.setOnClickListener {
            navigator.goToApiExample()
        }
    }
}
