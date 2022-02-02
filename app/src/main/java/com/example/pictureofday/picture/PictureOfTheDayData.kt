package com.example.pictureofday.picture

sealed class PictureOfTheDayData {
        // 3 состояния картинки - Грузится, Готово и Ошибка
    data class Success(val serverResponseData: PODServerResponseData) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()

}