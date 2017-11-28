package johnson.steam.usefull.jaxrs.handling

import johnson.steam.usefull.exceptions.IApiError
import johnson.steam.usefull.exceptions.ApiException
import java.util.concurrent.ConcurrentHashMap
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

/** Обработчик-маппер ошибок логики ApiException. Транслирует ошибку в JSON ответ.
 *
 * @author karpov-em on 24.11.2017*/
@Provider
class ApiExceptionMapper : ExceptionMapper<ApiException> {
    companion object {
        private val responseCache: MutableMap<Class<IApiError>, Response.ResponseBuilder> = ConcurrentHashMap()

        fun getResponse(exception: ApiException): Response.ResponseBuilder {
            if (ApiExceptionMapper.responseCache.containsKey(exception.getApiError().javaClass)) {
                return ApiExceptionMapper.responseCache[exception.getApiError().javaClass]!!
            }
            val response = Response.status(exception.getStatus())
                    .entity(exception)
                    .type(MediaType.APPLICATION_JSON_TYPE)
            ApiExceptionMapper.responseCache.put(exception.getApiError().javaClass, response)
            return response
        }

        fun getResponse(exception: IApiError): Response.ResponseBuilder {
            if (ApiExceptionMapper.responseCache.containsKey(exception.javaClass)) {
                return ApiExceptionMapper.responseCache[exception.javaClass]!!
            }
            return getResponse(ApiException(exception))
        }
    }

    override fun toResponse(exception: ApiException): Response = getResponse(exception).build()
}