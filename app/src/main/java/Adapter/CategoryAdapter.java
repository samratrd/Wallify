package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ritwik.wallify.R;
import com.example.ritwik.wallify.SectionDetail;

import java.util.List;

import Models.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }



    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_categories,parent,false );
       return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.CategoryViewHolder holder, int position) {

        Category category = categories.get(position);
        holder.textView.setText(category.title);
        Glide.with(context).load(category.thumburl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        ImageView imageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            textView = itemView.findViewById(R.id.category_text);
            imageView = itemView.findViewById(R.id.category_image);
        }

        @Override
        public void onClick(View v) {

            //get the position of the row
            int position = getAdapterPosition();
            Category category = categories.get(position);
            //Toast.makeText(context,category.getTitle(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, SectionDetail.class);
            intent.putExtra("title", category.getTitle());

            context.startActivity(intent);

        }
    }
}
