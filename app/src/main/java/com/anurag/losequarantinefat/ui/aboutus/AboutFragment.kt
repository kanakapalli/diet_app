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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anurag.losequarantinefat.R


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

        instagramButton.setOnClickListener {
            val link = "https://www.instagram.com/kanakapalli_anurag_rao/"
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

        linkedinButton.setOnClickListener {
            val link = "https://www.linkedin.com/in/kanakapalli-anurag-b78a16118/"
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

        UserButton.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://linktr.ee/sulphuricacid")
                )
            )
        }



//        aboutViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}