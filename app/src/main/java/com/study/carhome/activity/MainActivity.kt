package com.study.carhome.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.study.carhome.adapter.CarBrandAdapter
import com.study.carhome.databinding.ActivityMainBinding
import com.study.carhome.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mViewModel: MainViewModel by viewModels()

    private val mCarBrandAdapter by lazy {
        CarBrandAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.recyclerView.adapter=mCarBrandAdapter
        mViewModel.data.observe(this){
            mCarBrandAdapter.submitData(lifecycle,it)
        }
    }
}