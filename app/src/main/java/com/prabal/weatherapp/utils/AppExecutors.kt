

package com.prabal.weatherapp.utils
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

const val THREAD_COUNT = 3
/**
 * Executor which runs a operation on a new background thread.
 */
open class AppExecutors constructor(
    val networkIO: Executor = Executors.newFixedThreadPool(THREAD_COUNT),
    val mainThread: Executor = MainThreadExecutor(),
    val diskIO: Executor = DiskIOThreadExecutor()
) {
    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}