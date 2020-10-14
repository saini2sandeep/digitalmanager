package com.sandeep.digitalmanager.tenantRegistration

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sandeep.digitalmanager.database.DigitalManagerDataBaseDao
import java.lang.IllegalArgumentException


/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 * Provides application (context) and DigitalManagerDataBaseDao to ViewModel.
 *
 * @property application Application
 * @property dataBaseDao DigitalManagerDataBaseDao
 * @constructor
 */
class TenantRegViewModelFactory(
    private val application: Application,
    private val dataBaseDao: DigitalManagerDataBaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TenantRegViewModel::class.java)) {
            return TenantRegViewModel(application, dataBaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}