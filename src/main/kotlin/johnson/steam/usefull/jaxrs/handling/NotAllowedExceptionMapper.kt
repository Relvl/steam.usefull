package johnson.steam.usefull.jaxrs.handling

import johnson.steam.usefull.exceptions.EGeneralApiError
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.ws.rs.NotAllowedException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

/** Обработчик-маппер ошибок логики ApiException. Транслирует ошибку в JSON ответ.
 *
 * @author karpov-em on 24.11.2017*/
@Provider
class NotAllowedExceptionMapper : ExceptionMapper<NotAllowedException> {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(NotAllowedExceptionMapper::class.java)
    }

    override fun toResponse(exception: NotAllowedException): Response {
        LOGGER.error("No endpoints for this method: {}", exception.toString())
        return ApiExceptionMapper.getResponse(EGeneralApiError.METHOD_NOT_ALLOWED).build()
    }
}