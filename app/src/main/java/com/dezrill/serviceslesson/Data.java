package com.dezrill.serviceslesson;

public class Data {
    private ObservableString inTxt1, inTxt2, inTxt3;

    public Data() {
        inTxt1=new ObservableString();
        inTxt2=new ObservableString();
        inTxt3=new ObservableString();
    }

    public void setInTxt1(String inTxt1) {
        this.inTxt1.set(inTxt1);
    }

    public void setInTxt2(String inTxt2) {
            this.inTxt2.set(inTxt2);
    }

    public void setInTxt3(String inTxt3) {
        this.inTxt3.set(inTxt3);
    }

    public String getInTxt1() {
        return inTxt1.get();
    }

    public String getInTxt2() {
        return inTxt2.get();
    }

    public String getInTxt3() {
        return inTxt3.get();
    }
}
