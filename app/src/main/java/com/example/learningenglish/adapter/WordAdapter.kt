package com.example.learningenglish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningenglish.databinding.ItemWordBinding
import com.example.learningenglish.model.Word

class WordAdapter(
    private var wordList: List<Word>,
    private val onItemClick: (folder: Word) -> Unit
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun getItemCount(): Int = wordList.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = wordList[position]
        holder.bind(word)
    }

    inner class WordViewHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word : Word) {
            binding.tvNameWord.text = word.name
            binding.tvState.text = word.learState
            binding.lnItemWord.setOnClickListener {
                onItemClick.invoke(word)
            }
        }
    }
}