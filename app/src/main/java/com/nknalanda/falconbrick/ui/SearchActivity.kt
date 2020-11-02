package com.nknalanda.falconbrick.ui

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.nknalanda.falconbrick.R
import com.nknalanda.falconbrick.adapter.MyViewPagerAdapter
import com.nknalanda.falconbrick.adapter.SearchTermAdapter
import com.nknalanda.falconbrick.databinding.ActivitySearchBinding
import com.nknalanda.falconbrick.utils.Constant
import com.nknalanda.falconbrick.viewmodel.SearchViewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess


class SearchActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener{
    private var binding: ActivitySearchBinding? = null
    private var viewModel: SearchViewModel? = null
    private var searchTermAdapter: SearchTermAdapter? = null
    private var searchTermList = ArrayList<String>()
    private lateinit var listener: SearchTermAdapter.IOnItemClickListener
    private lateinit var pageChangeListener: ViewPager.OnPageChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding?.vm = viewModel
        binding?.lifecycleOwner = this
        binding?.executePendingBindings()
        initViews()
        searchTermAdapter
        setSearchTermAdapter()
        highlightText(
            resources.getString
                (R.string.type_to_start_searching_for), "Units, Activity, Status"
        )
        initViewPager()
    }

    private fun initViews() {
        supportActionBar?.title = "Search"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.svItem?.setOnQueryTextListener(this)

        binding?.svItem?.setOnCloseListener {
            searchTermList.clear()
            searchTermAdapter?.updateTermList(searchTermList)
            false
        }
    }

    private fun setSearchTermAdapter() {
        listener = object : SearchTermAdapter.IOnItemClickListener {
            override fun removeItemClickListener(pos: Int) {
                searchTermList.removeAt(pos)
                searchTermAdapter?.updateTermList(searchTermList)
            }

        }
        binding?.rvSearch?.recycledViewPool?.clear()
        searchTermAdapter = SearchTermAdapter(this, listener)
        searchTermAdapter?.updateTermList(searchTermList)
        binding?.rvSearch?.adapter = searchTermAdapter
    }

    private fun highlightText(fullText: String, highlightText: String) {
        // highlight search text
        // highlight search text
        if (highlightText != null && !highlightText.isEmpty()) {
            val startPos: Int =
                fullText.toLowerCase(Locale.US).indexOf(highlightText.toLowerCase(Locale.US))
            val endPos: Int = startPos + highlightText.length
            if (startPos != -1) {
                val spannable: Spannable = SpannableString(fullText)
                val blueColor = ColorStateList(
                    arrayOf(intArrayOf()), intArrayOf(
                        resources.getColor(
                            R.color.highlight_color, null
                        )
                    )
                )
                val highlightSpan = TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null)
                spannable.setSpan(
                    highlightSpan,
                    startPos,
                    endPos,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding?.tvSearchTitle?.text = spannable
            } else {
                binding?.tvSearchTitle?.text = fullText
            }
        } else {
            binding?.tvSearchTitle?.text = fullText
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {

        AlertDialog.Builder(this, 0)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _: DialogInterface?, _: Int ->
                finish()
                exitProcess(0)
            }.setNegativeButton("No", null).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchTermList.add(query)
            searchTermAdapter?.updateTermList(searchTermList)
            binding?.svItem?.setQuery("", false)
            binding?.svItem?.clearFocus()
            return true
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun initViewPager() {
        val adapter =
            binding?.tlBlock?.tabCount?.let { MyViewPagerAdapter(supportFragmentManager) }
        for (k in 0..1) {
            adapter?.addFragment(BlockFragment.newInstance("", ""), ""+k)
        }
        binding?.viewpagerBlock?.adapter = adapter
        binding?.tlBlock?.setupWithViewPager(binding?.viewpagerBlock)
        Constant.allotEachTabWithEqualWidth(binding?.tlBlock!!)
        binding?.viewpagerBlock?.offscreenPageLimit = 2
    }
}