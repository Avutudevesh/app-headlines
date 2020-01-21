package com.example.headlines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.headlines.view.HeadlinesListAdapter
import com.example.headlines.viewmodel.NewsHeadlinesViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var headlinesListAdapter: HeadlinesListAdapter
    @Inject
    lateinit var viewModel: NewsHeadlinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        headlines_list.adapter = headlinesListAdapter
        viewModel.state().observe(this, Observer { onStateChanged(it) })
        viewModel.fetchNewsHeadlines()
    }

    private fun onStateChanged(state: NewsHeadlinesViewModel.State) {
        when(state) {
            is NewsHeadlinesViewModel.State.Success -> {
                headlinesListAdapter.submitList(state.result.articles)
            }
            is NewsHeadlinesViewModel.State.Loading -> {

            }
            is NewsHeadlinesViewModel.State.Error -> {

            }
        }
    }
}
