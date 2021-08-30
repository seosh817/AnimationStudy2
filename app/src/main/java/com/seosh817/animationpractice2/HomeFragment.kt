package com.seosh817.animationpractice2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import androidx.vectordrawable.graphics.drawable.SeekableAnimatedVectorDrawable
import com.seosh817.animationpractice2.databinding.FragmentHomeBinding
import com.seosh817.animationpractice2.databinding.ItemHomeBinding
import com.seosh817.animationpractice2.utils.NoDiffCallback

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private var savd: SeekableAnimatedVectorDrawable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view).apply {
            SeekableAnimatedVectorDrawable.create(view.context, R.drawable.avd_search)?.let {
                savd = it

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    class Adapter(private val onItemClick: (HomeItemUiModel) -> Unit) :
        ListAdapter<HomeItemUiModel, ViewHolder>(
            NoDiffCallback()
        ) {

        private var expandedPosition = NO_POSITION
            set(value) {
                val from = field
                field = value
                if (from != NO_POSITION) notifyItemChanged(from)
                if (value != NO_POSITION) notifyItemChanged(from)
            }

        init {
            stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemHomeBinding.inflate(inflater, parent, false)
            return ViewHolder(binding, { _, position ->
                onItemClick(getItem(position))
            }, { position ->
                expandedPosition = if (expandedPosition == position) {
                    NO_POSITION
                } else {
                    position
                }
            })
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position), expanded = expandedPosition == position)
        }


    }

    class ViewHolder(
        private val binding: ItemHomeBinding,
        private val onItemClick: (ItemHomeBinding, Int) -> Unit,
        private val onMoreClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick(binding, bindingAdapterPosition)
            }
            binding.more.setOnClickListener {
                onMoreClick(bindingAdapterPosition)
            }
        }

        fun bind(uiModel: HomeItemUiModel, expanded: Boolean) {
            binding.ivThumnail.setImageResource(uiModel.resId)
            binding.newBadge.isVisible = uiModel.isNew

            binding.root.isActivated = expanded
            binding.ivThumnail.isVisible = expanded
        }
    }
}