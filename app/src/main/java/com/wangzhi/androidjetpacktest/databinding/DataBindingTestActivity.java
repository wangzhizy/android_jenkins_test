package com.wangzhi.androidjetpacktest.databinding;

import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wangzh.androidjetpacktest.R;
import com.wangzh.androidjetpacktest.databinding.ActivityDataBindingTestBinding;
import com.wangzhi.androidjetpacktest.databinding.model.UserModel;

public class DataBindingTestActivity extends AppCompatActivity {
    @BindingMethods({
            @BindingMethod(type = TextView.class,
                    attribute = "tt",
                    method = "setText"),
    })
    public class ImageViewBindingAdapter {
    }

    @BindingAdapter("adapterTt")
    public static void setText(TextView textView, String text) {
        textView.setText("adapterTt:" + text);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View inflate = getLayoutInflater().inflate(R.layout.activity_data_binding_test, null);
        setContentView(inflate);
        ActivityDataBindingTestBinding dataBindingTestBinding;
        //dataBindingTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_test);

        dataBindingTestBinding = ActivityDataBindingTestBinding.bind(inflate);
        dataBindingTestBinding.setLifecycleOwner(this);


        UserModel userModel = ViewModelProviders.of(this).get(UserModel.class);
        userModel.setAge("100");
        dataBindingTestBinding.setUser(userModel);
        dataBindingTestBinding.executePendingBindings();

    }

    public void cc(View view) {
//        userModel.getListenerBindingSwitch().set(!userModel.getListenerBindingSwitch().get());
    }
}
