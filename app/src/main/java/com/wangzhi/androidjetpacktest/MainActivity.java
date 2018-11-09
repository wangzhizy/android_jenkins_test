package com.wangzhi.androidjetpacktest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wangzh.androidjetpacktest.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_databinding:
//                startActivity(new Intent(this, DataBindingTestActivity.class));
                new DAlertDialog.Builder(this)
                        .setWidth(1.0)
                        .setTitle("友情提示")
                        .setMessage("友情提示")
                        .setOkText("立即签约")
                        .build().show();
                break;
            case R.id.btn_databindin:
//                startActivity(new Intent(this, DataBindingTestActivity.class));
                List<ActionSheetAdapter.ActionItem> actionItems = new ArrayList<>();
                actionItems.add(new ActionSheetAdapter.ActionItem("确定", 1));
                new DActionSheetDialog.Builder(this)
                        .setTitle("您确认要退出登录么？")
                        .setCancelText("取消")
                        .setActionList(actionItems)
                        .setOnActionClickListener(new ActionSheetAdapter.OnActionClickListener() {
                            @Override
                            public void onActionClick(ActionSheetAdapter.ActionItem actionItem) {
                                Toast.makeText(MainActivity.this, actionItem.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build().show();
                break;
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}
