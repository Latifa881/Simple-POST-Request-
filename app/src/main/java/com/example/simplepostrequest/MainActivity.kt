package com.example.simplepostrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tvNames: TextView
    lateinit var etName: EditText
    lateinit var btGetAlData: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        btGetAlData = findViewById(R.id.btGetAlData)
        tvNames = findViewById(R.id.tvNames)

        btGetAlData.setOnClickListener { getAllNames() }


        val btAdd = findViewById<Button>(R.id.btAdd)
        btAdd.setOnClickListener {
            if (etName.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter a name", Toast.LENGTH_SHORT).show()
            } else {
                val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
                if (apiInterface != null) {
                    val text = etName.text.toString()
                    apiInterface.addNames(NamesItem(text,0))
                        //apiInterface.addNames(etName.text.toString())
                        .enqueue(object : Callback<NamesItem> {
                            override fun onResponse(
                                call: Call<NamesItem>,
                                response: Response<NamesItem>
                            ) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "${etName.text} added successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                etName.text.clear()
                            }

                            override fun onFailure(call: Call<NamesItem>, t: Throwable) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "failed to add",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.e("Failed oops", t.toString())

                                call.cancel()
                            }
                        })

                }
            }
        }


    }

    fun getAllNames() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        var text = ""
        if (apiInterface != null) {

            apiInterface.getNames().enqueue(object : Callback<NamesX> {
                override fun onResponse(
                    call: Call<NamesX>,
                    response: Response<NamesX>
                ) {

                    Log.d("TAG", response.code().toString() + "")
                    for (data in response.body()!!) {
                        text += data.name + "\n"
                    }
                    tvNames.text = text


                }

                override fun onFailure(call: Call<NamesX>, t: Throwable) {
                    call.cancel()
                }
            })
        }
    }
}