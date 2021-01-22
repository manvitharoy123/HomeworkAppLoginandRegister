package com.example.ns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ImageAdapter extends Adapter<ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;

    public class ImageViewHolder extends ViewHolder {
        public ImageView imageView;
        public TextView textViewName;

        public ImageViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.text_view_name);
            this.imageView = (ImageView) itemView.findViewById(R.id.image_view_upload);
        }
    }

    public ImageAdapter(Context context, List<Upload> uploads) {
        this.mContext = context;
        this.mUploads = uploads;
    }

    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.image_item, parent, false));
    }

    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = (Upload) this.mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        Picasso.with(this.mContext).load(uploadCurrent.getImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerInside().into(holder.imageView);
    }

    public int getItemCount() {
        return this.mUploads.size();
    }
}
