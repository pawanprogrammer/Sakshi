package com.cetpainfotech.onlinelearning.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cetpainfotech.onlinelearning.R;


/**
 * Created by Dell on 22-Jan-18.
 */

public class InterviewAdapter extends RecyclerView.Adapter<InterviewAdapter.MyNoteHolder>{
    Context context;
    private String[] name;
    private String[] url;

    public InterviewAdapter(Context context, String[] name, String[] url) {
        this.context = context;
        this.name = name;
        this.url = url;
    }

    @Override
    public MyNoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.interviewlist,parent,false);
        return new MyNoteHolder(view);
    }

    @Override
    public void onBindViewHolder(MyNoteHolder holder, final int position) {
        holder.noteName.setText(name[position]);
        holder.noteUrl.setText(url[position]);
       /* holder.book_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url[position]));
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    class MyNoteHolder extends RecyclerView.ViewHolder{
        TextView noteName,noteUrl;
        ConstraintLayout book_layout;
        public MyNoteHolder(View itemView) {
            super(itemView);
            noteName=itemView.findViewById(R.id.noteName);
            noteUrl=itemView.findViewById(R.id.noteUrl);
            //book_layout = itemView.findViewById(R.id.book_layout);
        }
    }
}
