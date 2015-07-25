package np.com.aawaz.csitentrance.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import np.com.aawaz.csitentrance.R;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {
    LayoutInflater inflater;
    String[] names,desc;
    int[] images;


    public AboutAdapter(Context context, String[] names, String[] desc, int[] images){
        inflater=LayoutInflater.from(context);
        this.names=names;
        this.desc=desc;
        this.images=images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.about_each_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(names[position]);
        holder.descri.setText(desc[position]);
        holder.image.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView descri;
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.name);
            descri= (TextView) itemView.findViewById(R.id.detailInfo);
            image= (ImageView) itemView.findViewById(R.id.imageMan);
        }
    }
}