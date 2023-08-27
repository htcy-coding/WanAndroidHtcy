package com.htcy.wanandroid.domain.message

import com.htcy.wanandroid.domain.event.Messages
import com.kunminx.architecture.domain.dispatch.MviDispatcher

class PageMessenger : MviDispatcher<Messages>() {
    override fun onHandle(intent: Messages) {
        sendResult(intent)
    }
}