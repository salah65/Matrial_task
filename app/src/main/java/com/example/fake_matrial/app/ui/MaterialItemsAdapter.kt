package com.example.fake_matrial.app.ui

import android.content.res.Resources
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.fake_matrial.R
import com.example.fake_matrial.databinding.MaterialsRvItemBinding
import com.example.fake_matrial.domain.model.DownloadStatus
import com.example.fake_matrial.domain.model.Material
import com.example.fake_matrial.domain.model.MaterialType

class MaterialItemsAdapter(
    private var materials: List<Material>? = null,
    private val onDownloadClickListener: (materialItem: Material, position: Int) -> Unit
) :
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
        this.materials = newData
        notifyDataSetChanged()
    }

    fun updateMaterialState(state: DownloadStatus, position: Int) {
        this.materials?.get(position)?.DownloadStatus = state
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materialItem = materials?.get(position)
        if (materialItem?.type == MaterialType.PDF) {
            holder.binding.icon.setImageResource(R.drawable.pdf)
            holder.binding.root.setBackgroundResource(R.drawable.pdf_material_item_bg)

        } else {
            holder.binding.icon.setImageResource(R.drawable.mp4)
            holder.binding.root.setBackgroundResource(R.drawable.video_material_item_bg)

        }
        holder.binding.name.text = materialItem?.name
        holder.binding.type.text = materialItem?.type?.name
        holder.binding.imageView2.setOnClickListener {
            holder.binding.imageView2.playAnimation()
            materialItem?.let { material -> onDownloadClickListener.invoke(material, position) }
        }
        when (materialItem?.DownloadStatus) {
            is DownloadStatus.Downloading -> {
                holder.binding.imageView2.playAnimation()
                holder.binding.done.visibility=View.GONE
                holder.binding.imageView2.visibility=View.VISIBLE
                holder.binding.progressBar2.visibility = View.VISIBLE
                holder.binding.progressBar2.progress =
                    (materialItem.DownloadStatus as DownloadStatus.Downloading).progress
            }
            is DownloadStatus.Downloaded -> {
                holder.binding.done.visibility=View.VISIBLE
                holder.binding.imageView2.pauseAnimation()
                holder.binding.imageView2.visibility=View.GONE
                holder.binding.progressBar2.visibility = View.GONE
                holder.binding.progressBar2.progress = 0
            }
            is DownloadStatus.NotDownloaded -> {
                holder.binding.done.visibility=View.GONE
                holder.binding.imageView2.pauseAnimation()
                holder.binding.imageView2.visibility=View.VISIBLE
                holder.binding.progressBar2.visibility = View.GONE
                holder.binding.progressBar2.progress = 0
            }
        }
    }

    override fun getItemCount(): Int {
        return materials?.size ?: 0
    }
}