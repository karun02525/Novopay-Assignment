package com.novopay.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.novopay.R
import com.novopay.adapter.PlansPagerAdapter
import com.novopay.model.Sources
import com.novopay.mvvm.SourceListViewModel
import com.novopay.utils.showSnack
import kotlinx.android.synthetic.main.activity_main_tab_layout.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import javax.xml.transform.Source


class MainTabsActivity : AppCompatActivity() {

    private val _viewModel: SourceListViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab_layout);

        setupViewModel()
    }


    private fun setupViewModel(){
        progress_circular.visibility=View.VISIBLE
        _viewModel.getSourceData().observe(this, Observer {
            if(it!=null) {
                progress_circular.visibility=View.GONE
                bindTabs(it as MutableList<Sources>)
            }
        })
        _viewModel.getErrorMessage().observe(this, Observer {
            progress_circular.visibility=View.GONE
            showSnack(it)
        })
    }


    private  fun bindTabs(mutableList: MutableList<Sources>) {
        for((count, item) in mutableList.withIndex()){
                if(count<10) {
                    tabs.addTab(tabs.newTab().setText(item.name?:"--"))
                }
        }

        val adapter = PlansPagerAdapter(supportFragmentManager, tabs.tabCount, mutableList as ArrayList<Sources>)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabs))
        if (tabs.tabCount === 1) {
            tabs.tabMode = TabLayout.MODE_FIXED
        } else {
            tabs.tabMode = TabLayout.MODE_SCROLLABLE
        }
    }
}


class DynamicFragment : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val  view = inflater.inflate(R.layout.fragment_list, container, false)
        val cnt=view.context
        val item = arguments!!.getParcelable<Sources>("item")
        view.apply {
            tv_name.text = item?.name
            tv_description.text = item?.description
            tv_language.text = item?.language
            tv_country.text =item?.country
            tv_url.text = item?.url

            tv_url.setOnClickListener {
                startActivity(Intent(cnt,WebViewActivity::class.java).putExtra("url",item?.url))
            }


        }.cardView.setOnClickListener {
            startActivity(Intent(cnt,NewsListActivity::class.java).putExtra("country",item?.country))
        }

        return view
    }

    companion object {
        fun newInstance(item: Sources): DynamicFragment {
            val fragment = DynamicFragment()
            val args = Bundle()
            args.putParcelable("item",item)
            fragment.arguments = args
            return fragment
        }
    }




}