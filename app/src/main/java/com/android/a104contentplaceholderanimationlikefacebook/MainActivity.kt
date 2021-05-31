package com.android.a104contentplaceholderanimationlikefacebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.a104contentplaceholderanimationlikefacebook.Adapter.MyAdapter
import com.android.a104contentplaceholderanimationlikefacebook.Model.Item
import com.android.a104contentplaceholderanimationlikefacebook.Retrofit.IMyApi
import com.android.a104contentplaceholderanimationlikefacebook.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myAPI:IMyApi
    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.getInstance
        myAPI = retrofit.create(IMyApi::class.java)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)

        fetchData()
    }

    private fun fetchData() {
        shimmer.startShimmerAnimation()
        compositeDisposable.add(
            myAPI.getData
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    photos->displayData(photos)
                }
        )
    }

    private fun displayData(photos: List<Item>?) {
        val adapter = MyAdapter(this,photos)
        recycler_view.adapter = adapter
        shimmer.stopShimmerAnimation()
        shimmer.visibility = View.GONE
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}