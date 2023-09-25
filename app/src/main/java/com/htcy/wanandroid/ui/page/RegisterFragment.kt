package com.htcy.wanandroid.ui.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.htcy.arti.ui.page.BaseFragment
import com.htcy.arti.ui.page.StateHolder
import com.htcy.wanandroid.BR
import com.htcy.wanandroid.R
import com.htcy.wanandroid.databinding.FragmentRegisterBinding
import com.htcy.wanandroid.domain.request.AccountRequester
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.kunminx.architecture.ui.state.State
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class RegisterFragment: BaseFragment() {

    private var mStates: RegisterStates? = null
    private var mAccountRequester: AccountRequester? = null
    private var mBinding: FragmentRegisterBinding? = null;


    fun create(): RegisterFragment {
        return RegisterFragment()
    }

    override fun initViewModel() {
        mStates = getFragmentScopeViewModel(RegisterStates::class.java)
        mAccountRequester = getFragmentScopeViewModel(AccountRequester::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
      return  mStates?.let { DataBindingConfig(R.layout.fragment_register, BR.vm, it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = binding as? FragmentRegisterBinding


    }

    inner class ClickProxy {
//        fun showRegister() {
//            (mActivity as AuthActivity).showRegisterView()
//        }

        fun startRegister(){
            if (mStates?.name?.get().isNullOrEmpty1())

        }



    }

    class RegisterStates : StateHolder() {
        val name = State("")
        val password = State("")
        val rePassword = State("")
        val loadingVisible = State(false)
    }


    fun String.lastChar(): Char = this.get(this.length - 1)

     fun CharSequence?.isNullOrEmpty1(): Boolean {
        return this == null || this.length == 0
    }


}