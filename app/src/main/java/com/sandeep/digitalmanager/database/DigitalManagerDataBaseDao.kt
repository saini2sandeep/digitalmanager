package com.sandeep.digitalmanager.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DigitalManagerDataBaseDao {


    @Insert
    fun insertTenantPersonalDetails(tenantDetails: TenantDetails)

//    @Query("SELECT * FROM tenant_details_table WHERE tenant_start_name= :name")
//    fun getTenantDetails(name: String)

    @Query("SELECT * FROM tenant_details_table")
    fun getTenantDetails(): TenantDetails
}