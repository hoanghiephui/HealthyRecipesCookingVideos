package com.food.healthyrecipes.cookingvideo.views.activitys;

import android.os.Bundle;

import com.food.healthyrecipes.cookingvideo.R;
import com.food.healthyrecipes.cookingvideo.views.base.BaseSubActivity;
import com.jemshit.elitemvp.utils.LauncherUtilities;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hoanghiep on 8/2/17.
 */

public class SplashActivity extends BaseSubActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onNext("");
                e.onComplete();
            }
        }).delay(3000, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        LauncherUtilities.openActivity(SplashActivity.this, WelcomeActivity.class);
                    }
                });
    }
}
