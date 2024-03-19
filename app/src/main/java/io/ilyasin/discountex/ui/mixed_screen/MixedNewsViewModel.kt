package io.ilyasin.discountex.ui.mixed_screen

import dagger.hilt.android.lifecycle.HiltViewModel
import io.ilyasin.discountex.data.DataSource
import io.ilyasin.discountex.data.ItemData
import io.ilyasin.discountex.domain.MixedFeedUseCase
import io.ilyasin.discountex.ui.common.GridViewModel
import javax.inject.Inject


@HiltViewModel
class MixedNewsViewModel @Inject constructor(mixedFeedUseCase: MixedFeedUseCase) : GridViewModel(mixedFeedUseCase) {

    override fun runPostItemProcessingTask(items: List<ItemData>): List<ItemData> {
        return  items.sortedWith(ItemsComparator())
    }

    class ItemsComparator : Comparator<ItemData> {
        override fun compare(item1: ItemData, item2: ItemData): Int {
            return when {
                item1.source == DataSource.Sport && item2.source == DataSource.Entertainment -> -1
                item1.source == DataSource.Entertainment && item2.source == DataSource.Sport -> 1
                else -> 0
            }
        }
    }
}