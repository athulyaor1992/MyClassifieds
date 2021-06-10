package com.example.myclassifieds.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myclassifieds.R
import com.example.myclassifieds.model.Classify
import com.example.myclassifieds.ui.DetailActivity
import kotlinx.android.synthetic.main.classify_item.view.*
import java.text.SimpleDateFormat


class ClassifyAdapter(
    private val users: ArrayList<Classify?>,
    private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<ClassifyAdapter.DataViewHolder>() {

    interface CellClickListener {
        fun onCellClickListener(data: Classify)
    }


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Classify) {
            itemView.apply {
                classifyName.text = user.name
                classifyPrice.text = user.price

                var date = user.created_at
                var spf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val newDate = spf.parse(date)
                spf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                date = spf.format(newDate)

                classifyDate.text = date

                Glide.with(this)
                    .load(user.image_urls_thumbnails[0])
                    .into(classifyImage);

                details.setOnClickListener {

                    val intent = Intent (context, DetailActivity::class.java)
                    intent.putExtra("classify", user)
                    context.startActivity(intent)

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.classify_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position]!!)

        val data = users[position]

        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(data!!)
        }

    }

    fun addGridItemsToView(position: Int, data: Classify?) {
      //  tileList.add(position, data)
        internalNotifyItemInserted(position)
    }

    fun internalNotifyItemInserted(position: Int) {
        notifyItemInserted(position)
    }

    fun addUsers(users: List<Classify>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}