package com.example.pixabay.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pixabay.databinding.ItemImageBinding
import com.example.pixabay.model.ImageModel

class PixaAdapter() :RecyclerView.Adapter<PixaViewHolder>() {
    private var list: ArrayList<ImageModel> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list.get(position))


    }fun addList(newList: List<ImageModel>){
        list.addAll(newList)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class PixaViewHolder(var binding: ItemImageBinding) :RecyclerView.ViewHolder(binding.root) {
    fun onBind(model: ImageModel) {
        binding.pixaImage.load(model.largeImageURL)


    }

}
