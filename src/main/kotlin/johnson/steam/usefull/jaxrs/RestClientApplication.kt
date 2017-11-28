package johnson.steam.usefull.jaxrs

import johnson.steam.usefull.database.Database
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application
import javax.ws.rs.core.Context

/** @author karpov-em on 24.11.2017*/
@ApplicationPath("/api")
class RestClientApplication : Application() {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(RestClientApplication::class.java)
    }

    private lateinit var emf: EntityManagerFactory
    private lateinit var em: EntityManager

    @Context
    private fun getContext() {
        try {
            Database.getEntityManager()
        } catch (e: Exception) {
            LOGGER.error("Error during starting hibernate", e)
        }

        LOGGER.info("-========================= SERVER STARTED =========================-")
    }
}