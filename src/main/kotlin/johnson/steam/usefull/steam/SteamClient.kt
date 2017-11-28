package johnson.steam.usefull.steam

import johnson.steam.usefull.jaxrs.JacksonConfigurator
import johnson.steam.usefull.steam.model.SteamGame
import johnson.steam.usefull.steam.model.SteamGameSimple
import org.glassfish.jersey.jackson.JacksonFeature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientRequestFilter
import javax.ws.rs.core.MediaType

/** @author karpov-em on 24.11.2017*/
class SteamClient {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(SteamClient::class.java)

        private val clientBuilder: ClientBuilder = ClientBuilder
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .register(LogFilter::class.java)
                .register(JacksonFeature::class.java)
                .register(JacksonConfigurator::class.java)

        fun updateGameFromSteam(gameId: Int): SteamGame? {
            val webTarget = clientBuilder.build().target("http://store.steampowered.com/api").path("appdetails").queryParam("appids", gameId)
            val invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=utf-8")
            val response = invocationBuilder.get(SteamGame.SteamGameResponse::class.java)
            LOGGER.info("Response reached")
            return null
        }

        fun loadAllGames(): List<SteamGameSimple> {
            val webTarget = clientBuilder.build().target("http://api.steampowered.com")
                    .path("ISteamApps")
                    .path("GetAppList")
                    .path("v0002/")
                    .queryParam("key", "A02EE4D0F03C51AD8FCBDF5935D08666")
                    .queryParam("format", "json")
            val invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=utf-8")
            val response = invocationBuilder.get(SteamGameSimple.GetAppListResponse::class.java)
            LOGGER.info("Response reached")
            return response.appList.apps
        }
    }

    class LogFilter : ClientRequestFilter {
        override fun filter(requestContext: ClientRequestContext?) {
            if (requestContext != null) {
                if (requestContext.hasEntity()) {
                    LOGGER.info("Request to {}: {}", requestContext.uri, requestContext.entity.toString())
                } else {
                    LOGGER.info("Request to {}", requestContext.uri)
                }
            }
        }
    }

}