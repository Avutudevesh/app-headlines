package com.example.headlines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.headlines.R
import com.example.headlines.network.Article
import com.example.headlines.viewmodel.NewsHeadlinesViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.headlines_list_fragment.*
import javax.inject.Inject

class HeadlinesListFragment : Fragment() {

    @Inject
    lateinit var headlinesListAdapter: HeadlinesListAdapter
    @Inject
    lateinit var viewModel: NewsHeadlinesViewModel

    private lateinit var callback: OnHeadlineSelectedListener

    fun setOnHeadlineSelectedListener(callback: OnHeadlineSelectedListener) {
        this.callback = callback
    }

    interface OnHeadlineSelectedListener {
        fun onArticleSelected(article: Article)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        setUpViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.headlines_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headlines_list.adapter = headlinesListAdapter
    }

    private fun setUpViewModel() {
        viewModel.state().observe(this, Observer { onStateChanged(it) })
        viewModel.fetchNewsHeadlines()
        viewModel.clickedArticleLiveData.observe(this, Observer { onArticleClicked(it) })
    }

    private fun onArticleClicked(article: Article) {
        callback.onArticleSelected(article)
    }

    private fun onStateChanged(state: NewsHeadlinesViewModel.State) {
        when (state) {
            is NewsHeadlinesViewModel.State.Success -> {
                headlinesListAdapter.submitList(state.result.articles)
            }
            is NewsHeadlinesViewModel.State.Loading -> {

            }
            is NewsHeadlinesViewModel.State.Error -> {

            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}
