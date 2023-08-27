package com.htcy.wanandroid.ui.page

import android.os.Bundle
import com.htcy.arti.ui.page.BaseActivity
import com.htcy.arti.ui.page.StateHolder
import com.htcy.wanandroid.R
import com.htcy.wanandroid.BR
import com.htcy.wanandroid.domain.message.PageMessenger
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.kunminx.architecture.ui.state.State
import java.util.ArrayList

class AuthActivity: BaseActivity() {

    private var mStates: AuthActivityStates? = null

    private var mMessenger: PageMessenger? = null



    override fun initViewModel() {
        mStates = getActivityScopeViewModel(AuthActivityStates::class.java)
        mMessenger = getApplicationScopeViewModel(PageMessenger::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_auth, BR.vm, mStates!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)


    }


    class AuthActivityStates : StateHolder() {
        val isDrawerOpened = State(false)
        val openDrawer = State(false)
        val allowDrawerOpen = State(true)
//        val list: State<List<LibraryInfo>> = State<List<LibraryInfo>>(ArrayList<Any>())

    }


}