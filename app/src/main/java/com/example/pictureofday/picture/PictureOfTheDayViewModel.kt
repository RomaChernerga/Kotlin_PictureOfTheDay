package com.example.pictureofday.picture

import android.view.ViewTreeObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureofday.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PictureOfTheDayViewModel(
    private val liveDayDataForViewToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()):

    ViewModel() {
         fun getData() : LiveData<PictureOfTheDayData> {
             sendServerRequest()
            return liveDayDataForViewToObserve
        }
        private fun sendServerRequest() {
            liveDayDataForViewToObserve.value = PictureOfTheDayData.Loading(null)

            val apiKey : String = BuildConfig.NASA_API_KEY

            //Проверка на наличие ключа
            if(apiKey.isBlank()) {
                PictureOfTheDayData.Error(Throwable("You need API key"))
            } else {
                retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(object : Callback<PODServerResponseData>{
                    override fun onResponse(
                        call: Call<PODServerResponseData>,
                        response: Response<PODServerResponseData>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            liveDayDataForViewToObserve.value = PictureOfTheDayData.Success(response.body()!!)
                        } else {
                            val message = response.message()

                            if (message.isNotEmpty()) {
                                liveDayDataForViewToObserve.value = PictureOfTheDayData.Error(
                                    Throwable("Undefined Error"))
                            } else {
                                liveDayDataForViewToObserve.value = PictureOfTheDayData.Error(Throwable(message))
                            }
                        }
                    }

                    override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                        liveDayDataForViewToObserve.value = PictureOfTheDayData.Error(t)
                    }
                })
            }
        }

    }