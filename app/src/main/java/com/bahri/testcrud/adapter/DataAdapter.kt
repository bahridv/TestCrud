package com.bahri.testcrud.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bahri.testcrud.R
import com.bahri.testcrud.UpdateActivity
import com.bahri.testcrud.model.DataProduk
import com.bahri.testcrud.model.ResponseDelate
import com.bahri.testcrud.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataAdapter (private var ListData: ArrayList<DataProduk>):
RecyclerView.Adapter<DataAdapter.viewHolder>() {
    inner class viewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val Title : TextView = itemView.findViewById(R.id.tv_title)
        val Content : TextView = itemView.findViewById(R.id.tv_content)
        val delate : ImageView = itemView.findViewById(R.id.iv_delate)
        val update : ImageView = itemView.findViewById(R.id.iv_update)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data = ListData[position]
        holder.Title.text = data.title
        holder.Content.text = data.content

        holder.update.setOnClickListener {
            val i = Intent(holder.itemView.context, UpdateActivity::class.java)
            i.putExtra("id", data.id)
            i.putExtra("title", data.title)
            i.putExtra("complete", data.complete)
            i.putExtra("content", data.content)
            holder.itemView.context.startActivity(i)
        }

        holder.delate.setOnClickListener {
            ApiService.endNetwork.delateTodo("${data.id}").enqueue(object : Callback<ResponseDelate>{
                override fun onResponse(
                    call: Call<ResponseDelate>,
                    response: Response<ResponseDelate>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(holder.itemView.context, "Delate Todo Succes", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(holder.itemView.context, "Gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseDelate>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    override fun getItemCount() = ListData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataProduk>){
        ListData.clear()
        ListData.addAll(data)
        notifyDataSetChanged()
    }
}