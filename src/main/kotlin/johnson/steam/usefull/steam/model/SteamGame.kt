package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


/** @author karpov-em on 24.11.2017*/
//http://api.steampowered.com/ISteamApps/GetAppList/v0002/?key=A02EE4D0F03C51AD8FCBDF5935D08666&format=json
class SteamGame {

    @JsonProperty
    var type: String = "game" // TODO@karpov: Enum?

    @JsonProperty
    var name: String = ""

    @JsonProperty("steam_appid")
    var appId: Int = 0

    @JsonProperty("required_age")
    var requiredAge: Int = 0

    @JsonProperty("is_free")
    var isFree: Boolean = false

    @JsonProperty("controller_support")
    var controllerSupport: String = "none"// TODO@karpov: Enum?

    @JsonProperty("dlc")
    var dlc: List<Int> = Collections.emptyList()

    @JsonProperty("detailed_description")
    var detailedDescription: String? = null

    @JsonProperty("about_the_game")
    var aboutTheGame: String? = null

    @JsonProperty("short_description")
    var shortDescription: String? = null

    @JsonProperty("supported_languages")
    var supportedLangusges: String = "english"

    @JsonProperty("reviews")
    var reviews: String? = null

    @JsonProperty("header_image")
    var headerImage: String? = null

    @JsonProperty("website")
    var website: String? = null

    @JsonProperty("pc_requirements")
    var pcRequirements: Requirements? = null
    @JsonProperty("mac_requirements")
    var macRequirements: Requirements? = null
    @JsonProperty("linux_requirements")
    var linuxRequirements: Requirements? = null

    @JsonProperty("legal_notice")
    var legalNotice: String? = null

    @JsonProperty("developers")
    var developers: List<String> = Collections.emptyList()
    @JsonProperty("publishers")
    var publishers: List<String> = Collections.emptyList()

    @JsonProperty("demos")
    var demos: DemoInfo? = null

    @JsonProperty("price_overview")
    var priceOverview: PriceOverview? = null

    @JsonProperty("packages")
    var packages: List<Int> = Collections.emptyList()

    @JsonProperty("package_groups")
    var packageGroups: List<PackageGroup> = Collections.emptyList()

    @JsonProperty("platforms")
    var platforms: PlatformsList? = null

    @JsonProperty("metacritic")
    var metacritic: MetacriticData? = null

    @JsonProperty("categories")
    var categories: List<Category> = Collections.emptyList()
    @JsonProperty("genres")
    var genres: List<Category> = Collections.emptyList()

    @JsonProperty("screenshots")
    var screenshots: List<ScreenshotData> = Collections.emptyList()

    @JsonProperty("movies")
    var movies: List<MovieData> = Collections.emptyList()

    @JsonProperty("recommendations")
    var recommendations: Recomendations? = null

    @JsonProperty("achievements")
    var achievements: AchievementsList? = null

    @JsonProperty("release_date")
    var releaseDate: ReleaseDate? = null

    @JsonProperty("support_info")
    var supportInfo: SupportData? = null

    @JsonProperty("background")
    var background: String? = null

    class SteamGameResponse {
        @JsonAnySetter
        fun addGame(gameId: String, gameObject: SteamGameWrapper) {
            println("Game id: $gameId, obj: $gameObject")
        }
    }

    class SteamGameWrapper {
        @JsonProperty
        var success: Boolean = false

        @JsonProperty
        var data: SteamGame? = null
    }

}