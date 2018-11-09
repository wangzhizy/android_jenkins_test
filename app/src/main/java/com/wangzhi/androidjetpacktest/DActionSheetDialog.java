package com.wangzhi.androidjetpacktest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.wangzh.androidjetpacktest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用ActionSheetDialog
 */
public class DActionSheetDialog extends Dialog {
    private String titleText;
    private int titleTextColor = Color.parseColor("#333333");
    private String cancelText;
    private int cancelTextColor = Color.parseColor("#FE656A");
    private boolean cancelable = true;
    private List<ActionSheetAdapter.ActionItem> actionList;
    private RecyclerView recycler_dialog;

    private ActionSheetAdapter.OnActionClickListener onActionClickListener;
    private OnCancelListener onCancelListener;

    public DActionSheetDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_action_sheet_dialog);
        //设置window背景，默认的背景会有Padding值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //一定要在setContentView之后调用，否则无效
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        initView();
        initData();

    }

    private void initData() {
        if (actionList == null) {
            actionList = new ArrayList<>();
        }
        if (!TextUtils.isEmpty(titleText)) {
            actionList.add(0, new ActionSheetAdapter.ActionItem(titleText, -1, titleTextColor));
        }
        if (!TextUtils.isEmpty(cancelText)) {
            actionList.add(new ActionSheetAdapter.ActionItem(cancelText, -2, cancelTextColor));
        }
        recycler_dialog.setLayoutManager(new LinearLayoutManager(getContext()));
        ActionSheetAdapter actionSheetAdapter = new ActionSheetAdapter(actionList, new ActionSheetAdapter.OnActionClickListener() {
            @Override
            public void onActionClick(ActionSheetAdapter.ActionItem actionItem) {
                if (actionItem.getTag() != -1) {
                    dismiss();
                }
                if (onActionClickListener != null) {
                    onActionClickListener.onActionClick(actionItem);
                }
            }
        });
        recycler_dialog.setAdapter(actionSheetAdapter);
    }

    @Override
    public void show() {
        setCancelable(cancelable);
        setOnCancelListener(onCancelListener);
        super.show();
    }

    private void initView() {
        recycler_dialog = findViewById(R.id.recycler_dialog);
        findViewById(R.id.view_transparent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable){
                    dismiss();
                }
            }
        });
    }

    public static class Builder {

        private DActionSheetDialog alertDialog;

        public Builder(Context context) {
            alertDialog = new DActionSheetDialog(context);
        }

        public Builder setActionList(List<ActionSheetAdapter.ActionItem> value) {
            alertDialog.actionList = value;
            return this;
        }

        public Builder setTitle(String value) {
            alertDialog.titleText = value;
            return this;
        }

        public Builder setTitleTextColor(int value) {
            alertDialog.titleTextColor = value;
            return this;
        }

        public Builder setCancelText(String value) {
            alertDialog.cancelText = value;
            return this;
        }

        public Builder setCancelTextColor(int value) {
            alertDialog.cancelTextColor = value;
            return this;
        }

        public Builder setOnActionClickListener(ActionSheetAdapter.OnActionClickListener value) {
            alertDialog.onActionClickListener = value;
            return this;
        }

        public Builder setCancelable(boolean value) {
            alertDialog.cancelable = value;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener value) {
            alertDialog.onCancelListener = value;
            return this;
        }

        public DActionSheetDialog build() {
            return alertDialog;
        }

    }
}
