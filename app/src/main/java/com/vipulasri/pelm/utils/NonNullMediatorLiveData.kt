package com.vipulasri.pelm.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

/**
 * <p>
 *   NonNullMediatorLivedata helps us to observe any livedata without worrying about the nullability of it's value
 *   It does this by intercepting a live-data and transforming it's value to non null type.
 *   To use it, just call nonNull() on any live-data and observe from the resulting one
 * </p>
 */
class NonNullMediatorLiveData<T> : MediatorLiveData<T>() {

  fun observe(
    owner: LifecycleOwner,
    observer: (t: T) -> Unit
  ) {

    this.observe(
      owner,
      Observer {
        it?.let(observer)
      }
    )
  }
}

/**
 * <p>
 *   Call this method on a live-data receiver to transform your live-data to one that emits only non-null values.
 * </p>
 * */
fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
  val mediator = NonNullMediatorLiveData<T>()

  mediator.addSource(this) {
    mediator.value = it
  }

  return mediator
}
