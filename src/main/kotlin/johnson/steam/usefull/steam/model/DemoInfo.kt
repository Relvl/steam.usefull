package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Johnson / 25.11.2017 */
class DemoInfo {

    @JsonProperty("appid")
    private var appId: Int = 0

    @JsonProperty("description")
    private var description: String? = null
}