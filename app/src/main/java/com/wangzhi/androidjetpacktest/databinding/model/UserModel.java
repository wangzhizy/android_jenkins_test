package com.wangzhi.androidjetpacktest.databinding.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    private MutableLiveData<String> name;
    private String age;
    private boolean listenerBindingSwitch = false;
//    private ObservableField<Boolean> listenerBindingSwitch = new ObservableField<>(true);


    public UserModel() {
    }

    public String getName() {
        if (name == null) {
            name = new MutableLiveData<String>();
        }
        return name.getValue() + System.currentTimeMillis();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    //    public ObservableField<Boolean> getListenerBindingSwitch() {
//        return listenerBindingSwitch;
//    }
//
//    public void setListenerBindingSwitch(ObservableField<Boolean> listenerBindingSwitch) {
//        this.listenerBindingSwitch = listenerBindingSwitch;
//    }

    public boolean isListenerBindingSwitch() {
        return listenerBindingSwitch;
    }

    public void setListenerBindingSwitch(boolean listenerBindingSwitch) {
        this.listenerBindingSwitch = listenerBindingSwitch;
    }
}
