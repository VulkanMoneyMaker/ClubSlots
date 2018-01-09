package chhar.afgaz.com.chhar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;


public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private List<Integer> image;
    private Context context;
    private OnGameClicl onGameClick;

    public interface OnGameClicl{
        void onClick();
    }

    public GameAdapter(List<Integer> image, Context context, OnGameClicl onGameClick) {
        this.image = image;
        this.context = context;
        this.onGameClick = onGameClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_casino, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(context.getResources().getDrawable(image.get(position)));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGameClick.onClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item);
        }
    }
}
