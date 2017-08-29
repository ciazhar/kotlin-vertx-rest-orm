package com.ciazhar.vertx.rest.orm

import com.ciazhar.vertx.rest.orm.controller.MainController
import com.ciazhar.vertx.rest.orm.extension.logger
import com.ciazhar.vertx.rest.orm.extension.propertiesConfiguration
import com.ciazhar.vertx.rest.orm.extension.retrieveConfig
import com.ciazhar.vertx.rest.orm.extension.useLogBack
import com.ciazhar.vertx.rest.orm.verticle.MainVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

/**
 * Created by ciazhar on 8/29/17.
 */

class Application {

    /**
     * companion object agar bisa di akses ke luar class
     * companion object sama dengan static di java
     */
    companion object {
        /**
         * karena menggunakan companion object maka fun main bersifat private,
         * agar dapat diakses maka digunakan fungsi @JvmStatic
         * Selain @JvmStatic, terdapat fungsi lain yaitu
         * lateinit dan const
         */
        @JvmStatic
        fun main(args: Array<String>) {
            /**
             * load konfigurasi logback
             */
            useLogBack()
            /**
             * inisialisasi logger pada class ini
             */
            val log = logger(Application::class)

            log.info("Inisialisasi Vertx")
            /**
             * inisialisasi object vertex
             */
            val vertex = Vertx.vertx()
            /**
             * load konfigurasi file application-config.properties
             */
            val configurationProperties = propertiesConfiguration("application-config.properties")
            /**
             * konfigurasi file properties ke vertex
             */
            val configuration = vertex.retrieveConfig(configurationProperties).toBlocking().first()
            /**
             * inisialisasi router vertx
             */
            val router = Router.router(vertex)

            log.info("Deploy Main Verticle")
            val mainController = MainController(router)
            val mainVerticle = MainVerticle(mainController)

            /**
             * konfigurasi vertx untuk load konfigurasi diatas
             */
            vertex.deployVerticle(mainVerticle, DeploymentOptions().apply {
                this.config = configuration
            })
            log.info("Application Success Running")
        }
    }
}