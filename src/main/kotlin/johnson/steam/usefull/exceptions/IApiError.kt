package johnson.steam.usefull.exceptions

/** @author karpov-em on 24.11.2017*/
interface IApiError {
    /** */
    fun getErrorStatus(): Int = 500

    /** */
    fun getErrorType(): String

    /** */
    fun getErrorMessage(): String
}