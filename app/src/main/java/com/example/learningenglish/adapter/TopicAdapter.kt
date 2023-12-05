package com.example.learningenglish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningenglish.databinding.ItemTopicBinding
import com.example.learningenglish.model.Topic

class TopicAdapter(
    private var topicList: List<Topic>,
    private val onItemClick: (topic: Topic) -> Unit
) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun getItemCount(): Int = topicList.size

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = topicList[position]
        holder.bind(topic)
    }

    inner class TopicViewHolder(private val binding: ItemTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Topic) {
            binding.tvNameTopic.text = data.name
            binding.tvTotalWord.text = data.total.toString()
            when(data.mode) {
                0 -> binding.tvModeTopic.text = "Public"
                1 -> binding.tvModeTopic.text = "Private"
            }
            binding.lnItemTopic.setOnClickListener {
                onItemClick.invoke(data)
            }
        }
    }
}