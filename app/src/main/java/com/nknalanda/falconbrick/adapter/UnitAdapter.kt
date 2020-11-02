package com.nknalanda.falconbrick.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nknalanda.falconbrick.R
import com.nknalanda.falconbrick.databinding.UnitItemBinding
import com.nknalanda.falconbrick.datamodel.BlockModel
import com.nknalanda.falconbrick.datamodel.UnitModel
import com.nknalanda.falconbrick.viewmodel.BlockItemViewModel

class UnitAdapter(
    private var context: Context,
    private var unitList: ArrayList<UnitModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var binding: UnitItemBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater,
            R.layout.unit_item, parent, false)
        viewHolder = BlockViewHolder(binding?.root!!)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val unitModel: UnitModel = unitList[position]
        val viewHolder: BlockViewHolder = holder as BlockViewHolder
        val itemViewModel = BlockItemViewModel(context, unitModel)
        binding?.vm = itemViewModel
        viewHolder.bind(position, unitModel)
    }

    override fun getItemCount(): Int {
        return unitList.size
    }

    inner class BlockViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view.rootView) {
        fun bind(position: Int, model: UnitModel) {

        }
    }
}