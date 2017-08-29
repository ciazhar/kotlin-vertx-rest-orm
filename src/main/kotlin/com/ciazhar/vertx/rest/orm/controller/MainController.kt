package com.ciazhar.vertx.rest.orm.controller

import com.ciazhar.vertx.rest.orm.extension.logger
import io.vertx.ext.web.Router


/**
 * Created by ciazhar on 8/29/17.
 */
class MainController(override val router: Router) : Controller({
    val log = logger(MainController::class)

    /**
     * fungsinya sama seperti router.get('/') di express js
     * sama seperti @GetMapping("/") di spring MVC
     */
    get("/").handler { context ->
        log.info("GET / from client")
        context.response().statusCode = 200
        context.response().end("Hello Word")
    }
})