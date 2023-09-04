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

class LoginFragment: BaseFragment() {


    //切换到注册

    //TODO tip 1：基于 "单一职责原则"，应将 ViewModel 划分为 state-ViewModel 和 result-ViewModel，
    // state-ViewModel 职责仅限于托管、保存和恢复本页面 state，作用域仅限于本页面，
    // result-ViewModel 职责仅限于 "消息分发" 场景承担 "可信源"，作用域依 "数据请求" 或 "跨页通信" 消息分发范围而定
    // 如这么说无体会，详见 https://xiaozhuanlan.com/topic/8204519736
    private var mStates: LoginStates? = null
    private var mAccountRequester: AccountRequester? = null
    //登录

    fun create(): LoginFragment {
        return LoginFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun initViewModel() {
        mStates = getFragmentScopeViewModel(LoginStates::class.java)
        mAccountRequester = getFragmentScopeViewModel(AccountRequester::class.java)
    }

        override fun getDataBindingConfig(): DataBindingConfig? {
        //TODO tip 2: DataBinding 严格模式：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这方式，彻底解决 View 实例 Null 安全一致性问题，
        // 如此，View 实例 Null 安全性将和基于函数式编程思想的 Jetpack Compose 持平。
        // 而 DataBindingConfig 就是在这样背景下，用于为 base 页面 DataBinding 提供绑定项。

        // 如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
        return mStates?.let { DataBindingConfig(R.layout.fragment_login, BR.vm, it) }
    }

    class ClickProxy {
//        fun back() {
//            nav().navigateUp()
//        }
//
//        fun login() {
//
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

    //TODO tip 6：基于单一职责原则，抽取 Jetpack ViewModel "状态保存和恢复" 的能力作为 StateHolder，
    // 并使用 ObservableField 的改良版子类 State 来承担 BehaviorSubject，用作所绑定控件的 "可信数据源"，
    // 从而在收到来自 PublishSubject 的结果回推后，响应结果数据的变化，也即通知控件属性重新渲染，并为其兜住最后一次状态，
    //如这么说无体会，详见 https://xiaozhuanlan.com/topic/6741932805
    class LoginStates : StateHolder() {
        val name = State("")
        val password = State("")
        val loadingVisible = State(false)
    }

}