package com.example.test.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.calculator.R;

import java.util.ArrayList;
import java.util.List;

public class SymbolViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> mSymbolList;
    private Context mContext;
    private SymbolListener mSymbolListener;

    public SymbolViewAdapter(Context context){
        this.mContext=context;
        mSymbolList=new ArrayList<>();
    }

    public void setSymbolList(List<String> symbolList){
        if(mSymbolList!=null){
            mSymbolList.addAll(symbolList);
        }
    }

    public void setSymbolListener(SymbolListener listener) {
        this.mSymbolListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mSymbolList==null)return null;
        TextView tv=new TextView(mContext);
        tv.setText(mSymbolList.get(i));
        tv.setTextColor(0xffffffff);
        tv.setTextSize(32);
        tv.setSingleLine();
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundResource(R.drawable.symbol_btn_bg);
        RecyclerView.LayoutParams lp=new RecyclerView.LayoutParams(200, RecyclerView.LayoutParams.MATCH_PARENT);
        tv.setLayoutParams(lp);
        SymbolViewHolder holder=new SymbolViewHolder(tv);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(mSymbolList==null)return;
        ((TextView)viewHolder.itemView).setText(mSymbolList.get(i));

    }

    @Override
    public int getItemCount() {
        return mSymbolList.size();
    }

    public static class SymbolViewHolder extends RecyclerView.ViewHolder{

        public SymbolViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class SymbolDecoration  extends RecyclerView.ItemDecoration{

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, int itemPosition, @NonNull RecyclerView parent) {
            outRect.set(1, 3, 2, 3);
        }
    }

    public static interface SymbolListener{
        void onSymbolClick(int index);
    }
}
