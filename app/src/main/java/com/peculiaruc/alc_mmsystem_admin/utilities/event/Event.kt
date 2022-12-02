package com.peculiaruc.alc_mmsystem_admin.utilities.event

/**
 * event handler
 */
open class Event<out T>(private val content: T) {

    /**
     * return content
     */

    fun peekContent(): T = content

    var hasBeenHandled = false
        private set


    /**
     * handles if content is not handled
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

}