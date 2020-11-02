package com.nknalanda.falconbrick.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nknalanda.falconbrick.R
import com.nknalanda.falconbrick.databinding.SearchTermItemBinding
import com.nknalanda.falconbrick.viewmodel.SearchTermItemViewModel

class SearchTermAdapter(
    private var context: Context,
    private var listener: IOnItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var termList = ArrayList<String>()
    private var binding: SearchTermItemBinding? = null

    fun updateTermList(items: ArrayList<String>) {
        this.termList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater,
            R.layout.search_term_item, parent, false)
        viewHolder = SearchTermViewHolder(binding?.root!!)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val searchTerm: String = termList[position]
        val viewHolder: SearchTermViewHolder = holder as SearchTermViewHolder
        val itemViewModel = SearchTermItemViewModel(context, searchTerm)
        binding?.vm = itemViewModel
        viewHolder.bind(position, searchTerm)
    }

    override fun getItemCount(): Int {
        return  termList.size
    }

    inner class SearchTermViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view.rootView) {
        fun bind(pos: Int, term: String) {
            binding?.tvSearchTerm?.text = term
            binding?.ivRemoveTerm?.setOnClickListener {
                listener.removeItemClickListener(pos)
            }
        }
    }

    interface IOnItemClickListener {
        fun removeItemClickListener(pos: Int)
    }

}