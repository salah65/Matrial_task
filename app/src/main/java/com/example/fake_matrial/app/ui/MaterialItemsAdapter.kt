package com.example.fake_matrial.app.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fake_matrial.R
import com.example.fake_matrial.databinding.MaterialsRvItemBinding
import com.example.fake_matrial.domain.model.Material
import com.example.fake_matrial.domain.model.MaterialType

class MaterialItemsAdapter(private var materials: List<Material>? = null) :
    RecyclerView.Adapter<MaterialItemsAdapter.ViewHolder>() {

    class ViewHolder(binding: MaterialsRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = MaterialsRvItemBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MaterialsRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun updateData(newData: List<Material>) {
        materials = newData
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materialItem = materials?.get(position)
        if (materialItem?.type == MaterialType.PDF) {
            holder.binding.icon.setImageResource(R.drawable.pdf)
            holder.binding.root.background =
                ResourcesCompat.getDrawable(
                    Resources.getSystem(),
                    R.drawable.pdf_material_item_bg,
                    null
                )
        } else {
            holder.binding.icon.setImageResource(R.drawable.mp4)
            holder.binding.root.background =
                ResourcesCompat.getDrawable(
                    Resources.getSystem(),
                    R.drawable.video_material_item_bg,
                    null
                )
        }
        holder.binding.name.text = materialItem?.name
        holder.binding.type.text = materialItem?.type?.name

    }

    override fun getItemCount(): Int {
        return materials?.size ?: 0
    }
}