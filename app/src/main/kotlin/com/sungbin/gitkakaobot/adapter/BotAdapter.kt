package com.sungbin.gitkakaobot.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sungbin.gitkakaobot.R
import com.sungbin.gitkakaobot.databinding.LayoutBotBinding
import com.sungbin.gitkakaobot.model.BotItem
import com.sungbin.gitkakaobot.model.BotType
import com.sungbin.gitkakaobot.ui.activity.CodeEditActivity
import com.sungbin.sungbintool.extensions.setTint
import org.jetbrains.anko.startActivity
import java.util.*


/**
 * Created by SungBin on 2020-08-23.
 */

class BotAdapter constructor(
    private val items: ArrayList<BotItem>,
    private val activity: Activity
) : RecyclerView.Adapter<BotAdapter.ViewHolder>() {

    init {
        Collections.sort(items, Comparator { item, item2 ->
            return@Comparator item.index.compareTo(item2.index)
        })
    }

    class ViewHolder(private val itemBinding: LayoutBotBinding, private val activity: Activity) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindViewHolder(bot: BotItem) {
            with(itemBinding) {
                tvName.isSelected = true
                ivEdit.setOnClickListener {
                    activity.startActivity<CodeEditActivity>("bot" to bot.toString())
                }

                item = bot
                if (bot.type == BotType.SIMPLE) {
                    trivIcon.setTint(
                        ContextCompat.getColor(
                            trivIcon.context,
                            R.color.colorPrimary
                        )
                    )
                }
                invalidateAll()
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.layout_bot, viewGroup, false
            ), activity
        )

    override fun onBindViewHolder(@NonNull viewholder: ViewHolder, position: Int) {
        viewholder.bindViewHolder(items[position])
    }

    override fun getItemCount() = items.size
    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position
}