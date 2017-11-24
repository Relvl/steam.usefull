package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/** @author karpov-em on 24.11.2017*/
class Requirements {
    @JsonProperty("minimum")
    private var minimum: String? = null

    @JsonProperty("recommended")
    private var recommended: String? = null
}