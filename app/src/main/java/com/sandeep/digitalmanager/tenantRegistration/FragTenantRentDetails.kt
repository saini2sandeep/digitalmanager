package com.sandeep.digitalmanager.tenantRegistration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sandeep.digitalmanager.R
import com.sandeep.digitalmanager.base.BaseFragment
import com.sandeep.digitalmanager.databinding.FragTenantRentDetailsBinding
import kotlinx.android.synthetic.main.frag_tenant_rent_details.*

/**
 * A simple [Fragment] subclass.
 */
class FragTenantRentDetails : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragTenantRentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.frag_tenant_rent_details, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkEmptyDataAndProcess()
    }


    private fun checkEmptyDataAndProcess() {
        val roomNumber = rent_detail_room_no_et.text.toString()
        val bedNumber = rent_detail_bed_no_et.text.toString()
        val monthlyRent = rent_detail_monthly_rent_et.text.toString()
        val advanceRent = rent_detail_rent_advance_et.text.toString()
        val admissionDate = rent_detail_joining_date_et.text.toString()

        if (roomNumber.isBlank()) {
            rent_detail_room_no_et.showSnackbar("Room number couldn't be blank")
            return
        }

        if (bedNumber.isBlank()) {
            rent_detail_bed_no_et.showSnackbar("Bed number couldn't be blank")
            return
        }

        if (monthlyRent.isBlank()) {
            rent_detail_monthly_rent_et.showSnackbar("Monthly rent couldn't be blank")
            return
        }

        if (advanceRent.isBlank()) {
            rent_detail_rent_advance_et.showSnackbar("Advance rent couldn't be blank")
            return
        }

        if (admissionDate.isBlank()) {
            rent_detail_joining_date_et.showSnackbar("Date couldn't be blank")
            return
        }


    }
}
