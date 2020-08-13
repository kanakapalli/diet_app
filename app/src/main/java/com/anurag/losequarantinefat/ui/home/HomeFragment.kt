package com.anurag.losequarantinefat.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anurag.losequarantinefat.R
import com.anurag.losequarantinefat.localstorage.MenuLocal
import com.anurag.losequarantinefat.localstorage.StorageSP
import kotlinx.android.synthetic.main.fragment_home.*
import java.time.Instant.now
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    val TAG = "++++++++++++++++ Home"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView home")
        val sharedPreferences = context?.let { StorageSP(it) }
        val menuData = context?.let { MenuLocal(it) }
        if (menuData?.getTodayMenu() != null) {
            for (x in menuData.getTodayMenu().iterator())
                Log.d(TAG, "$menuData this is menuData")
        }


        val BMI = sharedPreferences!!.getBMI()
        if (BMI.isNullOrEmpty()) Log.d(TAG, "$BMI this is bmi")
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.textView)
        val title: TextView = root.findViewById(R.id.text_home)


        val button2: Button = root.findViewById(R.id.button2)
        val lunch_expand_button: Button = root.findViewById(R.id.lunch_expand_button)
        val evening_expand_button: Button = root.findViewById(R.id.evening_expand_button)
        val dinner_expand_button: Button = root.findViewById(R.id.dinner_expand_button)
        val BMIcard: CardView = root.findViewById(R.id.BMIcard)
        val imageView2: ImageView = root.findViewById(R.id.imageView2)




        if (menuData!!.getDate() == null) {
            homeViewModel.funnction(context)
        }else{
            Log.d(TAG,"else")
        }

        homeViewModel.BMIget(context)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        homeViewModel.title_text.observe(viewLifecycleOwner, Observer {
            title.text = it
        })

        homeViewModel.BMIsavedL.observe(viewLifecycleOwner, Observer {

            BMI_tv.text = "Your BMI is $it"
        })


        homeViewModel.breakfast_dataL.observe(viewLifecycleOwner, Observer {
            one.text = it?.getString("breakfast_item").toString()
            breakfast_matter.text = it?.getString("breakfast_details").toString()

            lunch_iteam.text = it?.getString("afternoon_item").toString()
            lunch_detail_tv.text = it?.getString("afternoon_details").toString()

            evening_item.text = it?.getString("evening_item").toString()
            evening_detail_tv.text = it?.getString("evening_item").toString()

            dinner_item.text = it?.getString("evening_item").toString()
            dinner_detail_tv.text = it?.getString("dinner_details").toString()
        })

        BMIcard.setOnClickListener {
            homeViewModel.BMIget(context)
        }
        imageView2.setOnClickListener {
            homeViewModel.funnction2(context)
        }

        button2?.setOnClickListener {
            Log.d(TAG, "button clicked")
            test(breakfast_deatails)
        }
        lunch_expand_button?.setOnClickListener {
            Log.d(TAG, "button clicked")
            test(lunchDetails_deatails)
        }
        evening_expand_button?.setOnClickListener {
            Log.d(TAG, "button clicked")
            test(eveningDetails_deatails)
        }
        dinner_expand_button?.setOnClickListener {
            Log.d(TAG, "button clicked")
            test(dinnerDetails_deatails)
        }


        return root
    }

    private fun test(vieww: LinearLayout?) {
        dinnerDetails_deatails.visibility = View.GONE
        this.eveningDetails_deatails.visibility = View.GONE
        breakfast_deatails.visibility = View.GONE
        lunchDetails_deatails.visibility = View.GONE

        vieww?.visibility = View.VISIBLE

    }


}