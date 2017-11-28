package johnson.steam.usefull.jaxrs

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

/**
 * @author Johnson / 24.11.2017 */
@Provider
class JacksonConfigurator : ContextResolver<ObjectMapper> {
    companion object {
        private val mapper: ObjectMapper = ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
    }

    override fun getContext(type: Class<*>?): ObjectMapper = mapper
}