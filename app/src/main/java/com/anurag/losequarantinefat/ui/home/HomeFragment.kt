package com.anurag.losequarantinefat.ui.home

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.androiddevs.firebasenotifications.NotificationData
import com.androiddevs.firebasenotifications.PushNotification
import com.anurag.losequarantinefat.R
import com.anurag.losequarantinefat.firebase.TOPIC
import com.anurag.losequarantinefat.firebase.sendNotification
import com.anurag.losequarantinefat.firebase.testnot
import com.anurag.losequarantinefat.localstorage.MenuLocal
import com.anurag.losequarantinefat.localstorage.StorageSP
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import org.threeten.bp.LocalDate
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    val TAG = "++++++++++++++++ Home"
    lateinit var mPublisherAdView: PublisherAdView
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "com.example.vicky.notificationexample"
    private val description = "Test notification"

    private var BB: String? = null
    private var LL: String? = null
    private var DD: String? = null
    private var SS: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView home")
        val sharedPreferences = context?.let { StorageSP(it) }
        val menuData = context?.let { MenuLocal(it) }

        Log.d(TAG, "==============$BB , $LL ,$SS ,$DD==============")
        menuData!!.getTodayMenu()[9]?.let { Log.d(TAG, it) }
        if (!sharedPreferences!!.getfalse()) {
            PushNotification(
                NotificationData("Welcome", "if you like this app please gives us 5 star"), TOPIC
            ).also {
                sendNotification(it)
            }
            sharedPreferences.changeTOFalse(true)
        }
        Toast.makeText(context, "===$BB , $LL ,$SS ,$DD===", Toast.LENGTH_SHORT).show()
        val BMI = sharedPreferences!!.getBMI()
        if (BMI.isNullOrEmpty()) Log.d(TAG, "$BMI this is bmi")
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.textView)
        val title: TextView = root.findViewById(R.id.text_home)

        val mAdView = root.findViewById<AdView>(R.id.adView)

        Googleads(mAdView)
//        RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("1018020B301508C2D31969EA9E8738C7")
        var number = 0
        val button2: Button = root.findViewById(R.id.button2)
        val lunch_expand_button: Button = root.findViewById(R.id.lunch_expand_button)
        val evening_expand_button: Button = root.findViewById(R.id.evening_expand_button)
        val dinner_expand_button: Button = root.findViewById(R.id.dinner_expand_button)
        val BMIcard: CardView = root.findViewById(R.id.BMIcard)
        val imageView2: ImageView = root.findViewById(R.id.imageView2)
        val caldetails_tv = root.findViewById<TextView>(R.id.caldetails_tv)

        val Breakfast_img: ImageView = root.findViewById(R.id.Breakfast_img)
        val Lunch_img: ImageView = root.findViewById(R.id.Lunch_img)
        val Snack_img: ImageView = root.findViewById(R.id.Snack_img)
        val Dinner_img: ImageView = root.findViewById(R.id.Dinner_img)

        val Bcal_details: Button = root.findViewById(R.id.Bcal_details)
        val Lcal_details: Button = root.findViewById(R.id.Lcal_details)
        val Scal_details: Button = root.findViewById(R.id.Scal_details)
        val Dcal_details: Button = root.findViewById(R.id.Dcal_details)

        val breakfasr_cal: TextView = root.findViewById(R.id.breakfasr_cal)
        val Lunch_cal: TextView = root.findViewById(R.id.Lunch_cal)
        val Snack_cal: TextView = root.findViewById(R.id.Snack_cal)
        val Dinner_cal: TextView = root.findViewById(R.id.Dinner_cal)



        if (!sharedPreferences.getLoginInfo()[2].toString().isNullOrEmpty()) Picasso.get()
            .load(sharedPreferences.getLoginInfo()[2].toString()).error(R.drawable.spidermanfat)
            .fit().into(imageView2)


        val calendar =
            LocalDate.parse(SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time))
//        if(calendar > menuData!!.getDate()) Log.d(TAG, "0000000000 $calendar > ${menuData!!.getDate()}") else  Log.d(TAG, "gggggggggg $calendar > ${menuData!!.getDate()}")
        if (menuData!!.getDate() == null || calendar > menuData!!.getDate()) {
            Log.d(TAG, "1111111111111111")
            try {
                homeViewModel.Newdata(context)
            } catch (e: Exception) {
                homeViewModel.Newdata(context)
            }
        } else {
            Log.d(TAG, "${calendar.toString()} 222222222222222")
            homeViewModel.applyingData(context)

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
            breakfasr_cal.text = it?.getString("breakfast_calories").toString()
            BB = it?.getString("breakfast_cal_deatails").toString()
            val bf_img = it?.getString("breakfast_photo_url").toString()
            if (!bf_img.isNullOrEmpty()) Picasso.get().load(bf_img).fit().into(Breakfast_img)


            lunch_iteam.text = it?.getString("afternoon_item").toString()
            lunch_detail_tv.text = it?.getString("afternoon_details").toString()
            Lunch_cal.text = it?.getString("afternoon_calories").toString()
            LL = it?.getString("afternoon_cal_details").toString()
            val af_img = it?.getString("afternoon_photo_url").toString()
            if (!af_img.isNullOrEmpty()) Picasso.get().load(af_img).fit().into(Lunch_img)

            evening_item.text = it?.getString("evening_item").toString()
            evening_detail_tv.text = it?.getString("evening_item").toString()
            Snack_cal.text = it?.getString("evening_calories").toString()
            SS = it?.getString("evening_cal_details").toString()
            val sk_img = it?.getString("evening_photo_url").toString()
            if (!sk_img.isNullOrEmpty()) Picasso.get().load(sk_img).fit().into(Snack_img)

            dinner_item.text = it?.getString("dinner_item").toString()
            dinner_detail_tv.text = it?.getString("dinner_details").toString()
            Dinner_cal.text = it?.getString("dinner_calories").toString()
            DD = it?.getString("dinner_cal_details").toString()
            val DR_img = it?.getString("dinner_photo_url").toString()
            if (!DR_img.isNullOrEmpty()) Picasso.get().load(DR_img).fit().into(Dinner_img)
            Log.d(TAG, "==============$BB , $LL ,$SS ,$DD==============")

        })

        homeViewModel.menuLocal.observe(viewLifecycleOwner, Observer {
            one.text = it[0]
            breakfast_matter.text = it[1]
            lunch_iteam.text = it[2]
            lunch_detail_tv.text = it[3]
            evening_item.text = it[4]
            evening_detail_tv.text = it[5]
            dinner_item.text = it[6]
            dinner_detail_tv.text = it[7]

            Picasso.get().load(it[8]).fit().into(Breakfast_img)
            Picasso.get().load(it[9]).fit().into(Lunch_img)
            Picasso.get().load(it[10]).fit().into(Snack_img)
            Picasso.get().load(it[11]).fit().into(Dinner_img)

            breakfasr_cal.text = it[12]
            Lunch_cal.text = it[13]
            Snack_cal.text = it[14]
            Dinner_cal.text = it[15]

            BB = it[16]
            LL = it[17]
            SS = it[18]
            DD = it[19]
            Log.d(TAG, "==============$BB , $LL ,$SS ,$DD==============")
        })

        BMIcard.setOnClickListener {
            homeViewModel.Newdata(context)
            number += 1
            if (number == 10) {
                Toast.makeText(context, "you found a easter egg", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, testnot::class.java))
            }
        }
        imageView2.setOnClickListener {
            Toast.makeText(
                context,
                "with easter egg #2 u can send mgs to all user of app",
                Toast.LENGTH_SHORT
            ).show()
            PushNotification(
                NotificationData(
                    "Easter egg #1",
                    "There are many easter eggs u can noe find #2"
                ), TOPIC
            ).also {
                sendNotification(it)
            }
        }

        Bcal_details.setOnClickListener { context?.let { it1 -> bottom(it1,"$BB")} }
        Lcal_details.setOnClickListener { context?.let { it1 -> bottom(it1,"$LL")} }
        Scal_details.setOnClickListener { context?.let { it1 -> bottom(it1,"$SS")} }
        Dcal_details.setOnClickListener { context?.let { it1 -> bottom(it1,"$DD")} }

        button2.setOnClickListener {
            Log.d(TAG, "button clicked")
            test(breakfast_deatails)
        }
        lunch_expand_button.setOnClickListener {
            Log.d(TAG, "button clicked")
            test(lunchDetails_deatails)
        }
        evening_expand_button.setOnClickListener {
            Log.d(TAG, "button clicked")
            test(eveningDetails_deatails)
        }
        dinner_expand_button.setOnClickListener {
            Log.d(TAG, "button clicked")
            test(dinnerDetails_deatails)
        }
//        on cal deatails

//        onclick image
        Breakfast_img.setOnClickListener { }
        Lunch_img.setOnClickListener { }
        Snack_img.setOnClickListener { }
        Dinner_img.setOnClickListener { }
        return root
    }

    private fun Googleads(adView: AdView) {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        val applicationContext = context
        adView.adListener = object : AdListener() {
            override fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0)
                val toastMessage: String = "ad fail to load"

                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG)
                    .show()
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                val toastMessage: String = "ad loaded"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG)
                    .show()
            }

            override fun onAdOpened() {
                super.onAdOpened()
                val toastMessage: String = "ad is open"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG)
                    .show()
            }

            override fun onAdClicked() {
                super.onAdClicked()
                val toastMessage: String = "ad is clicked"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG)
                    .show()
            }

            override fun onAdClosed() {
                super.onAdClosed()
                val toastMessage: String = "ad is closed"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG)
                    .show()
            }

            override fun onAdImpression() {
                super.onAdImpression()
                val toastMessage: String = "ad impression"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG)
                    .show()
            }

            override fun onAdLeftApplication() {
                super.onAdLeftApplication()
                val toastMessage: String = "ad left application"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        if (adView != null) {
            adView.resume();
        }
    }

    override fun onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }


    private fun test(vieww: LinearLayout?) {
        dinnerDetails_deatails.visibility = View.GONE
        this.eveningDetails_deatails.visibility = View.GONE
        breakfast_deatails.visibility = View.GONE
        lunchDetails_deatails.visibility = View.GONE

        vieww?.visibility = View.VISIBLE

    }

    private fun bottom(context: Context, text: String) {
        Log.d(TAG, "$text is text in bottom")
        val dialog = BottomSheetDialog(context)
        val view = layoutInflater.inflate(R.layout.bottom_layout, null)

        val caldetails_tv = view.findViewById<TextView>(R.id.caldetails_tv)
        caldetails_tv.setText(text)

        dialog.setContentView(view)
        dialog.show()

    }


}


