package com.anurag.losequarantinefat.ui.aboutus

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anurag.losequarantinefat.R
import com.anurag.losequarantinefat.firebase.testnot
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        aboutViewModel = ViewModelProviders.of(this).get(AboutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        val instagramButton :ImageView = root.findViewById(R.id.instagramButton)
        val linkedinButton :ImageView = root.findViewById(R.id.linkedinButton)
        val UserButton :ImageView = root.findViewById(R.id.UserButton)

        val ruth_IG :ImageView = root.findViewById(R.id.ruth_IG)
        val ruth_IN :ImageView = root.findViewById(R.id.ruth_IN)

        val anandIN :ImageView = root.findViewById(R.id.anandIN)
        val anand_IG :ImageView = root.findViewById(R.id.anand_IG)
        val anand_U :ImageView = root.findViewById(R.id.anand_U)

        val anuragdp :CircleImageView = root.findViewById(R.id.anuragdp)


        var number = 0

        anuragdp.setOnClickListener {
            number += 1
            if (number == 10) {
                Toast.makeText(context, "welcome to admin panel", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, testnot::class.java))
            }
        }

        instagramButton.setOnClickListener {
            val link = "https://www.instagram.com/kanakapalli_anurag_rao/"
            instagram(link)
        }
        linkedinButton.setOnClickListener {
            val link = "https://www.linkedin.com/in/kanakapalli-anurag-b78a16118/"
            linkedin(link)
        }
        UserButton.setOnClickListener {
            user("https://linktr.ee/sulphuricacid")
        }
        ruth_IG.setOnClickListener {
            val link = "https://instagram.com/_shiny_thomas_?igshid=icog9xnabqa8"
            instagram(link)
        }
        ruth_IN.setOnClickListener {
            val link = "https://www.facebook.com/profile.php?id=100010411382949"
            linkedin(link)
        }

        anandIN.setOnClickListener {
            val link = "https://www.linkedin.com/in/ravi-anand-veludandi-36731b109"
            linkedin(link)
        }
        anand_U.setOnClickListener {
            user("https://www.youtube.com/c/RaviAnandVeludandiTM")
        }
        anand_IG.setOnClickListener {
            val link = "https://instagram.com/mr.anandveludandi?igshid=nhghq09xi4ha"
            instagram(link)
        }



//        aboutViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    private fun instagram(link: String) {
        val uri: Uri = Uri.parse(link)
        val likeIng = Intent(Intent.ACTION_VIEW, uri)

        likeIng.setPackage("com.instagram.android")

        try {
            startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(link)
                )
            )
        }
    }

    private fun linkedin(link: String) {
        val uri: Uri = Uri.parse(link)
        val likeIng = Intent(Intent.ACTION_VIEW, uri)

        likeIng.setPackage("com.linkedin.android")

        try {
            startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(link)
                )
            )
        }
    }

    private fun user(link: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://linktr.ee/sulphuricacid")
            )
        )
    }
}