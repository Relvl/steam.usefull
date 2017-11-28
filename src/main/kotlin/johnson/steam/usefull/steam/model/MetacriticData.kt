package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Johnson / 24.11.2017 */
class MetacriticData {
    @JsonProperty("score")
    private var score: Int = 0

    @JsonProperty("url")
    private var url: String = ""
}