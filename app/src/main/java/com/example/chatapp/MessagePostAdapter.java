package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.entities.MessagePost;

import java.util.List;
/*
public class MessagePostAdapter extends RecyclerView.Adapter<MessagePostAdapter.MessagePostViewHolder> {

    class MessagePostViewHolder extends RecyclerView.ViewHolder {
        private final TextView message;
        private final boolean send;

        private MessagePostViewHolder(View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.chatMessage);
            send = true;

        }

        private final LayoutInflater mInflater;
        private List<MessagePost> messagePosts;

        public MessagePostAdapter(Context context) {mInflater = LayoutInflater.from(context);}

        @Override
        public MessagePostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.post_item, parent, false);
            return new MessagePostViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MessagePostViewHolder holder, int position) {
            if (messagePosts != null) {
                final MessagePost current = messagePosts.get(position);
                holder.message.setText(current.getMessage());
            }
        }

        public void setMessagePost(List<MessagePost> s) {
            messagePosts = s;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (messagePosts != null)
                return messagePosts.size();
            return 0;
        }

        public List<MessagePost> getMessagePosts() {return messagePosts;}

    }
}

 */
