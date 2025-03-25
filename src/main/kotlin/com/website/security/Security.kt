package com.website.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.website.dotenv
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*


fun Application.configureSecurity() {

    val jwtAudience = dotenv["JWT_AUDIENCE"]
    val jwtDomain = dotenv["JWT_DOMAIN"]
    val jwtRealm = dotenv["JWT_REALM"]
    val jwtSecret = dotenv["JWT_SECRET"]

    authentication {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )

            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }

            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}
