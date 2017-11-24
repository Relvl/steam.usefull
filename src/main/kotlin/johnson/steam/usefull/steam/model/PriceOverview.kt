package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/** @author karpov-em on 24.11.2017*/
class PriceOverview {

    @JsonProperty("currency")
    private var currency: String = "USD"

    @JsonProperty("initial")
    private var initial: Int = 0

    @JsonProperty("final")
    private var final: Int = 0

    @JsonProperty("discount_percent")
    private var discountPercent: Int = 0
}