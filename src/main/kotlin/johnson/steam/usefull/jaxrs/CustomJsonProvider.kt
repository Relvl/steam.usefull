package johnson.steam.usefull.jaxrs

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.Provider

/**
 * @author Johnson / 24.11.2017 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
class CustomJsonProvider : JacksonJsonProvider() {
    companion object {
        private val mapper: ObjectMapper = ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
    }

    init {
        setMapper(mapper)
    }
}