package johnson.steam.usefull.database

import johnson.steam.usefull.database.entities.DbGame
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

/**
 * @author Johnson / 25.11.2017 */

object GamesDAO : AutoCloseable {
    private val LOGGER: Logger = LoggerFactory.getLogger(GamesDAO::class.java)

    @PersistenceContext
    private val entityManager: EntityManager = Database.getEntityManager()
    private val transaction: EntityTransaction = entityManager.transaction

    @Transactional
    fun addGameBases(games: List<DbGame>) {
        transaction.begin()
        for (game in games) {
            entityManager.merge(game)
        }
        transaction.commit()
    }

    override fun close() {
        entityManager.transaction.commit()
        entityManager.close()
    }
}