package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientRequestFilter
import javax.ws.rs.core.MediaType


/** @author karpov-em on 24.11.2017*/
class SteamGame {

    @JsonProperty
    private var type: String = "game" // TODO@karpov: Enum?

    @JsonProperty
    private var name: String = ""

    @JsonProperty("steam_appid")
    private var appId: Int = 0

    @JsonProperty("required_age")
    private var requiredAge: Int = 0

    @JsonProperty("is_free")
    private var isFree: Boolean = false

    @JsonProperty("controller_support")
    private var controllerSupport: String = "none"// TODO@karpov: Enum?

    @JsonProperty("dlc")
    private var dlc: List<Int> = Collections.emptyList()

    @JsonProperty("detailed_description")
    private var detailedDescription: String? = null

    @JsonProperty("about_the_game")
    private var aboutTheGame: String? = null

    @JsonProperty("short_description")
    private var shortDescription: String? = null

    @JsonProperty("supported_languages")
    private var supportedLangusges: String = "english"

    @JsonProperty("reviews")
    private var reviews: String? = null

    @JsonProperty("header_image")
    private var headerImage: String? = null

    @JsonProperty("website")
    private var website: String? = null

    @JsonProperty("pc_requirements")
    private var pcRequirements: Requirements? = null
    @JsonProperty("mac_requirements")
    private var macRequirements: Requirements? = null
    @JsonProperty("linux_requirements")
    private var linuxRequirements: Requirements? = null

    @JsonProperty("legal_notice")
    private var legalNotice: String? = null

    @JsonProperty("developers")
    private var developers: List<String> = Collections.emptyList()
    @JsonProperty("publishers")
    private var publishers: List<String> = Collections.emptyList()

    @JsonProperty("price_overview")
    private var priceOverview: PriceOverview? = null

    @JsonProperty("packages")
    private var packages: List<Int> = Collections.emptyList()

    @JsonProperty("package_groups")
    private var packageGroups: List<PackageGroup> = Collections.emptyList()

    // TODO@karpov: продолжить


    // -------------------------------------------------------------------------------------------------
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(SteamGame::class.java)

        fun updateGameFromSteam(gameId: Int): SteamGame? {
            val client = ClientBuilder
                    .newBuilder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .register(LogFilter())
                    .build()
            val webTarget = client.target("http://store.steampowered.com/api").path("appdetails").queryParam("appids", gameId)

            val invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON + ";charset=utf-8")
            val response = invocationBuilder.get(SteamGameResponse::class.java)

            LOGGER.info("Response reached")

            return null
        }
    }

    class LogFilter : ClientRequestFilter {
        override fun filter(requestContext: ClientRequestContext?) {
            LOGGER.info(requestContext?.entity.toString())
        }
    }

    class SteamGameResponse {
        @JsonAnySetter
        fun addGame(gameId: String, gameObject: SteamGameWrapper) {
            println("Game id: $gameId, obj: $gameObject")
        }
    }

    class SteamGameWrapper {
        @JsonProperty
        private var success: Boolean = false
        @JsonProperty
        private var data: SteamGame? = null
    }

}