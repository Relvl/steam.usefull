package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Johnson / 24.11.2017 */
class PlatformsList {
    @JsonProperty("windows")
    private var windows: Boolean = false

    @JsonProperty("mac")
    private var mac: Boolean = false

    @JsonProperty("linux")
    private var linux: Boolean = false
}