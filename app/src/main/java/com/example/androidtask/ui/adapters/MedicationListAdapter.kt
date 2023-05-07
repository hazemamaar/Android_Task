package com.example.androidtask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.databinding.MedicationLayoutItemBinding
import com.example.data.network.models.Medication
import javax.inject.Inject


class MedicationListAdapter @Inject constructor() : RecyclerView.Adapter<MedicationListAdapter.MedicationViewHolder>() {

    inner class MedicationViewHolder(private val binding:MedicationLayoutItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Medication) {
            binding.medicationName.text = item.name
            binding.medicationStrength.text = item.strength
            binding.cardView.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    var medicationList: List<Medication>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<Medication>() {
        override fun areItemsTheSame(oldItem: Medication, newItem: Medication): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: Medication,
            newItem: Medication,
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

    private var onItemClickListener: ((Medication) -> Unit)? = null

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        val bleDevice = medicationList[position]
        holder.apply {
            bind(bleDevice)
        }
    }

    override fun getItemCount() = medicationList.size

    fun setOnItemClickListener(listener: (Medication) -> Unit) {
        onItemClickListener = listener
    }
}