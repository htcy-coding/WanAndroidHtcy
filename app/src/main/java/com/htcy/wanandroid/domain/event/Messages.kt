package com.htcy.wanandroid.domain.event

class Messages(val eventId: Int) {
    companion object {
        const val EVENT_CLOSE_SLIDE_PANEL_IF_EXPANDED = 1
        const val EVENT_CLOSE_ACTIVITY_IF_ALLOWED = 2
        const val EVENT_OPEN_DRAWER = 3
        const val EVENT_ADD_SLIDE_LISTENER = 4
        const val EVENT_LOGIN_SUCCESS = 5
    }
}
