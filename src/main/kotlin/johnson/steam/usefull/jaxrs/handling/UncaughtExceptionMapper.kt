package johnson.steam.usefull.jaxrs.handling

import johnson.steam.usefull.exceptions.EGeneralApiError
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

/** Обработчик-маппер ошибок логики ApiException. Транслирует ошибку в JSON ответ.
 *
 * @author karpov-em on 24.11.2017*/
@Provider
class UncaughtExceptionMapper : ExceptionMapper<Exception> {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(ExceptionMapper::class.java)
    }

    override fun toResponse(exception: Exception): Response {
        LOGGER.error("Uncaught exception falled to servlet", exception)
        return ApiExceptionMapper.getResponse(EGeneralApiError.GENERAL_SERVER_ERROR)
    }
}