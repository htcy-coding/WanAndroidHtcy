package com.htcy.wanandroid.ui.page

import android.os.Bundle
import com.htcy.arti.ui.FixedFragmentPagerAdapter
import com.htcy.arti.ui.page.BaseActivity
import com.htcy.arti.ui.page.StateHolder
import com.htcy.wanandroid.R
import com.htcy.wanandroid.BR
import com.htcy.wanandroid.databinding.ActivityAuthBinding
import com.htcy.wanandroid.domain.message.PageMessenger
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.kunminx.architecture.ui.state.State

class AuthActivity: BaseActivity() {

    private var mStates: AuthActivityStates? = null
    private var mMessenger: PageMessenger? = null
    private var loginFragment: LoginFragment? = null
    private var registerFragment: RegisterFragment? = null
    private var mBinding: ActivityAuthBinding? = null

    override fun initViewModel() {
        mStates = getActivityScopeViewModel(AuthActivityStates::class.java)
        mMessenger = getApplicationScopeViewModel(PageMessenger::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_auth, BR.vm, mStates!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = FixedFragmentPagerAdapter(
            supportFragmentManager
        )
        mBinding =  binding as ActivityAuthBinding
        mBinding?.vp?.adapter = adapter
        loginFragment = LoginFragment().create()
        registerFragment = RegisterFragment().create()
        adapter.setFragmentList(loginFragment, registerFragment)
    }

    fun showRegisterView(){
        mBinding?.vp?.currentItem = 1
    }

    fun showLoginView(){
        mBinding?.vp?.currentItem = 0
    }


    class AuthActivityStates : StateHolder() {
        val isDrawerOpened = State(false)
        val openDrawer = State(false)
        val allowDrawerOpen = State(true)
//        val list: State<List<LibraryInfo>> = State<List<LibraryInfo>>(ArrayList<Any>())
    }




}