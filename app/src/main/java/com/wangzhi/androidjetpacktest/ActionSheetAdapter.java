package com.wangzhi.androidjetpacktest;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangzh.androidjetpacktest.R;

import java.util.List;

public class ActionSheetAdapter extends RecyclerView.Adapter<ActionSheetAdapter.ViewHolder> {
    private List<ActionItem> actionList;
    private OnActionClickListener onActionClick;

    public ActionSheetAdapter(List<ActionItem> actionList, OnActionClickListener onActionClick) {
        this.actionList = actionList;
        this.onActionClick = onActionClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_action_sheet, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ActionItem actionItem = actionList.get(position);
        if (actionItem.getTag() == -2) {
            //取消
            holder.line_top.setVisibility(View.VISIBLE);
            holder.line_bottom.setVisibility(View.GONE);
        } else {
            //action,title
            holder.line_top.setVisibility(View.GONE);
            holder.line_bottom.setVisibility(View.VISIBLE);
        }
        holder.tv_title.setText(actionItem.title);
        holder.tv_title.setTextColor(actionItem.titleColor);
        holder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onActionClick != null) {
                    onActionClick.onActionClick(actionItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (actionList == null || actionList.isEmpty()) {
            return 0;
        }
        return actionList.size();
    }

    public static class ActionItem {
        private String title;

        private int tag;//-1:title;-2:取消；other：action
        private int titleColor = Color.parseColor("#333333");

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public int getTitleColor() {
            return titleColor;
        }

        public void setTitleColor(int titleColor) {
            this.titleColor = titleColor;
        }

        public ActionItem(String title, int tag, int titleColor) {
            this.title = title;
            this.tag = tag;
            this.titleColor = titleColor;
        }

        public ActionItem(String title, int tag) {
            this.title = title;
            this.tag = tag;
        }

        public ActionItem() {
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View line_top, line_bottom;
        TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            line_top = itemView.findViewById(R.id.line_top);
            line_bottom = itemView.findViewById(R.id.line_bottom);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }

    public interface OnActionClickListener {
        void onActionClick(ActionItem actionItem);
    }
}
