package com.anurag.losequarantinefat.ui.gallery

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anurag.losequarantinefat.R
import com.anurag.losequarantinefat.ui.webview.WebView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adaptor_tips.view.*


class MainAdapter(
    val title: List<String>,
    val subject: List<String>,
    val img_URL: List<String>,
    val link: List<String>
) : RecyclerView.Adapter<CustomViewHolder>() {

//    val x = jsonData.getJSONObject(Random.nextInt(0, 6))


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.adaptor_tips, parent, false)
        return CustomViewHolder(cellForRow)
    }



    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.Title.text = title.get(position).toString()
        holder.view.Subject.text = subject.get(position).toString()
        val DisplayAdd = holder.view.DisplayAdd
        Picasso.get().load(img_URL.get(position)).fit().into(DisplayAdd)

            holder.Link = link.get(position)
            holder.title = title.get(position)

    }



}



class CustomViewHolder(val view: View, var Link:String? = null, var title:String?=null): RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                val intent =Intent(view.context , WebView::class.java)
                intent.putExtra("Link",Link)
                intent.putExtra("title",title)
                view.context.startActivity(intent)
            }
        }
}
