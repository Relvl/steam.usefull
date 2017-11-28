package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Johnson / 24.11.2017 */
class ReleaseDate {
    @JsonProperty("coming_soon")
    private var comingSoon: Boolean = false

    @JsonProperty("date")
    private var date: String? = null
}