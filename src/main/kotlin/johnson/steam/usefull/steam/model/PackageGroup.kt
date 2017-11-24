package johnson.steam.usefull.steam.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/** @author karpov-em on 24.11.2017*/
class PackageGroup {

    @JsonProperty("name")
    private var name: String = ""

    @JsonProperty("title")
    private var title: String? = null

    @JsonProperty("description")
    private var description: String = ""

    @JsonProperty("selection_text")
    private var selectionText: String = ""

    @JsonProperty("save_text")
    private var saveText: String = ""

    @JsonProperty("display_type")
    private var displayType: Int = 0

    @JsonProperty("is_recurring_subscription")
    private var isRecurringSubscription: Boolean = false

    @JsonProperty("subs")
    private var subs: List<PackageGroupSub> = Collections.emptyList()
}