package com.example.taskmanage.modelcalss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanage.R;
import com.example.taskmanage.TaskTrackingProgress;

import java.util.ArrayList;

public class recycleViewprogrss extends RecyclerView.Adapter<recycleViewprogrss.MyViewHolder> {
    private ArrayList<progrclass> progrclassArrayList;

    public  recycleViewprogrss(ArrayList<progrclass> progrclassArrayList){
        this.progrclassArrayList = progrclassArrayList;
    }
    public  class  MyViewHolder extends RecyclerView.ViewHolder{
        private TextView titleId;
        private ProgressBar progressBar;
        private RadioButton radioButton1;
        private RadioButton radioButton2;
        private Button submitBtn;
        public MyViewHolder(final View view){
            super(view);
            titleId = view.findViewById(R.id.titleId);
            progressBar = view.findViewById(R.id.progress_bar);
            radioButton1 = view.findViewById(R.id.completed);
            radioButton2 = view.findViewById(R.id.inCamplet);
            submitBtn = view.findViewById(R.id.submit);


        }
    }

    @NonNull
    @Override
    public recycleViewprogrss.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View progrssView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progressbar,parent,false);
        return new MyViewHolder(progrssView);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleViewprogrss.MyViewHolder holder, int position) {
        String uTitle = progrclassArrayList.get(position).titeName;
        int uprogrss = progrclassArrayList.get(position).progrBar;

        holder.titleId.setText(uTitle);
        holder.progressBar.setProgress(uprogrss);
        holder.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.radioButton1.isChecked()){

                }else if(holder.radioButton2.isChecked()){

                }else{
                    Toast.makeText(view.getContext(), "Please select whether the task have completed or not", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return progrclassArrayList.size();
    }
}
