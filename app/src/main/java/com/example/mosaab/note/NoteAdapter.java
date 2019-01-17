package com.example.mosaab.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends ListAdapter<Note,NoteAdapter.NoteViewHolder> {

    private OnNoteClickListener onNoteClickListener;

    protected NoteAdapter() {

        super(DIIF_CALLbACK);

    }

    private static final DiffUtil.ItemCallback<Note> DIIF_CALLbACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note currentNote = getItem(position);

        holder.title_tv.setText(currentNote.getTitle());
        holder.priority_tv.setText(String.valueOf(currentNote.getPriority()));
        holder.desc_tv.setText(currentNote.getDescription());

    }


    public Note getNoteAt(int position) {
        return getItem(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView title_tv, desc_tv, priority_tv;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            title_tv = itemView.findViewById(R.id.text_view_title);
            desc_tv = itemView.findViewById(R.id.text_view_desc);
            priority_tv = itemView.findViewById(R.id.text_view_priority);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if (onNoteClickListener != null && position != RecyclerView.NO_POSITION) {
                        onNoteClickListener.onNoteClick(getItem(position));

                    }

                }
            });

        }

    }

    public void setOnItemClickListener(OnNoteClickListener listener) {
        onNoteClickListener = listener;

    }
}
