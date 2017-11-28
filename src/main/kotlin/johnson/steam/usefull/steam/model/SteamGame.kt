package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


/** @author karpov-em on 24.11.2017*/
//http://api.steampowered.com/ISteamApps/GetAppList/v0002/?key=A02EE4D0F03C51AD8FCBDF5935D08666&format=json
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

    @JsonProperty("demos")
    private var demos: DemoInfo? = null

    @JsonProperty("price_overview")
    private var priceOverview: PriceOverview? = null

    @JsonProperty("packages")
    private var packages: List<Int> = Collections.emptyList()

    @JsonProperty("package_groups")
    private var packageGroups: List<PackageGroup> = Collections.emptyList()

    @JsonProperty("platforms")
    private var platforms: PlatformsList? = null

    @JsonProperty("metacritic")
    private var metacritic: MetacriticData? = null

    @JsonProperty("categories")
    private var categories: List<Category> = Collections.emptyList()
    @JsonProperty("genres")
    private var genres: List<Category> = Collections.emptyList()

    @JsonProperty("screenshots")
    private var screenshots: List<ScreenshotData> = Collections.emptyList()

    @JsonProperty("movies")
    private var movies: List<MovieData> = Collections.emptyList()

    @JsonProperty("recommendations")
    private var recommendations: Recomendations? = null

    @JsonProperty("achievements")
    private var achievements: AchievementsList? = null

    @JsonProperty("release_date")
    private var releaseDate: ReleaseDate? = null

    @JsonProperty("support_info")
    private var supportInfo: SupportData? = null

    @JsonProperty("background")
    private var background: String? = null

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