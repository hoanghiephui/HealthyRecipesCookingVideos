package com.food.healthyrecipes.cookingvideo.views.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jemshit.elitemvp.BaseActivity;
import com.jemshit.elitemvp.RxBus;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hoanghiep on 8/2/17.
 */

public abstract class BaseSubActivity extends BaseActivity {
    private Unbinder unbinder;

    @Override
    protected void attachView() {
        super.attachView();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroyPresenter() {
        super.onDestroyPresenter();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
