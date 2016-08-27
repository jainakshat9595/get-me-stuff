package in.jainakshat.getmestuff.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.jainakshat.getmestuff.R;
import in.jainakshat.getmestuff.database.FirebaseUtil;
import in.jainakshat.getmestuff.model.Item;

/**
 * Created by jainakshat9595 on 27/8/16.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private Context mContext;
    private ArrayList<Item> mItems;

    public MainAdapter(Context context) {
        this.mContext = context;
        this.mItems = new ArrayList<>();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vh_main_list, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final Item item = mItems.get(position);
        holder.name.setText(item.getName());
        holder.quant.setText(item.getQuant());
        holder.desc.setText(item.getDesc());
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUtil.deleteItem(item.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItem(Item item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void deleteItem(Item item) {
        int index = -1;
        for(int i=0; i<mItems.size(); i++) {
            if(mItems.get(i).getId().equals(item.getId())) {
                index = i;
                break;
            }
        }
        mItems.remove(index);
        notifyDataSetChanged();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView quant;
        TextView desc;
        ImageView done;

        public MainViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.vh_name);
            quant = (TextView) itemView.findViewById(R.id.vh_quant);
            desc = (TextView) itemView.findViewById(R.id.vh_desc);
            done = (ImageView) itemView.findViewById(R.id.vh_done);
        }
    }
}
