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

class RegisterFragment: BaseFragment() {

    private var mStates: RegisterStates? = null
    private var mAccountRequester: AccountRequester? = null

    var binding: FragmentRegisterBinding? = null;

    fun create(): RegisterFragment {
        return RegisterFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun initViewModel() {
        mStates = getFragmentScopeViewModel(RegisterStates::class.java)
        mAccountRequester = getFragmentScopeViewModel(AccountRequester::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
      return  mStates?.let { DataBindingConfig(R.layout.fragment_register, BR.vm, it) }
    }

    class RegisterStates : StateHolder() {
        val name = State("")
        val password = State("")
        val loadingVisible = State(false)
    }
}