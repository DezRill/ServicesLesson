package com.dezrill.serviceslesson;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Data extends BaseObservable {
    private String inTxt1, inTxt2, inTxt3;

    public void setInTxt1(String inTxt1) {
        this.inTxt1=inTxt1;
        notifyPropertyChanged(BR.inTxt1);
    }

    public void setInTxt2(String inTxt2) {
            this.inTxt2=inTxt2;
            notifyPropertyChanged(BR.inTxt2);
    }

    public void setInTxt3(String inTxt3) {
        this.inTxt3=inTxt3;
        notifyPropertyChanged(BR.inTxt3);
    }

    @Bindable
    public String getInTxt1() {
        return inTxt1;
    }

    @Bindable
    public String getInTxt2() {
        return inTxt2;
    }

    @Bindable
    public String getInTxt3() {
        return inTxt3;
    }
}
