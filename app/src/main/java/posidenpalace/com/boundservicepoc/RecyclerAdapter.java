package posidenpalace.com.boundservicepoc;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    List<String> randomStrings = new ArrayList<>();
    public RecyclerAdapter(List<String> randomStrings) {
        this.randomStrings = randomStrings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String rando = randomStrings.get(position);
        holder.text.setText(rando);

    }

    @Override
    public int getItemCount() {
        return randomStrings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tvLOSrandomText);
        }
    }
}
