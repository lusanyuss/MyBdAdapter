package com.mybdadapter.bindingcollectionadapter2;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.viewpager2.widget.ViewPager2;

import com.bdAdapter.BindingCollectionAdapters;
import com.bdAdapter.BindingRecyclerViewAdapter;
import com.bdAdapter.ItemBinding;
import com.bdAdapter.R;
import com.bdAdapter.collections.AsyncDiffObservableList;

import java.util.List;

/**
 * @see {@link BindingCollectionAdapters}
 */
public class BindingViewPager2Adapters {
    // RecyclerView
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"itemBinding", "items", "adapter", "itemIds", "viewHolder", "diffConfig"}, requireAll = false)
    public static <T> void setAdapter(ViewPager2 viewPager,
                                      ItemBinding<T> itemBinding,
                                      List<T> items,
                                      BindingRecyclerViewAdapter<T> adapter,
                                      BindingRecyclerViewAdapter.ItemIds<? super T> itemIds,
                                      BindingRecyclerViewAdapter.ViewHolderFactory viewHolderFactory,
                                      AsyncDifferConfig<T> diffConfig) {
        if (itemBinding != null) {
            BindingRecyclerViewAdapter oldAdapter = (BindingRecyclerViewAdapter) viewPager.getAdapter();
            if (adapter == null) {
                if (oldAdapter == null) {
                    adapter = new BindingRecyclerViewAdapter<>();
                } else {
                    adapter = oldAdapter;
                }
            }
            adapter.setItemBinding(itemBinding);

            if (diffConfig != null && items != null) {
                AsyncDiffObservableList<T> list = (AsyncDiffObservableList<T>) viewPager.getTag(R.id.bindingcollectiondapter_list_id);
                if (list == null) {
                    list = new AsyncDiffObservableList<>(diffConfig);
                    viewPager.setTag(R.id.bindingcollectiondapter_list_id, list);
                    adapter.setItems(list);
                }
                list.update(items);
            } else {
                adapter.setItems(items);
            }

            adapter.setItemIds(itemIds);
            adapter.setViewHolderFactory(viewHolderFactory);

            if (oldAdapter != adapter) {
                viewPager.setAdapter(adapter);
            }
        } else {
            viewPager.setAdapter(null);
        }
    }
}
