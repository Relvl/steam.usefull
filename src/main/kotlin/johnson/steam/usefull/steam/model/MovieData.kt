package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Johnson / 24.11.2017 */
class MovieData {
    @JsonProperty("id")
    private var id: Int = 0

    @JsonProperty("name")
    private var name: String = ""

    @JsonProperty("thumbnail")
    private var thumbnail: String? = null

    @JsonProperty("webm")
    private var webm: Webm? = null

    @JsonProperty("highlight")
    private var highlight: Boolean = false

    class Webm{
        @JsonProperty("480")
        private var size480: String? = null
        @JsonProperty("max")
        private var sizeMax: String? = null
    }
}