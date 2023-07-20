package com.example.martyrs.common

import com.example.martyrs.R
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber

class MartyrExceptionMapper {

    companion object {
        fun map(throwable: Throwable): MartyrException {
            if (throwable is HttpException) {
                try {
                    val jsonObject = JSONObject(throwable.response()?.errorBody()!!.string())
                    val isSuccess = jsonObject.getBoolean("success")
                    if (!isSuccess) {
                        val validationsObject = jsonObject.getJSONObject("validations")
                        for (fieldName in validationsObject.keys()) {
                            val errorsArray = validationsObject.getJSONArray(fieldName)
                            if (errorsArray.length() > 0) {
                                for (errorIndex in 0 until errorsArray.length()) {
                                    val errorMessage = errorsArray.getString(errorIndex)
                                    return MartyrException(errorMessage)
                                }
                            }
                        }
                    } else {
                        val result = jsonObject.getString("result")
                        return MartyrException(result)
                    }
                } catch (exception: Exception) {
                    Timber.e(exception)
                    return MartyrException(null, R.string.errorJsonException)
                } catch (t: Throwable) {
                    return MartyrException(null, R.string.errorThrowable)
                    Timber.e(t)
                }
            }
            return MartyrException(null, R.string.unknownError)
        }
    }


}