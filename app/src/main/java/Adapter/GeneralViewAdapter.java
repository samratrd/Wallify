package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ritwik.wallify.R;

import java.util.List;

import Models.GeneralViewModel;

public class GeneralViewAdapter extends RecyclerView.Adapter<GeneralViewAdapter.GeneralViewHolder> {

    private Context context;
    private List<GeneralViewModel> generalViewModels;

    public GeneralViewAdapter(Context context, List<GeneralViewModel> generalViewModels) {
        this.context = context;
        this.generalViewModels = generalViewModels;
    }



    @NonNull
    @Override
    public GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.general_row_view_image_model,parent,false );
        return new GeneralViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralViewHolder holder, int position) {

        GeneralViewModel gvm = generalViewModels.get(position);
        Glide.with(context).load(gvm.url).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return generalViewModels.size();
    }

    public class GeneralViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public GeneralViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.general_image_view);
        }
    }
}
