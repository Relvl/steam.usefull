package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * @author Johnson / 24.11.2017 */
class AchievementsList {
    @JsonProperty("total")
    private var total: Int = 0

    @JsonProperty("highlighted")
    private var highlighted: List<AchievementData> = Collections.emptyList()

    class AchievementData{
        @JsonProperty("name")
        private var name: String = ""

        @JsonProperty("path")
        private var path: String? = null
    }
}