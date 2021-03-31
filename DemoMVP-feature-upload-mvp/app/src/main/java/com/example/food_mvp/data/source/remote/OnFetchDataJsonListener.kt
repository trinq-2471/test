package com.example.food_mvp.data.source.remote

import java.lang.Exception

interface OnFetchDataJsonListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception?)
}
