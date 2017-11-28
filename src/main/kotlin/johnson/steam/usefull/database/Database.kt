package johnson.steam.usefull.database

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence


/**
 * @author Johnson / 25.11.2017 */
object Database {
    private val LOGGER: Logger = LoggerFactory.getLogger(Database::class.java)
    private val emf: EntityManagerFactory

    init {
        LOGGER.info("-========================= HIBERNATE =========================-")
        try {
            emf = Persistence.createEntityManagerFactory("default")
        } catch (e: Exception) {
            LOGGER.error("Cannot start hibernate", e)
            throw e
        }
    }

    fun getEntityManager(): EntityManager = emf.createEntityManager()

}