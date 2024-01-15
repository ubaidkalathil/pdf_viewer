package com.example.testproject.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testproject.DB.DBHelper;
import com.example.testproject.MainActivity;
import com.example.testproject.R;
import com.example.testproject.Services.PdfDownloadTask;
import com.example.testproject.model.PdfListModel;

import java.util.List;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.MyHolder> {
    Context context;
    List<PdfListModel> pdf;
    public PdfAdapter(MainActivity homeActivity, List<PdfListModel> pdf) {
        this.pdf = pdf;
        this.context = homeActivity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_list_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        PdfListModel data = pdf.get(holder.getAdapterPosition());
        DBHelper DB = new DBHelper(context);
        holder.tvTitle.setText(data.getTitle());
        holder.tvAuthor.setText(data.getAuthor());
        holder.tvCode.setText(data.getISBN());

        Glide.with(context)
                .load(data.getCover())
                .placeholder(R.drawable.loade)
                .skipMemoryCache(true)
                .into(holder.ivCover);
        holder.ivDone.setImageResource(R.drawable.done);
        holder.ivDownload.setImageResource(R.drawable.download);

        holder.ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdfDownloadTask download = new PdfDownloadTask(context,data.getId());
                download.execute(data.getUrl());
                Log.e("SAVED","CLICK");
                holder.ivDownload.setVisibility(View.GONE);
                holder.downloadProgress.setVisibility(View.VISIBLE);
            }
        });

        holder.linearCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(context);
                if (db.getData().moveToFirst()) {
                    Log.e("SAVED","1");
                } else {
                    Log.e("SAVED","0");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return pdf.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvAuthor,tvCode;
        ImageView ivCover,ivDownload,ivDone;
        ProgressBar downloadProgress;
        CardView linearCard;

        public MyHolder(@NonNull View itemView) {
            super(itemView);


            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvCode = itemView.findViewById(R.id.tv_isbn);
            ivCover = itemView.findViewById(R.id.iv_cover);
            ivDownload = itemView.findViewById(R.id.iv_download);
            ivDone = itemView.findViewById(R.id.iv_done);
            downloadProgress = itemView.findViewById(R.id.down_progress);
            linearCard = itemView.findViewById(R.id.linear_card);





        }
    }
}
