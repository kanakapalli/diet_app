package com.anurag.losequarantinefat.ui.bmi

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anurag.losequarantinefat.R
import com.anurag.losequarantinefat.common.Common
import com.anurag.losequarantinefat.localstorage.StorageSP
import com.google.android.gms.ads.AdView

class SlideshowFragment : Fragment() {

    private lateinit var bmiViewModel: BmiViewModel
    val TAG = "^^^^^^^^^^ BMI"

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        bmiViewModel =  ViewModelProviders.of(this).get(BmiViewModel::class.java)
        val sharedPreferences = context?.let { StorageSP(it) }
        val root = inflater.inflate(R.layout.fragment_bmi, container, false)

        val height: EditText = root.findViewById(R.id.height)
        val weight: EditText = root.findViewById(R.id.weight)

        val result : TextView = root.findViewById(R.id.result)

        val display : TextView = root.findViewById(R.id.Display)

        val button : Button = root.findViewById(R.id.button)
        val mAdView = root.findViewById<AdView>(R.id.adView)

        Common(requireContext()).Googleads(mAdView)
        button.setOnClickListener {
            Log.d(TAG,height.text.toString())
            Log.d(TAG,weight.text.toString())

            val meter = height.text.toString().toFloat() * 0.3048
            Log.d(TAG,meter.toString())

            val BMI = (weight.text.toString().toInt() /(meter * meter)).toInt()
            sharedPreferences!!.saveBMI(BMI.toString())
            Log.d(TAG,BMI.toString())
            result.text = BMI.toString()

            if (BMI in 18..25) {
                Log.d(TAG,"perfect")
                display.setTextColor("#9cde45".toColor())
                display.text = "Your BMl is perfect"
            }
                if (BMI in 0..17) {
                    Log.d(TAG, "underweight")
                    display.setTextColor("#a85943".toColor())
                    display.text = "Your underweight"
                }
                if (BMI in 26..100) {
                    Log.d(TAG, "overweight")
                    display.setTextColor("#a85943".toColor())
                    display.text = "Your overweight"
                }
            }



        bmiViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        return root
    }

    private fun Googleads(mAdView: AdView?) {

    }
}

private fun String.toColor(): Int = Color.parseColor(this)
