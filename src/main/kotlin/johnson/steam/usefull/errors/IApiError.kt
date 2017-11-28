package johnson.steam.usefull.errors

/** @author karpov-em on 24.11.2017*/
interface IApiError {
    /** */
    fun getErrorStatus(): Int = 500

    /** */
    fun getErrorType(): String

    /** */
    fun getErrorMessage(): String
}