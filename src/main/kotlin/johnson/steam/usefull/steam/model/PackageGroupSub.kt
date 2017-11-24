package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty

/** @author karpov-em on 24.11.2017*/
class PackageGroupSub {

    @JsonProperty("packageid")
    private var packageId: Int = 0

    @JsonProperty("percent_savings_text")
    private var percentSavingText: String = ""

    @JsonProperty("percent_savings")
    private var percentSavings: String = ""

    @JsonProperty("option_text")
    private var optionText: String = ""

    @JsonProperty("option_description")
    private var optionDescription: String = ""

    @JsonProperty("can_get_free_license")
    private var canGetFreeLicense: Int = 0

    @JsonProperty("is_free_license")
    private var isFreeLicense: Boolean = false

    @JsonProperty("price_in_cents_with_discount")
    private var priceInCentsWithDiscount: Int = 0
}