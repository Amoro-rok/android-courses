package coeait.g333.orlov.lab08_gameoflife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.FieldViewHolder> {

    public interface onItemClickListener {
        void onItemClick(FieldData fieldData, int position);
    }
    public List<FieldData> fieldlist;


    public onItemClickListener onClickListener;


    public FieldAdapter(ArrayList<FieldData> fieldlist, onItemClickListener onClickListener) {
        this.fieldlist = fieldlist;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public FieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_field, parent, false);
        return new FieldViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldViewHolder holder, int position) {
        FieldData fieldData = fieldlist.get(position);
        holder.fieldName.setText(fieldData.name);
        holder.fieldSize.setText("(" + fieldData.width + "x" + fieldData.height + ")");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemClick(fieldData, position);
            }
        });
    }

    public int getItemCount() {
        return fieldlist.size();
    }


    public static class FieldViewHolder extends RecyclerView.ViewHolder {
        public TextView fieldName;
        public TextView fieldSize;


        public FieldViewHolder(@NonNull View itemView) {
            super(itemView);
            fieldName = itemView.findViewById(R.id.tv_field_name);
            fieldSize = itemView.findViewById(R.id.tv_field_size);
            itemView.setOnClickListener(null);
        }
    }
}
