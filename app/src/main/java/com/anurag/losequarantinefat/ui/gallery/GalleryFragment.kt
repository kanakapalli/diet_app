package com.anurag.losequarantinefat.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anurag.losequarantinefat.R

class GalleryFragment : Fragment() {

    private lateinit var tipsViewModel: GalleryViewModel


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tipsViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        tipsViewModel.getTips(context)
        var title = listOf<String>()
        var subject = listOf<String>()
        var img_URL = listOf<String>()
        var link = listOf<String>()

        for (i in 0 until tipsViewModel.data.value?.length()!!) {
            title += tipsViewModel.data.value?.getJSONObject(i)?.getString("title").toString()
            subject += tipsViewModel.data.value?.getJSONObject(i)?.getString("subject").toString()
            img_URL += tipsViewModel.data.value?.getJSONObject(i)?.getString("img_URL").toString()
            link += tipsViewModel.data.value?.getJSONObject(i)?.getString("link").toString()
        }

        val RecycleView = root.findViewById(R.id.RecycleView) as RecyclerView
        RecycleView.layoutManager = LinearLayoutManager(this.activity)
        RecycleView.adapter =  MainAdapter(title,subject,img_URL,link)

//        tipsViewModel.data.observe(viewLifecycleOwner, Observer {
////            textView.text = it
//            RecycleView.adapter = MainAdapter(it)
//        })
        return root
    }
}