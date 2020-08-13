package com.anurag.losequarantinefat

import android.content.DialogInterface
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.TaskStackBuilder
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.anurag.losequarantinefat.localstorage.StorageSP
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    val TAG = "MMMMMMMMMM Mainactivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val save = StorageSP(this)
        var x = save.getDisplayname()
//        Log.d(TAG, x[0] as String)
//        val DisplayName_tv : TextView = findViewById(R.id.DisplayName_tv)
//        DisplayName_tv.text = x[0]?.let{it as String}


//        val bundle = intent.extras
//        val displayName : String?= bundle!!.getString("displayName")
//        val photoUrl : String?= bundle.getString("photoUrl")
//        val email : String?= bundle.getString("email")
//
//        Log.i(TAG, email.toString())
//        Log.i(TAG, photoUrl.toString())
//        Log.i(TAG, displayName.toString())




//        val DisplayPic : ImageView = findViewById(R.id.DIsplayPic)
//        val link = photoUrl.toString()
//        Picasso.get().load("https://image.shutterstock.com/image-photo/beautiful-water-drop-on-dandelion-260nw-789676552.jpg").into(DIsplayPic)

//        EmailAdders_tv.text = email.toString()




        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_tips, R.id.nav_Workout,R.id.nav_Aboutus), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val headerView = navView.getHeaderView(0)
        val EmailAdders_tv = headerView.findViewById(R.id.EmailAdders_tv) as TextView
        val DisplayName_tv = headerView.findViewById(R.id.DisplayName_tv) as TextView
        val DisplayPic = headerView.findViewById(R.id.DIsplayPic) as ImageView
        EmailAdders_tv.text = "${x[1]}"
        DisplayName_tv.text = "${x[0]}"
//      Picasso.get().load("https://image.shutterstock.com/image-photo/beautiful-water-drop-on-dandelion-260nw-789676552.jpg").into(DIsplayPic)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }



    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure!")
        builder.setMessage("Do you want to close the app?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            finish()
        }
        builder.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }
}