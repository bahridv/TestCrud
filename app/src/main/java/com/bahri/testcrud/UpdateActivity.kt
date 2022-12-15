package com.bahri.testcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bahri.testcrud.databinding.ActivityUpdateBinding
import com.bahri.testcrud.model.ResponseUpdate
import com.bahri.testcrud.network.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    var id : Int = 0
    var title: String? = ""
    var complete: Boolean = true
    var content: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        id = i.getIntExtra("id", 0)
        title = i.getStringExtra("title")
        complete = i.getBooleanExtra("comlete",true)
        content = i.getStringExtra("content")
        binding.etTitleUpdate.setText(title)
        binding.etContentUpdate.setText(content)
        updateTodo()

    }

    private fun updateTodo() {
        binding.btnTodoUpdate.setOnClickListener {
            val job = JsonObject()
            job.addProperty("title", binding.etTitleUpdate.text.toString())
            job.addProperty("complete", true)
            job.addProperty("content", binding.etContentUpdate.text.toString())
            ApiService.endNetwork.updateTodo(id.toString(),job).enqueue(object :Callback<ResponseUpdate>{
                override fun onResponse(
                    call: Call<ResponseUpdate>,
                    response: Response<ResponseUpdate>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@UpdateActivity, "Succes", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@UpdateActivity, "Gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseUpdate>, t: Throwable) {
                    Toast.makeText(this@UpdateActivity, "$t", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }
}