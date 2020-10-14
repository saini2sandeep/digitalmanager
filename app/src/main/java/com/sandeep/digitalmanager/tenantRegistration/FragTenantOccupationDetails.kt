package com.sandeep.digitalmanager.tenantRegistration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.sandeep.digitalmanager.R
import com.sandeep.digitalmanager.base.BaseFragment
import com.sandeep.digitalmanager.database.DigitalManagerDataBase
import com.sandeep.digitalmanager.databinding.FragTenantOccupationDetailsBinding
import com.sandeep.digitalmanager.extension.isValidMobileNumber
import com.sandeep.digitalmanager.tenantRegistration.model.TenantOccupation
import kotlinx.android.synthetic.main.frag_tenant_occupation_details.*
import kotlinx.android.synthetic.main.frag_tenant_occupation_details.view.*

/**
 * A simple [Fragment] subclass.
 */
class FragTenantOccupationDetails : BaseFragment() {

    private lateinit var viewModel: TenantRegViewModel

    private lateinit var tenantOccupation: TenantOccupation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragTenantOccupationDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.frag_tenant_occupation_details, container, false
        )

        val safeArgs: FragTenantOccupationDetailsArgs by navArgs()
        tenantOccupation = safeArgs.tenantOccupation

        Log.d("Saini", "tenant occupation = $tenantOccupation")


        val application = requireNotNull(this.activity).application
        val dataSource = DigitalManagerDataBase.getInstance(application).digitalManagerDataBaseDao
        val viewModelFactory = TenantRegViewModelFactory(application, dataSource)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(TenantRegViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleButtonClicks(view)
        when (tenantOccupation) {
            TenantOccupation.TENANT_OCCUPATION_STUDENT -> {
                inflateUi(true)
            }
            TenantOccupation.TENANT_OCCUPATION_WORKING -> {
                inflateUi(false)
            }
        }
    }

    private fun handleButtonClicks(view: View) {
        view.btn_next.setOnClickListener { view: View ->
            checkEmptyDataAndProcess(view)

//            Navigation.findNavController(view)
//                .navigate(R.id.action_fragTenantOccupationDetails_to_fragTenantRentDetails)
        }
    }

    private fun inflateUi(isStudent: Boolean) {
        occupation_detail_college_name_et.apply {
            hint = if (isStudent) {
                "School/College/Institution name"
            } else {
                "Organization name"
            }
        }

        if (isStudent) {
            // tenant occupation student details ui
            occupation_detail_course_name_et.hint = "Class/Course/Diploma/Degree name"
            occupation_detail_mobile_no_et.hint = "College contact number"
            add_line1_et.hint = "College address"
            add_landmark_et.hint = "Landmark"
            add_state_et.hint = "State"
            add_district_et.hint = "District"
            add_pin_et.hint = "PIN"
        } else {
            // tenant occupation working details ui
            occupation_detail_course_name_et.hint = "Designation/Profile"
            occupation_detail_mobile_no_et.hint = "Organization contact number"
            add_line1_et.hint = "Organization address"
            add_landmark_et.hint = "Landmark"
            add_state_et.hint = "State"
            add_district_et.hint = "District"
            add_pin_et.hint = "PIN"
        }
    }

    private fun checkEmptyDataAndProcess(view: View) {
        val collegeName = occupation_detail_college_name_et.text.toString()
        val courseName = occupation_detail_course_name_et.text.toString()
        val mobileNo = occupation_detail_mobile_no_et.text.toString()

        val addLine1 = add_line1_et.text.toString()
        val addLandmark = add_landmark_et.text.toString()
        val addState = add_state_et.text.toString()
        val addDistrict = add_district_et.text.toString()
        val addPinCode = add_pin_et.text

        if (addLine1.isBlank()) {
            add_line1_et.showSnackbar("Address couldn't be blank")
            return
        }
        if (addLandmark.isBlank()) {
            add_landmark_et.showSnackbar("Landmark couldn't be blank")
            return
        }
        if (addState.isBlank()) {
            add_state_et.showSnackbar("Stage couldn't be blank")
            return
        }
        if (addDistrict.isBlank()) {
            add_district_et.showSnackbar("District couldn't be blank")
            return
        }


        when (tenantOccupation) {
            TenantOccupation.TENANT_OCCUPATION_STUDENT -> {
                if (collegeName.isBlank()) {
                    occupation_detail_college_name_et.showSnackbar("College name couldn't be blank")
                    return
                }

                if (courseName.isBlank()) {
                    occupation_detail_course_name_et.showSnackbar("Course couldn't be blank")
                    return
                }

                if (mobileNo.isBlank()) {
                    occupation_detail_mobile_no_et.showSnackbar("Mobile number couldn't be blank")
                    return
                }

                /**
                 * Data Validations
                 */
                if (!mobileNo.isValidMobileNumber()) {
                    occupation_detail_mobile_no_et.showSnackbar("Enter correct mobile number.")
                    return
                }

                btn_next.showSnackbar("Save student occupation details")
            }

            TenantOccupation.TENANT_OCCUPATION_WORKING -> {
                if (collegeName.isBlank()) {
                    occupation_detail_college_name_et.showSnackbar("Organization name couldn't be blank")
                    return
                }

                if (courseName.isBlank()) {
                    occupation_detail_course_name_et.showSnackbar("Designation/Profile couldn't be blank")
                    return
                }

                if (mobileNo.isBlank()) {
                    occupation_detail_mobile_no_et.showSnackbar("Mobile number couldn't be blank")
                    return
                }

                /**
                 * Data Validations
                 */
                if (!mobileNo.isValidMobileNumber()) {
                    occupation_detail_mobile_no_et.showSnackbar("Enter correct mobile number.")
                    return
                }

                btn_next.showSnackbar("Save working occupation details")
            }
        }

            Navigation.findNavController(view)
                .navigate(R.id.action_fragTenantOccupationDetails_to_fragTenantRentDetails)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save args data here
    }

}
