package com.website.routes

import com.website.routes.website_user.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        createCreateWebsiteUserRoute()
        createGetWebsiteUserRoute()

        createGetAllWebsiteUsersRoute()
        createUpdateWebsiteUserRoute()

        authenticate("auth-jwt") {
            createDeleteAllWebsiteUsersRoute()
            createDeleteWebsiteUserRoute()
        }
    }
}
