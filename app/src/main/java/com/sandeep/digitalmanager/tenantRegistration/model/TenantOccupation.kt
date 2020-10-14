package com.sandeep.digitalmanager.tenantRegistration.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
enum class TenantOccupation {
    @SerializedName("0")
    TENANT_OCCUPATION_STUDENT,
    @SerializedName("1")
    TENANT_OCCUPATION_WORKING
}