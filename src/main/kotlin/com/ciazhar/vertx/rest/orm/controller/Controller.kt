package com.ciazhar.vertx.rest.orm.controller

import io.vertx.ext.web.Router


/**
 * Created by ciazhar on 8/29/17.
 */
abstract class Controller(val handlers: Router.() -> Unit) {
    abstract val router: Router
    fun create(): Router {
        return router.apply {
            handlers()
        }
    }
}