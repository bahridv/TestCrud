package com.bahri.testcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bahri.testcrud.databinding.ActivityAddBinding
import com.bahri.testcrud.model.ResponseAdd
import com.bahri.testcrud.network.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        addTODO()
//        addFormatData()
    }

//    @Field
//    private fun addFormatData() {
//        ApiService.endNetwork.AddTODOFORMDATA(
//            binding.etTitle.text.toString(),
//            true,
//            binding.etContent.text.toString()
//        ).enqueue(object : Callback<ResponseAdd>{
//            override fun onResponse(call: Call<ResponseAdd>, response: Response<ResponseAdd>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<ResponseAdd>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }

    // @Body
    private fun addTODO() {
        binding.btnTodo.setOnClickListener {
            var job = JsonObject()
            job.addProperty("title", binding.etTitle.text.toString())
            job.addProperty("complete", true)
            job.addProperty("content", binding.etContent.text.toString())
            Log.d("JSON", "$job")
            if (binding.etTitle.text.isEmpty() || binding.etContent.text.isEmpty()){
                Toast.makeText(this, "Masukkan data terlebih dahulu", Toast.LENGTH_SHORT).show()
            }else{
                ApiService.endNetwork.addTodo(job).enqueue(object : Callback<ResponseAdd> {
                    override fun onResponse(
                        call: Call<ResponseAdd>,
                        response: Response<ResponseAdd>
                    ) {
                        if (response.isSuccessful) {
                            finish()
                            Toast.makeText(this@AddActivity, "Add Todo Succes", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(this@AddActivity, "Gagal Add Todo", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseAdd>, t: Throwable) {
                        Toast.makeText(this@AddActivity, "$t", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}