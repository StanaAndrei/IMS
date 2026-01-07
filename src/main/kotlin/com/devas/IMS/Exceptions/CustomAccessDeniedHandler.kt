package com.devas.IMS.Exceptions

import com.fasterxml.jackson.databind.ObjectMapper
import com.devas.IMS.DTOs.Response
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class CustomAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    @Throws(IOException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val errorResponse = Response(
            status = HttpStatus.FORBIDDEN.value(),
            message = accessDeniedException.message
        )

        response.contentType = "application/json"
        response.status = HttpStatus.FORBIDDEN.value()
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}