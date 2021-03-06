package com.bdAdapter.itembindings;

import androidx.annotation.NonNull;
import com.bdAdapter.ItemBinding;
import com.bdAdapter.OnItemBind;

/**
 * An {@link OnItemBind} that selects item views by delegating to each item. Items must implement
 * {@link ItemBindingModel}.
 */
public class OnItemBindModel<T extends ItemBindingModel> implements OnItemBind<T> {

    @Override
    public void onItemBind(@NonNull ItemBinding itemBinding, int position, T item) {
        item.onItemBind(itemBinding);
    }
}
