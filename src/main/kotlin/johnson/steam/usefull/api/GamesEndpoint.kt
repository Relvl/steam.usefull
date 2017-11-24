package johnson.steam.usefull.api

import com.fasterxml.jackson.annotation.JsonProperty
import johnson.steam.usefull.steam.model.SteamGame
import javax.annotation.ManagedBean
import javax.inject.Singleton
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
        //throw ApiException(EGeneralApiError.BAD_ARGUMENT)
        throw Exception("Test exception")
    }

    @GET
    @Path("{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getLoadGame(@PathParam("gameId") @DefaultValue("0") gameId: Int, @Context request: HttpServletRequest): IResponse {

        SteamGame.updateGameFromSteam(gameId)

        return object : IResponse {
            @JsonProperty
            var test: Int = gameId
        }
    }
}