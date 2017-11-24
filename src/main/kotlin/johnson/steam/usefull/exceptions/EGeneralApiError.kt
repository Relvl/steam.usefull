package johnson.steam.usefull.exceptions

/** @author karpov-em on 24.11.2017*/
enum class EGeneralApiError(private val status: Int, private val message: String) : IApiError {

    GENERAL_SERVER_ERROR(500, "Internal server error"),
    BAD_ARGUMENT(400, "Bad argument");

    override fun getErrorStatus(): Int = this.status
    override fun getErrorType(): String = this.name
    override fun getErrorMessage(): String = this.message
}