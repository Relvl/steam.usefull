package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Johnson / 24.11.2017 */
class Category {
    @JsonProperty("id")
    private var id: Int = 0

    @JsonProperty("description")
    private var description: String = ""
}