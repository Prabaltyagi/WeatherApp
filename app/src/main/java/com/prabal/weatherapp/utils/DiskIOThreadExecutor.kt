

package com.prabal.weatherapp.utils
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Executor which runs a operation on a new background thread.
 */
class DiskIOThreadExecutor : Executor {

    private val diskIO = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable) { diskIO.execute(command) }
}