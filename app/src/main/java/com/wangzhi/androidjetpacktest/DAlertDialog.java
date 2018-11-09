package com.wangzhi.androidjetpacktest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.wangzh.androidjetpacktest.R;

import java.math.BigDecimal;

/**
 * 通用AlertDialog
 * 支持设置Title
 * 支持设置Message
 * 支持设置多按钮。按钮文字颜色
 * 支持设置单按钮，单按钮需使用okText、否则左边会多出一根线
 * 支持设置按钮点击事件
 * 支持设置设置宽度：<=1时为dialog宽度的百分比，>1时为具体宽度
 * 支持设置是否可点击外部区域关闭
 * 支出设置关闭监听事件
 */
public class DAlertDialog extends Dialog {
    private String mTitle;
    private String mMessage;
    private String mLeftText;
    private String mOkText;
    private int mLeftTextColor = Color.parseColor("#999999");
    private int mOKTextColor = Color.parseColor("#0099FF");
    private boolean cancelable = true;
    private Double width = 0.8;

    private TextView tv_title;
    private TextView tv_message;
    private TextView tv_left;
    private View line_bottom;
    private TextView tv_ok;

    private OnClickListener onLeftClickListener;
    private OnClickListener onOkClickListener;
    private OnCancelListener onCancelListener;

    private DAlertDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_alert_dialog);
        if (width == 1) {
            //设置window背景，默认的背景会有Padding值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //一定要在setContentView之后调用，否则无效
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        initView();
        initData();

    }

    private void initData() {
        if (!TextUtils.isEmpty(mTitle)) {
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(mTitle);
        }
        if (!TextUtils.isEmpty(mMessage)) {
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText(mMessage);
        }
        if (!TextUtils.isEmpty(mMessage)) {
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText(mMessage);
        }
        if (!TextUtils.isEmpty(mLeftText)) {
            tv_left.setVisibility(View.VISIBLE);
            line_bottom.setVisibility(View.VISIBLE);
            tv_left.setText(mLeftText);
            tv_left.setTextColor(mLeftTextColor);
            tv_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (onLeftClickListener != null) {
                        onLeftClickListener.onClick(DAlertDialog.this, 0);
                    }
                }
            });
        }
        if (!TextUtils.isEmpty(mOkText)) {
            tv_ok.setVisibility(View.VISIBLE);
            tv_ok.setText(mOkText);
            tv_ok.setTextColor(mOKTextColor);
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (onOkClickListener != null) {
                        onOkClickListener.onClick(DAlertDialog.this, 1);
                    }
                }
            });
        }
    }

    @Override
    public void show() {
        setCancelable(cancelable);
        setOnCancelListener(onCancelListener);
        super.show();
        if (width == 1) {
            return;
        }
        if (width < 1) {
            width = BigDecimal.valueOf(1080 * 3).
                    multiply(BigDecimal.valueOf(width)).doubleValue();
        }
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width.intValue(); //设置宽度
        getWindow().setAttributes(lp);
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_message = findViewById(R.id.tv_message);
        tv_left = findViewById(R.id.tv_left);
        line_bottom = findViewById(R.id.line_bottom);
        tv_ok = findViewById(R.id.tv_ok);
    }

    public static class Builder {

        private DAlertDialog alertDialog;

        public Builder(Context context) {
            alertDialog = new DAlertDialog(context);
        }

        public Builder setTitle(String value) {
            alertDialog.mTitle = value;
            return this;
        }

        public Builder setMessage(String value) {
            alertDialog.mMessage = value;
            return this;
        }

        public Builder setLeftText(String value) {
            alertDialog.mLeftText = value;
            return this;
        }

        public Builder setLeftTextColor(int value) {
            alertDialog.mLeftTextColor = value;
            return this;
        }

        public Builder setOkText(String value) {
            alertDialog.mOkText = value;
            return this;
        }

        public Builder setOkTextColor(int value) {
            alertDialog.mOKTextColor = value;
            return this;
        }

        public Builder setCancelable(boolean value) {
            alertDialog.cancelable = value;
            return this;
        }


        public Builder setWidth(Double value) {
            alertDialog.width = value;
            return this;
        }

        public Builder setOnLeftClickListener(OnClickListener value) {
            alertDialog.onLeftClickListener = value;
            return this;
        }

        public Builder setOnOkClickListener(OnClickListener value) {
            alertDialog.onOkClickListener = value;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener value) {
            alertDialog.onCancelListener = value;
            return this;
        }

        public DAlertDialog build() {
            return alertDialog;
        }

    }
}
