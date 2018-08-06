package com.eugene.androidpractice.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.eugene.androidpractice.data.model.Resource

fun <T1, T2> zip(src1: LiveData<Resource<T1>>, src2: LiveData<Resource<T2>>):
        LiveData<Resource<Pair<Resource<T1>?, Resource<T2>?>>> {

    return MediatorLiveData<Resource<Pair<Resource<T1>?, Resource<T2>?>>>().apply {
        value = Resource.loading(null)
        var src1Version = 0
        var src2Version = 0
        var lastSrc1: Resource<T1>? = null
        var lastSrc2: Resource<T2>? = null

        fun update() {
            if (src1Version > 0 && src2Version > 0){
                if(lastSrc1 != null && lastSrc2 != null) {
                    value = Resource.success(Pair(lastSrc1, lastSrc2))
                    src1Version = 0
                    src2Version = 0
                } else {
                    value = Resource.error("", Pair(lastSrc1, lastSrc2))
                }
            }
        }

        addSource(src1) {
            lastSrc1 = it
            src1Version++
            update()
        }

        addSource(src2) {
            lastSrc2 = it
            src2Version++
            update()
        }
    }
}

fun <T1, T2, R> zip2Sources(src1: LiveData<Resource<T1>>, src2: LiveData<Resource<T2>>, zipper: (Resource<T1>, Resource<T2>) -> R):
        LiveData<Resource<R>> {

    return MediatorLiveData<Resource<R>>().apply {
        value = Resource.loading(null)
        var src1Version = 0
        var src2Version = 0
        var lastSrc1: Resource<T1>? = null
        var lastSrc2: Resource<T2>? = null

        fun update() {
            if (src1Version > 0 && src2Version > 0){
                if(lastSrc1 != null && lastSrc2 != null) {
                    value = Resource.success(zipper(lastSrc1!!, lastSrc2!!))
                    src1Version = 0
                    src2Version = 0
                } else {
                    value = Resource.error("", zipper(lastSrc1!!, lastSrc2!!))
                }
            }
        }

        addSource(src1) {
            lastSrc1 = it
            src1Version++
            update()
        }

        addSource(src2) {
            lastSrc2 = it
            src2Version++
            update()
        }
    }
}