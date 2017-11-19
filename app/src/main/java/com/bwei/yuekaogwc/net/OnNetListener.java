package com.bwei.yuekaogwc.net;



public interface OnNetListener<T> {
    public void Onfailure(Exception e);
    public void OnSuccess(T t);

}
