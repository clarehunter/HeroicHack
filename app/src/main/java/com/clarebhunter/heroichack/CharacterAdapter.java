package com.clarebhunter.heroichack;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharViewHolder> {
    private List<Character> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class CharViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public TextView mTextView;
        public CharViewHolder(View view) {
            super(view);
            mView = view;
            mTextView = (TextView) view.findViewById(R.id.char_text_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CharacterAdapter(List<Character> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterAdapter.CharViewHolder onCreateViewHolder(ViewGroup parent,
                                                                           int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_list_content, parent, false);
        return new CharacterAdapter.CharViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CharacterAdapter.CharViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Character character = mDataset.get(position);
        holder.mTextView.setText(character.getName());
        holder.mView.setBackgroundColor(character.isSelected() ? holder.mView.getContext().getResources().getColor(R.color.colorBrightAccent) : Color.WHITE);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                character.setSelected(!character.isSelected());
                holder.mView.setBackgroundColor(character.isSelected() ? holder.mView.getContext().getResources().getColor(R.color.colorBrightAccent) : Color.WHITE);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}
