package com.website.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.website.dotenv
import com.website.model.user.WebsiteUser
import java.util.*

object JWTUtil {

    fun createToken(websiteUser: WebsiteUser): String {

        val jwtAudience = dotenv["JWT_AUDIENCE"]
        val jwtDomain = dotenv["JWT_DOMAIN"]
        val jwtSecret = dotenv["JWT_SECRET"]

        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtDomain)
            .withClaim("username", websiteUser.playerName)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000000))
            .sign(Algorithm.HMAC256(jwtSecret))
    }
}