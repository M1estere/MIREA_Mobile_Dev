package com.mirea.solovyevia.retrofitapp.RecView;

import static com.mirea.solovyevia.retrofitapp.MainActivity.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirea.solovyevia.retrofitapp.ApiWork.ApiService;
import com.mirea.solovyevia.retrofitapp.ApiWork.RetrofitClient;
import com.mirea.solovyevia.retrofitapp.ApiWork.ToDo;
import com.mirea.solovyevia.retrofitapp.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {

    private LayoutInflater layoutInflater;
    private List<ToDo> todos;

    private Random random;
    private String[] imageUrls = new String[] {
            "https://cdn.myanimelist.net/images/anime/1015/138006.jpg",
            "https://cdn.myanimelist.net/images/anime/1208/94745.jpg",
            "https://cdn.myanimelist.net/images/anime/1935/127974.jpg",
            "https://cdn.myanimelist.net/images/anime/1517/100633.jpg",
            "https://cdn.myanimelist.net/images/anime/3/72078.jpg",
            "https://cdn.myanimelist.net/images/anime/1337/99013.jpg"
    };

    public ToDoAdapter(Context context, List<ToDo> todoList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.todos = todoList;
        random = new Random();
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDo todo = todos.get(position);
        holder.textViewTitle.setText(todo.getTitle());
        holder.checkBoxCompleted.setChecked(todo.getCompleted());

        holder.checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            todo.setCompleted(isChecked);
            updateTodoCompletion(todo.getId(), isChecked);
        });

        Picasso.get()
                .load(imageUrls[random.nextInt(imageUrls.length)])
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .resize(50, 50)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        System.out.println("Exception " + e.toString() + " occured");
                    }
                });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    private void updateTodoCompletion(int todoId, boolean completed) {
        ApiService todoApi = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        ToDo toUpdate = todos.get(todoId);
        toUpdate.setCompleted(completed);


        Call<ToDo> call = todoApi.updateTodo(todoId, toUpdate);
        call.enqueue(new Callback<ToDo>() {
            @Override
            public void onResponse(Call<ToDo> call, Response<ToDo> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "ToDo updated: " + response.code());
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ToDo> call, Throwable t) {
                Log.e(TAG, "onFailure:	" + t.getMessage());
            }
        });
    }

}
