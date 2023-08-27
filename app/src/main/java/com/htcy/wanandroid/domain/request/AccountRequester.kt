package com.htcy.wanandroid.domain.request

import androidx.lifecycle.DefaultLifecycleObserver
import com.htcy.arti.domain.request.Requester
import io.reactivex.disposables.Disposable

class AccountRequester: Requester(), DefaultLifecycleObserver {

    //TODO tip 5：模拟可取消的登录请求：
    //
    // 配合可观察页面生命周期的 accountRequest，
    // 从而在页面即将退出、且登录请求由于网络延迟尚未完成时，
    // 及时通知数据层取消本次请求，以避免资源浪费和一系列不可预期的问题。

    //TODO tip 5：模拟可取消的登录请求：
    //
    // 配合可观察页面生命周期的 accountRequest，
    // 从而在页面即将退出、且登录请求由于网络延迟尚未完成时，
    // 及时通知数据层取消本次请求，以避免资源浪费和一系列不可预期的问题。
    private val mDisposable: Disposable? = null

}