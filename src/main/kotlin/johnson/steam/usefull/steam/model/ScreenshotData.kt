package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Johnson / 24.11.2017 */
class ScreenshotData {
    @JsonProperty("id")
    private var id: Int = 0

    @JsonProperty("path_thumbnail")
    private var pathTumbnail: String? = null

    @JsonProperty("path_full")
    private var pathFull: String? = null
}