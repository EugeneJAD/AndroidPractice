package com.eugene.androidpractice.data.repository

import io.reactivex.Observable

interface Repository {

    fun getSampleObservable(): Observable<String>
}