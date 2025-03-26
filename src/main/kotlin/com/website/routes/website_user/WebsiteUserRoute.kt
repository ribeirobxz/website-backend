package com.website.routes.website_user

import com.website.model.user.WebsiteUser
import com.website.repository.impl.PostgresWebsiteUserRepository
import com.website.util.JWTUtil
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createCreateWebsiteUserRoute() {
    post("/users") {
        try {
            val websiteUserReceived = call.receive<WebsiteUser>()

            val playerNameReceived = websiteUserReceived.playerName
            if (playerNameReceived.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "playername cant be empty")
                return@post
            }

            val passWordReceived = websiteUserReceived.passWord
            if (passWordReceived.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "password cant be empty")
                return@post
            }

            val groupsReceived = websiteUserReceived.groups
            if (groupsReceived.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "groups cant be empty")
                return@post
            }

            val websiteUser = PostgresWebsiteUserRepository.findByName(websiteUserReceived.playerName)
            if (websiteUser != null) {
                call.respond(HttpStatusCode.BadRequest, "already exist user with this playerName")
                return@post
            }

            PostgresWebsiteUserRepository.save(websiteUserReceived)

            val token = JWTUtil.createToken(websiteUserReceived)
            call.respond(hashMapOf("token" to token))
        } catch (_: IllegalStateException) {
            call.respond(HttpStatusCode.BadRequest, "cant process this request")
        } catch (_: JsonConvertException) {
            call.respond(HttpStatusCode.BadRequest, "cant convert json to website user")
        }
    }
}

fun Route.createGetAllWebsiteUsersRoute() {
    get("/users") {
        val websiteUsers = PostgresWebsiteUserRepository.findAll()
        call.respond(HttpStatusCode.OK, websiteUsers)
    }
}

fun Route.createDeleteAllWebsiteUsersRoute() {
    delete("/users") {
        PostgresWebsiteUserRepository.deleteAll()
        call.respond(HttpStatusCode.OK, "deleted all website users")
    }
}

fun Route.createGetWebsiteUserRoute() {
    route("/users") {
        route("/{playername}") {
            get {
                val playerName = call.parameters["playername"]
                if (playerName.isNullOrEmpty()) {
                    call.respond(HttpStatusCode.BadRequest, "invalid playername")
                    return@get
                }

                val websiteUser = PostgresWebsiteUserRepository.findByName(playerName)
                if (websiteUser == null) {
                    call.respond(HttpStatusCode.NotFound, "not found this user")
                    return@get
                }

                call.respond(HttpStatusCode.OK, websiteUser)
            }

            get("/groups") {
                val playerName = call.parameters["playername"]
                if (playerName.isNullOrEmpty()) {
                    call.respond(HttpStatusCode.BadRequest, "invalid playername")
                    return@get
                }

                val websiteUser = PostgresWebsiteUserRepository.findByName(playerName)
                if (websiteUser == null) {
                    call.respond(HttpStatusCode.NotFound, "not found this user")
                    return@get
                }

                call.respond(HttpStatusCode.OK, websiteUser.groups)
            }
        }
    }
}

fun Route.createUpdateWebsiteUserRoute() {
    put("/users/{playername}") {
        val playerName = call.parameters["playername"]
        if (playerName.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "invalid playername")
            return@put
        }

        val websiteUser = PostgresWebsiteUserRepository.findByName(playerName)
        if (websiteUser == null) {
            call.respond(HttpStatusCode.NotFound, "not found this user")
            return@put
        }

        val websiteUserReceived = call.receive<WebsiteUser>()
        if (websiteUserReceived.playerName != playerName) {
            call.respond(HttpStatusCode.BadRequest, "player name parameter is different that player name in the body")
            return@put
        }

        val passWordReceived = websiteUserReceived.passWord
        if (passWordReceived.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "password cant be empty")
            return@put
        }

        PostgresWebsiteUserRepository.update(websiteUserReceived)
        call.respond(HttpStatusCode.OK, "updated user '${websiteUserReceived.playerName}' informations")
    }
}

fun Route.createDeleteWebsiteUserRoute() {
    delete("/users/{playername}") {
        val playerName = call.parameters["playername"]
        if (playerName.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "invalid playername")
            return@delete
        }

        val websiteUser = PostgresWebsiteUserRepository.findByName(playerName)
        if (websiteUser == null) {
            call.respond(HttpStatusCode.NotFound, "not found this user")
            return@delete
        }

        PostgresWebsiteUserRepository.delete(websiteUser)
        call.respond(HttpStatusCode.OK, "user '${websiteUser.playerName}' deleted")
    }
}