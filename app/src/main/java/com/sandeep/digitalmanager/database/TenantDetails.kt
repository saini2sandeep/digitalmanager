package com.sandeep.digitalmanager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tenant_details_table")
data class TenantDetails(
    @PrimaryKey
    @ColumnInfo(name = "tenant_unique_id")
    var registrationId: Long = 0L,
    @ColumnInfo(name = "tenant_start_name")
    var tenantStartName: String,
    @ColumnInfo(name = "tenant_last_name")
    var tenantLastName: String,
    @ColumnInfo(name = "tenant_father_name")
    var tenantFatherName: String,
    @ColumnInfo(name = "tenant_mobile_number")
    var tenantMobileNumber: String,
    @ColumnInfo(name = "tenant_email")
    var tenantEmail: String
)
