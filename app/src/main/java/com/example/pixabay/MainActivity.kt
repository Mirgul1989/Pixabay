package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import com.example.pixabay.adapter.PixaAdapter
import com.example.pixabay.databinding.ActivityMainBinding
import com.example.pixabay.model.PixaModel
import com.example.pixabay.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var adapter = PixaAdapter()
    var page = 1

    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
    }

    private fun initClicker() {
        with(binding) {

            searchBtn.setOnClickListener {
                page = 1
                doRequest()
            }
            scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val new=v as NestedScrollView
                if (scrollY==new.getChildAt(0).measuredHeight-v.measuredHeight){
                    page++
                    progressBar.isVisible=true
                    doRequest()
                }
            }

        }

    }

    private fun ActivityMainBinding.doRequest() {
        RetrofitService().api.searchImage(keyWord = nameEd.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.hits?.let {
                            adapter.addList(it)
                            binding.progressBar.isVisible=false
                        }

                        Log.e("ololo", "onResponse: ${response.body()?.hits?.get(0)?.largeImageURL} ")
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure:${t.message} ")
                }
            })
recyclerView.adapter=adapter
    }
}