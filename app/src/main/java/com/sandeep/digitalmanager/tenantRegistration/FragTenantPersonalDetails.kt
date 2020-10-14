package com.sandeep.digitalmanager.tenantRegistration

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sandeep.digitalmanager.R
import com.sandeep.digitalmanager.base.BaseFragment
import com.sandeep.digitalmanager.database.DigitalManagerDataBase
import com.sandeep.digitalmanager.database.TenantDetails
import com.sandeep.digitalmanager.databinding.FragTenantPersonalDetailBinding
import com.sandeep.digitalmanager.extension.isValidEmail
import com.sandeep.digitalmanager.extension.isValidMobileNumber
import com.sandeep.digitalmanager.extension.isValidPinCode
import com.sandeep.digitalmanager.tenantRegistration.model.TenantOccupation
import com.sandeep.digitalmanager.utils.ViewUtils

/**
 * A simple [Fragment] subclass.
 *
 * A Fragment with TextInputs and Buttons to record the personal details of tenant
 * and later save them in database.
 */
class FragTenantPersonalDetails : BaseFragment() {

    private var binding: FragTenantPersonalDetailBinding? = null
    private var viewModel: TenantRegViewModel? = null


    /**
     * Called when Fragment is ready to display content to the screen.
     * This function use DataBindingUtil to inflate R.frag_tenant_personal_detail.
     *
     * @param inflater LayoutInflater
     * @param container ViewGroup?
     * @param savedInstanceState Bundle?
     * @return View?
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        /**
         * Get a reference to the binding object and inflate fragment view
         */
        binding = DataBindingUtil.inflate(
            inflater, R.layout.frag_tenant_personal_detail, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = DigitalManagerDataBase.getInstance(application).digitalManagerDataBaseDao
        val viewModelFactory = TenantRegViewModelFactory(application, dataSource)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(TenantRegViewModel::class.java)

        binding?.viewModel = viewModel

        /**
         * This is necessory so that binding can listen the LiveData update.
         */
        binding?.lifecycleOwner = this

        binding?.personalDetailBtnNext?.setOnClickListener { view: View ->
            checkEmptyDataAndProcess(view)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelData()
    }

    private fun observeViewModelData() {

        viewModel?.tenantPersonalDetails?.observe(this, Observer { personalDetails ->
            Log.d("Saini", "data = $personalDetails")
        })

    }

    private fun checkEmptyDataAndProcess(view: View) {
        val tenantName = binding?.personalDetailNameEt?.text
        val tenantLastName = binding?.personalDetailLastNameEt?.text
        val tenantFatherName = binding?.personalDetailFatherNameEt?.text
        val tenantMobileNumber = binding?.personalDetailMobileNoEt?.text
        val tenantEmailId = binding?.personalDetailEmailEt?.text
        val tenantPersonalAdd1 = binding?.personalDetailAddLine1Et?.text
        val tenantPersonalAddLandmark = binding?.personalDetailAddLandmarkEt?.text
        val tenantPersonalAddState = binding?.personalDetailAddStateEt?.text
        val tenantPersonalAddDistrict = binding?.personalDetailAddDistrictEt?.text
        val tenantPersonalAddPinCode = binding?.personalDetailAddPinEt?.text
        val tenantPersonalAddLandLineNumber = binding?.personalDetailAddLandlineEt?.text
        val tenantOccupation = binding?.personalDetailProfessionEt?.text

        if (tenantName.isNullOrBlank()) {
            binding?.personalDetailNameEt.showSnackbar("Name couldn't be empty.")
            return
        }

        if (tenantLastName.isNullOrBlank()) {
            binding?.personalDetailLastNameEt.showSnackbar("Last Name couldn't be empty.")
            return
        }

        if (tenantFatherName.isNullOrBlank()) {
            binding?.personalDetailFatherNameEt.showSnackbar("Father Name couldn't be empty.")
            return
        }

        if (tenantMobileNumber.isNullOrBlank()) {
            binding?.personalDetailMobileNoEt.showSnackbar("Mobile number couldn't be empty.")
            return
        }

        if (tenantEmailId.isNullOrBlank()) {
            binding?.personalDetailEmailEt.showSnackbar("Email couldn't be empty.")
            return
        }

        if (tenantPersonalAdd1.isNullOrBlank()) {
            binding?.personalDetailAddLine1Et.showSnackbar("Flat/House number couldn't be empty.")
            return
        }

        if (tenantPersonalAddLandmark.isNullOrBlank()) {
            binding?.personalDetailAddLandmarkEt.showSnackbar("Landmark couldn't be empty.")
            return
        }

        if (tenantPersonalAddState.isNullOrBlank()) {
            binding?.personalDetailAddStateEt.showSnackbar("State couldn't be empty.")
            return
        }

        if (tenantPersonalAddDistrict.isNullOrBlank()) {
            binding?.personalDetailAddDistrictEt.showSnackbar("District couldn't be empty.")
            return
        }

        if (tenantPersonalAddPinCode.isNullOrBlank()) {
            binding?.personalDetailAddPinEt.showSnackbar("PIN code couldn't be empty.")
            return
        }


        /**
         * Data Validations
         */
        if (!tenantMobileNumber.toString().isValidMobileNumber()) {
            binding?.personalDetailMobileNoEt.showSnackbar("Enter correct mobile number.")
            return
        }

        if (!tenantPersonalAddPinCode.toString().isValidPinCode()) {
            binding?.personalDetailAddPinEt.showSnackbar("Enter correct PIN.")
            return
        }

        if (!tenantEmailId.toString().isValidEmail()) {
            binding?.personalDetailEmailEt.showSnackbar("Enter correct email.")
            return
        }


        val tenantDetails = TenantDetails(
            System.currentTimeMillis(), tenantName.toString(),
            tenantLastName.toString(), tenantFatherName.toString(), tenantMobileNumber.toString(),
            tenantEmailId.toString()
        )

//        viewModel?.saveTenantPersonalData(tenantDetails)


//        viewModel?.addDelay(3000)
//        viewModel?.getTenantPersonalDetails()


        /**
         * [NavController] This is an object that keeps track of the current position within the navigation graph.
         * It orchestrates swapping destination content in the NavHostFragment as you move through a navigation graph.
         */

        //Use this code to navigate with out arguments data
//        Navigation.findNavController(view)
//            .navigate(R.id.action_fragTenantPersonalDetails_to_fragTenantOccupationDetails)

        val action =
            FragTenantPersonalDetailsDirections.actionFragTenantPersonalDetailsToFragTenantOccupationDetails(
                if (tenantOccupation.toString().contains("student", ignoreCase = true)) {
                    TenantOccupation.TENANT_OCCUPATION_STUDENT
                } else {
                    TenantOccupation.TENANT_OCCUPATION_WORKING
                }
            )
        Navigation.findNavController(view).navigate(action)
//            findNavController().navigate(action)
    }
}
