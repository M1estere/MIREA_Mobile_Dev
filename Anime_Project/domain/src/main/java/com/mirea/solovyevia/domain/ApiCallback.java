package com.mirea.solovyevia.domain;

public interface ApiCallback<T> {

    public void onSuccess(T result);
    public void onFailure(Exception e);

}
