package johnson.steam.usefull.exceptions

import com.fasterxml.jackson.annotation.JsonProperty
import johnson.steam.usefull.api.IResponse

/** @author karpov-em on 24.11.2017*/
class ApiException(private val error: IApiError) : IResponse, Exception() {
    @JsonProperty
    private val isError: Boolean = true;
    @JsonProperty
    private val errorType: String = error.getErrorType()
    @JsonProperty
    private val errorMessage: String = error.getErrorMessage()

    fun getStatus(): Int = error.getErrorStatus()
    fun getApiError(): IApiError = error
}