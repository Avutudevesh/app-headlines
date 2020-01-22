package com.example.headlines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.headlines.view.HeadlinesListFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), HeadlinesListFragment.OnHeadlineSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        setUpActionBar()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, HeadlinesListFragment()).commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is HeadlinesListFragment) {
            fragment.setOnHeadlineSelectedListener(this)
        }
    }

    private fun setUpActionBar() {
        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.actionbar)
        }
    }

    override fun onArticleSelected(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
