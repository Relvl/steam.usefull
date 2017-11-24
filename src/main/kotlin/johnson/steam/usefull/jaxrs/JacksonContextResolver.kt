package johnson.steam.usefull.jaxrs

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider


/** Настройки джексона для JaxRs джерси
 *
 * @author karpov-em on 24.11.2017 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
class JacksonContextResolver : ContextResolver<ObjectMapper> {
    private val objectMapper: ObjectMapper = ObjectMapper()

    init {
        this.objectMapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
    }

    override fun getContext(type: Class<*>?): ObjectMapper = objectMapper
}