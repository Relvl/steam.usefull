package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Johnson / 24.11.2017 */
class SupportData {
    @JsonProperty("url")
    private var url: String? = null

    @JsonProperty("email")
    private var email: String? = null
}