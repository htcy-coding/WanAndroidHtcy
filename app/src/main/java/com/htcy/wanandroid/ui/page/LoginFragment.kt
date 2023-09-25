package com.htcy.wanandroid.ui.page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.htcy.arti.ui.page.BaseFragment
import com.htcy.arti.ui.page.StateHolder
import com.htcy.wanandroid.BR
import com.htcy.wanandroid.R
import com.htcy.wanandroid.domain.request.AccountRequester
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.kunminx.architecture.ui.state.State

class LoginFragment : BaseFragment() {


    private var mStates: LoginStates? = null
    private var mAccountRequester: AccountRequester? = null
    //登录

    fun create(): LoginFragment {
        return LoginFragment()
    }

    override fun initViewModel() {
        mStates = getFragmentScopeViewModel(LoginStates::class.java)
        mAccountRequester = getFragmentScopeViewModel(AccountRequester::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return mStates?.let { DataBindingConfig(R.layout.fragment_login, BR.vm, it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAccountRequester?.let {
            lifecycle.addObserver(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    inner class ClickProxy {
        fun showRegister() {
            (mActivity as AuthActivity).showRegisterView()
        }
//
//        fun login() {
//            //TODO tip 5：通过双向绑定，使能通过 state-ViewModel 中与 xml 控件发生绑定的"可观察数据" 拿到控件数据，
//            // 避免直接接触控件实例而埋下 Null 安全一致性隐患。
//
//            //如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350
//            if (TextUtils.isEmpty(mStates.name.get()) || TextUtils.isEmpty(mStates.password.get())) {
//                ToastUtils.showLongToast(getString(R.string.username_or_pwd_incomplete))
//                return
//            }
//            val user = User(mStates.name.get(), mStates.password.get())
//            mAccountRequester.requestLogin(user)
//            mStates.loadingVisible.set(true)
    }

    class LoginStates : StateHolder() {
        val name = State("")
        val password = State("")
        val loadingVisible = State(false)
    }

}