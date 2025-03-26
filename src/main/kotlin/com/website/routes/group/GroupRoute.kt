package com.website.routes.group

import com.website.cache.group.GroupCache
import com.website.model.user.group.Group
import com.website.repository.impl.PostgresGroupRepository
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createCreateGroupRoute() {
    post("/groups") {
        try {
            val groupReceived = call.receive<Group>()
            val groupDataReceived = groupReceived.groupData

            val nameReceived = groupDataReceived.name
            if (nameReceived.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "group name cant be empty")
                return@post
            }

            val descriptionReceived = groupDataReceived.description
            if (descriptionReceived.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "group description cant be empty")
                return@post
            }

            val colorReceived = groupDataReceived.color
            if (colorReceived.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "group color cant be empty")
                return@post
            }

            GroupCache.addGroup(groupReceived)
            PostgresGroupRepository.save(groupReceived)

            call.respond(HttpStatusCode.Created, "group '${groupReceived.id}' created")
        } catch (_: IllegalStateException) {
            call.respond(HttpStatusCode.BadRequest, "cant process this request")
        } catch (_: JsonConvertException) {
            call.respond(HttpStatusCode.BadRequest, "cant convert json to group")
        }
    }
}

fun Route.createGetGroupRoute() {
    get("/groups/{groupId") {
        val groupId = call.parameters["groupId"]
        if (groupId.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "group id parameter cant be empty")
            return@get
        }

        val group = GroupCache.getGroup(groupId)
        if (group == null) {
            call.respond(HttpStatusCode.NotFound, "this group cant be found")
            return@get
        }

        call.respond(HttpStatusCode.OK, group)
    }
}

fun Route.createUpdateGroupRoute() {
    put("/groups/{groupId}") {
        val groupId = call.parameters["groupId"]
        if (groupId.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "group id parameter cant be empty")
            return@put
        }

        val group = GroupCache.getGroup(groupId)
        if (group == null) {
            call.respond(HttpStatusCode.NotFound, "this group cant be found")
            return@put
        }

        val groupBody = call.receive<Group>()
        if (groupBody.id != groupId) {
            call.respond(HttpStatusCode.BadRequest, "group id parameter is different that group id in the body")
            return@put
        }

        val groupDataReceived = groupBody.groupData

        val nameBody = groupDataReceived.name
        if (nameBody.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "group name cant be empty")
            return@put
        }

        val descriptionBody = groupDataReceived.description
        if (descriptionBody.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "group description cant be empty")
            return@put
        }

        val colorBody = groupDataReceived.color
        if (colorBody.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "group color cant be empty")
            return@put
        }

        GroupCache.removeGroup(groupId)

        GroupCache.addGroup(groupBody)
        PostgresGroupRepository.save(groupBody)

        call.respond(HttpStatusCode.OK, "group '${group}' updated")
    }
}

fun Route.createGetAllGroupsRoute() {
    get("/groups") {
        call.respond(HttpStatusCode.OK, GroupCache.values)
    }
}

fun Route.createDeleteGroupRoute() {
    delete("/groups/{groupId}") {
        val groupId = call.parameters["groupId"]
        if (groupId.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "group id parameter cant be empty")
            return@delete
        }

        val group = GroupCache.getGroup(groupId)
        if (group == null) {
            call.respond(HttpStatusCode.NotFound, "this group cant be found")
            return@delete
        }

        GroupCache.removeGroup(groupId)
        PostgresGroupRepository.delete(group)

        call.respond(HttpStatusCode.OK, "group '$groupId' deleted")
    }
}