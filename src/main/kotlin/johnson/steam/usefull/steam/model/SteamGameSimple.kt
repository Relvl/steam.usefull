package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * @author Johnson / 24.11.2017 */
class SteamGameSimple {
    @JsonProperty("appid")
    var appId: Int = 0

    @JsonProperty("name")
    var name: String = ""

    class GetAppListResponse {
        @JsonProperty("applist")
        var appList: AppList = AppList()
    }

    class AppList {
        @JsonProperty("apps")
        var apps: List<SteamGameSimple> = Collections.emptyList()
    }
}