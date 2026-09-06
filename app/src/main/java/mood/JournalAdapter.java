package mood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mood.R;

import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.ViewHolder> {
    private final List<JournalEntry> entries;
    private final Context context;

    public JournalAdapter(Context context, List<JournalEntry> entries) {
        this.context = context;
        this.entries = entries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_journal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JournalEntry e = entries.get(position);
        holder.tvEntry.setText(e.getEntry());
        holder.tvDate.setText(e.getDate());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEntry, tvDate;
        ViewHolder(View itemView) {
            super(itemView);
            tvEntry = itemView.findViewById(R.id.tvEntry);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
