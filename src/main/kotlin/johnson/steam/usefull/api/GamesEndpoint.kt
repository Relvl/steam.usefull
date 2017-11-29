package johnson.steam.usefull.api

import com.fasterxml.jackson.annotation.JsonProperty
import johnson.steam.usefull.database.Database
import johnson.steam.usefull.database.GamesDAO
import johnson.steam.usefull.database.entities.DbGame
import johnson.steam.usefull.steam.SteamClient
import johnson.steam.usefull.steam.model.SteamGameSimple
import javax.annotation.ManagedBean
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType

/** @author karpov-em on 24.11.2017*/
@ManagedBean
@Singleton
@Path("/game")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
class GamesEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllGames(@Context request: HttpServletRequest): IResponse {
        val g = DbGame(-1, "Test game")
        val entityManager: EntityManager = Database.getEntityManager()
        entityManager.persist(g)

        return object : IResponse {}
    }

    @GET
    @Path("{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getLoadGame(@PathParam("gameId") @DefaultValue("0") gameId: Int, @Context request: HttpServletRequest): IResponse {

        SteamClient.updateGameFromSteam(gameId)

        return object : IResponse {
            @JsonProperty
            var test: Int = gameId
        }
    }

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    fun doTest(): IResponse {
        return object : IResponse {
            @JsonProperty
            var result: String = "test ok"
        }
    }

    @POST
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    fun doTestPost(): IResponse {
        Thread.sleep(3000)
        return object : IResponse {
            @JsonProperty
            var result: String = "test ok"
        }
    }

    @GET
    @Path("gameList")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllGames(): IResponse {

        val games: List<SteamGameSimple> = SteamClient.loadAllGames()
        val dbGames: MutableList<DbGame> = ArrayList()
        for (game in games) {
            dbGames.add(DbGame(game.appId, game.name))
        }
        GamesDAO.addGameBases(dbGames)

        return object : IResponse {}
    }
}