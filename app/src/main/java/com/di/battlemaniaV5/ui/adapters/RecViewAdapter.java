package com.di.battlemaniaV5.ui.adapters;

import static com.di.battlemaniaV5.ui.fragments.PlayFragment.addView;
import static com.di.battlemaniaV5.ui.fragments.PlayFragment.balance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.di.battlemaniaV5.R;
import com.di.battlemaniaV5.models.GameData;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.toptas.fancyshowcase.FancyShowCaseQueue;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.MyViewHolder> {

    public interface GetGamedataLister{
        void GetGameData(GameData gameData);

    }

    Context mContext;
    List<GameData> gameData;
    GetGamedataLister gamedataLister;

    public RecViewAdapter(Context mContext, List<GameData> gameData,GetGamedataLister lister) {
        this.mContext = mContext;
        this.gameData = gameData;
        this.gamedataLister=lister;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.allgamedata,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        GameData modle=gameData.get(position);
        Picasso.get().load(modle.getGameImage()).placeholder(mContext.getDrawable(R.drawable.juspay_ic_reload)).into(holder.gamebanner);
        holder.matchesavailable.setText(mContext.getResources().getString(R.string.match_available_)+modle.getTotalUpcoming());
        holder.gamename
                .setText(modle.getGameName());

        new FancyShowCaseQueue()
                .add(addView(holder.showcaseimage,mContext.getResources().getString(R.string.show_case_1),"1"))
                .add(addView(balance,mContext.getResources().getString(R.string.show_case_2),"2"))
                .show();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamedataLister.GetGameData(modle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView gamebanner,showcaseimage;
        public TextView matchesavailable,gamename;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gamebanner=itemView.findViewById(R.id.gamebanner);
            matchesavailable=itemView.findViewById(R.id.matchesavailable);
            gamename=itemView.findViewById(R.id.gamename);
            showcaseimage=itemView.findViewById(R.id.showcaseimage);
        }
    }
}
