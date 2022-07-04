package com.alican.resimlimesajlar.HomePage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alican.resimlimesajlar.FinishActivity;
import com.alican.resimlimesajlar.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.cardDesign> {

    private Context mContext;
    private List<homeName> homeNameList;

    public homeAdapter(Context mContext, List<homeName> homeNameList) {
        this.mContext = mContext;
        this.homeNameList = homeNameList;
    }

    public class cardDesign extends RecyclerView.ViewHolder {

        private ImageView homeImageView;
        private TextView homeTextView;
        private CardView homeCardView;
        private ProgressBar finishProgressBar1;

        public cardDesign(@NonNull View itemView) {
            super(itemView);

            homeImageView = itemView.findViewById(R.id.homeImage);
            homeTextView = itemView.findViewById(R.id.homeText);
            homeCardView = itemView.findViewById(R.id.homeCardView);
            finishProgressBar1 = itemView.findViewById(R.id.finishProgressBar1);
        }
    }

    @NonNull
    @Override
    public homeAdapter.cardDesign onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_design,parent,false);

        return new cardDesign(view);
    }

    @Override
    public void onBindViewHolder(@NonNull homeAdapter.cardDesign holder, int position) {

        final homeName name = homeNameList.get(position);
        holder.homeTextView.setText(name.getText());

        Picasso.get().load(homeNameList.get(position).getImage()).into(holder.homeImageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.finishProgressBar1.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        holder.homeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, FinishActivity.class);

                intent.putExtra("url_id",name);

                mContext.startActivity(intent);


                /*
                if(name.getId() == 0){
                    //Toast.makeText(mContext,"0",Toast.LENGTH_LONG).show();

                    //mContext.startActivity(new Intent(mContext, FinishActivity.class));

                    Intent intent = new Intent(mContext, FinishActivity.class);

                    intent.putExtra("url_id",name);

                    mContext.startActivity(intent);
                }
                 */


            }
        });

    }

    @Override
    public int getItemCount() {
        return homeNameList.size();
    }


}
