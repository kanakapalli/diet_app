package com.anurag.losequarantinefat.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anurag.losequarantinefat.R
import kotlinx.android.synthetic.main.adaptor_tips.view.*

class MainAdapter(val title: List<String>, val subject: List<String>) : RecyclerView.Adapter<CustomViewHolder>() {

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

    }

}



class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}
