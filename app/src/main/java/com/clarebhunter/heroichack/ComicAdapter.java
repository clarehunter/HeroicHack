package com.clarebhunter.heroichack;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {
    private List<Comic> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ComicViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public CardView mCardView;
        public TextView mTitleTextView;
        public TextView mDescTextView;
        public ImageView mCoverImageView;
        public ComicViewHolder(View view) {
            super(view);
            mView = view;
            mCardView = (CardView) view.findViewById(R.id.card_view);
            mTitleTextView = (TextView) view.findViewById(R.id.comic_title_text);
            mDescTextView = (TextView) view.findViewById(R.id.comic_desc_text);
            mCoverImageView = (ImageView) view.findViewById(R.id.comic_cover_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ComicAdapter(List<Comic> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ComicAdapter.ComicViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comics_list_content, parent, false);
        return new ComicAdapter.ComicViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ComicAdapter.ComicViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Comic comic = mDataset.get(position);
        holder.mTitleTextView.setText(comic.getName());
        holder.mDescTextView.setText(comic.getDescription());
        Picasso.get().load(comic.getImagePath()).into(holder.mCoverImageView);
        //TODO: account for comics with incorrect/no provided image path
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}
