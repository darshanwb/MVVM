package com.app.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mvvm.R
import com.app.mvvm.databinding.RawItemListBinding
import com.app.mvvm.model.NotificationData
import com.bumptech.glide.Glide


class AdapterNotificationList(
    var notificationList: ArrayList<NotificationData>,
//    var onClick: OnItemSelected<Int>,
) : RecyclerView.Adapter<AdapterNotificationList.ViewHolder>() {

    inner class ViewHolder(var binding: RawItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NotificationData) {
            binding.data = data
//            Glide.with(binding.imgIcon).load(data.imageUser).placeholder(R.drawable.img_boy)
//                .error(R.drawable.img_boy).into(binding.imgIcon)

            if (data.type == "comment") {
                binding.tvUserName.text = data.actionUserData?.name
                binding.tvDescription.text = "${data.actionUserData?.name} Commented on your post : ${data.commentData?.comment}"
                Glide.with(binding.imgIcon).load("https://mindtrash.s3.us-west-2.amazonaws.com/${data.actionUserData?.profilePic}").placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground).into(binding.imgIcon)
            } else if (data.type == "like") {
                binding.tvUserName.text = data.actionUserData?.name
                binding.tvDescription.text = "${data.actionUserData?.name} Like on your post"
                Glide.with(binding.imgIcon).load("https://mindtrash.s3.us-west-2.amazonaws.com/${data.actionUserData?.profilePic}").placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground).into(binding.imgIcon)
            } else {
                binding.tvDescription.text = ""
            }

            var background = if (data.isRead == true) {
                itemView.context.getColor(R.color.white)
            } else {
                itemView.context.getColor(R.color.grey)
            }
//
            binding.layNotification.setBackgroundColor(background)

            binding.layNotification.setOnClickListener {
                //onClick.onItemSelected(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = RawItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val my = notificationList[position]
        holder.bind(my)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }
}
