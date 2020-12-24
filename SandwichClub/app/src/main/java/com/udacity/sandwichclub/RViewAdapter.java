package com.udacity.sandwichclub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.ViewHolder> {
    private String[] sandwiches_names;

    RViewAdapter(String[] sandwiches_names) {
        this.sandwiches_names = sandwiches_names;
    }

    @Override
    public RViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RViewAdapter.ViewHolder holder, int position) {
        String sandwich_name = sandwiches_names[position];

        holder.item_view.setText(sandwich_name);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return sandwiches_names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_view;

        ViewHolder(View view) {
            super(view);
            item_view = view.findViewById(R.id.item_view);
        }
    }

    final private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(context, DetailActivity.class);

            intent.putExtra(DetailActivity.EXTRA_POSITION, position);
            context.startActivity(intent);
        }
    };
}
