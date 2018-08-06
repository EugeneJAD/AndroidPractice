package com.eugene.androidpractice.data.repository

import android.os.SystemClock
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(): Repository {

    override fun getSampleObservable(): Observable<String> {
        return Observable.defer {
            // Do some long running operation
            SystemClock.sleep(2000)
            Observable.just("one", "two", "three", "four", "five")
        }
    }

}