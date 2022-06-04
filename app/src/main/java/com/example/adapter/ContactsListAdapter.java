package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.ChatContacts;
import com.example.chatapp.MainActivity;
import com.example.chatapp.R;
import com.example.chatapp.entities.Contact;
import com.example.chatapp.entities.Post;
import com.example.chatapp.viewModel.ContactsViewModel;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;
        private final TextView nickName;


        private ContactViewHolder (View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            nickName = itemView.findViewById(R.id.nickName);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(itemView.getContext(), MainActivity.class);
//                    Log.d("contactsadapter", "onClick: " + getAdapterPosition());
//                }
//            });
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    private  final LayoutInflater mInflater;
    private List<Contact> contacts;
    private RecyclerViewListener listener;

    public ContactsListAdapter(Context context, RecyclerViewListener listener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public  ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.contact_layout, parent, false);
        return  new ContactViewHolder(itemView);
    }

    @Override
    public void  onBindViewHolder(ContactViewHolder holder, int position) {
        if (contacts != null) {
            final Contact current = contacts.get(position);
            holder.name.setText(current.getName());
            holder.nickName.setText(current.getNickName());
        }
    }

    public void setContacts(List<Contact>  c){
        contacts = c;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(contacts != null)
            return contacts.size();
        else return 0;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public interface  RecyclerViewListener {
        void onClick(View v, int position);
    }
}
