package com.siddroid.genericrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * The type View repo generic adapter.
 *
 * @param <T> the type of list item
 * @param <K> the type of ViewHolder
 * @author Siddhesh
 */
public class GenericRecyclerViewAdapter<T,K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {

    private final DataBinder<K,T> mBinder;

    /**
     * Instantiates a new View repo generic adapter.
     *
     * @param mBinder the DataBinder
     */
    public GenericRecyclerViewAdapter(DataBinder<K,T> mBinder) {
        this.mBinder = mBinder;
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        checkState();
        return mBinder.createViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        checkState();
        mBinder.bindData(holder,position, mBinder.getList().get(position));
    }

    @Override
    public int getItemCount() {
        return mBinder.getList() != null ? mBinder.getList().size() : 0;
    }

    private void checkState(){
        if (mBinder == null) {
            throw new IllegalStateException("You must pass data mBinder while initializing adapter");
        }
    }

    /**
     * The interface <code>DataBinder</code>.</br>
     * Implement this interface to provide data to <code>GenericRecyclerViewAdapter</code> for RecyclerView
     *
     * @param <T> the type ViewHolder
     * @param <V> the type RecyclerView item
     */
    public interface DataBinder<T extends RecyclerView.ViewHolder,V>{
        /**
         * createViewHolder method creates and returns object of type <code>T extends RecyclerView.ViewHolder</code>
         *
         * @param parent   the parent
         * @param viewType the view type
         * @return the <code>T extends RecyclerView.ViewHolder</code>
         */
        T createViewHolder(ViewGroup parent, int viewType);

        /**
         * Bind data.
         *
         * @param holder   the holder
         * @param position the position
         * @param item     the item
         */
        void bindData(T holder, int position, V item);

        /**
         * Gets list.
         *
         * @return the list
         */
        List<V> getList();
    }
}
