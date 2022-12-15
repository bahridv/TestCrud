package com.bahri.testcrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bahri.testcrud.adapter.DataAdapter
import com.bahri.testcrud.databinding.ActivityMainBinding
import com.bahri.testcrud.model.DataProduk
import com.bahri.testcrud.model.ResponseList
import com.bahri.testcrud.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val dataTest = DataAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.fbAddTodo.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        rv_item.layoutManager = LinearLayoutManager(this)
        rv_item.adapter = dataTest
        binding.sfContent.setOnRefreshListener {
            getData()
        }
        getData()
    }

    override fun onStart(){
        super.onStart()
        getData()
    }

    private fun getData() {
        binding.sfContent.isRefreshing = false
        ApiService.endNetwork.getList().enqueue(object  : Callback<ResponseList>{
            override fun onResponse(call: Call<ResponseList>, response: Response<ResponseList>) {
                if (response.isSuccessful){
                    val responList: ResponseList? = response.body()
                    onResultData(responList!!)
                }else{
                    Toast.makeText(this@MainActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseList>, t: Throwable) {
                Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onResultData(responList: ResponseList?) {
        val test = responList?.data
        dataTest.setData(test as List<DataProduk>)

    }
}