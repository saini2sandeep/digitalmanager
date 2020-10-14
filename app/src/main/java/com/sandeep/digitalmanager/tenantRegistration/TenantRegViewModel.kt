package com.sandeep.digitalmanager.tenantRegistration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sandeep.digitalmanager.database.DigitalManagerDataBaseDao
import com.sandeep.digitalmanager.database.TenantDetails
import kotlinx.coroutines.*

/**
 * [application] to access resources like strings, styles
 * @property dataBaseDao DigitalManagerDataBaseDao
 * @constructor
 */
class TenantRegViewModel(application: Application, val dataBaseDao: DigitalManagerDataBaseDao) :
    AndroidViewModel(application) {

    val name = "Permanent Address"


    /**
     * This ViewModel job allow us to cancel all coroutines started byt this ViewModel when
     * ViewModel is no longer used or destroyed.
     */
    private var viewModelJob = Job()


    /**
     * Need a scope for coroutine to run in. This scope determine what thread the coroutine
     * will run on  and it also need to know about the job.
     *
     * To get a scope we ask for an instance of [CoroutineScope] and pass [Dispatchers] [Job]
     *
     * Here we are using [Dispatchers.Main] It means coroutine launch on UI scope will run on main thread.
     *
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    var tenantPersonalDetails = MutableLiveData<TenantDetails?>()


    fun saveTenantPersonalData(tenantDetails: TenantDetails) {
        uiScope.launch(Dispatchers.IO) {
            dataBaseDao.insertTenantPersonalDetails(tenantDetails)
        }
    }

    fun getTenantPersonalDetails() {
        uiScope.launch {
            val tenantDetails = withContext(Dispatchers.IO) {
                dataBaseDao.getTenantDetails()
            }

            tenantPersonalDetails.value = tenantDetails
        }
    }


    fun addDelay(delayTimeInMillis: Long = 3000) {
        uiScope.launch {
            delay(delayTimeInMillis)
        }
    }

    /**
     * When ViewModel is destroyed [onCleared] is called.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}