package com.example.githubrepos.core

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer<T> { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun observeForever(observer: Observer<in T>) {
        super.observeForever { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    @Synchronized
    override fun setValue(
        @Nullable
        t: T?
    ) {
        mPending.set(true)
        super.setValue(t)
    }

    @Synchronized
    override fun postValue(value: T) {
        mPending.set(true)
        super.postValue(value)
    }
}
