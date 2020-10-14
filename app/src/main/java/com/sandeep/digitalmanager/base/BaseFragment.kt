package com.sandeep.digitalmanager.base

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.sandeep.digitalmanager.R

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    fun View?.showSnackbar(
        text: String?, action: String? = null, runnable: Runnable? = null,
        duration: Int = Snackbar.LENGTH_LONG,
        snackbarColor: Int = Color.parseColor("#000000")
    ) {
        this?.let { view ->
            text?.let {
                val snackbar = Snackbar.make(view, it, duration)
                snackbar.view.setBackgroundColor(snackbarColor)
                if (action != null) {
                    snackbar.setActionTextColor(Color.WHITE)
                    snackbar.setAction(action) {
                        this.post(runnable)
                        snackbar.dismiss()
                    }
                }
                snackbar.show()
            }
        }

    }
}