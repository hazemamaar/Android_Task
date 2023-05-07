package com.example.androidtask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.network.models.AssociatedDrug
import com.example.androidtask.databinding.MedicationLayoutItemBinding
import javax.inject.Inject


class MedicationListAdapter @Inject constructor() : RecyclerView.Adapter<MedicationListAdapter.MedicationViewHolder>() {

    inner class MedicationViewHolder(private val binding:MedicationLayoutItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: AssociatedDrug) {
            binding.medicationName.text = item.name
            binding.medicationDose.text = item.dose
            binding.medicationStrength.text = item.strength

        }
    }

    var medicationList: List<AssociatedDrug>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<AssociatedDrug>() {
        override fun areItemsTheSame(oldItem: AssociatedDrug, newItem: AssociatedDrug): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: AssociatedDrug,
            newItem: AssociatedDrug,
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        return MedicationViewHolder(
            MedicationLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

//    private var onItemClickListener: ((BluetoothDevice) -> Unit)? = null

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        val bleDevice = medicationList[position]
        holder.apply {
            bind(bleDevice)
        }
    }

    override fun getItemCount() = medicationList.size

//    fun setOnItemClickListener(listener: (BluetoothDevice) -> Unit) {
//        onItemClickListener = listener
//    }
}