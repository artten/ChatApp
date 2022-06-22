package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.entities.Contact;
import com.example.chatapp.entities.MessagePost;

import java.util.List;

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessagesPostViewHolder> {

    class MessagesPostViewHolder extends RecyclerView.ViewHolder{
        private final TextView content;


        private MessagesPostViewHolder (View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.chatMessage);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(itemView.getContext(), MainActivity.class);
//                    Log.d("contactsadapter", "onClick: " + getAdapterPosition());
//                }
//            });
        }


    }

    private  final LayoutInflater mInflater;
    private List<MessagePost> messagePosts;
    private final String userId;

    public MessagesListAdapter(Context context, String userId) {
        mInflater = LayoutInflater.from(context);
        this.userId = userId;
    }

    @Override
    public  MessagesPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == 1) {
            itemView = mInflater.inflate(R.layout.user_message_box, parent, false);
        } else {
            itemView = mInflater.inflate(R.layout.contact_message_box, parent, false);
        }

        return  new MessagesPostViewHolder(itemView);
    }

    @Override
    public void  onBindViewHolder(MessagesPostViewHolder holder, int position) {
        if (messagePosts != null) {
            final MessagePost current = messagePosts.get(position);
            holder.content.setText(current.getMessage());
        }
    }

    public void setMessagePosts(List<MessagePost>  m){
        messagePosts = m;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(messagePosts != null)
            return messagePosts.size();
        else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(messagePosts.get(position).getSenderId().equals(userId)) {
            return 1;
        } else {
            return 2;
        }
    }

    public List<MessagePost> getMessagePosts() {
        return messagePosts;
    }

}
