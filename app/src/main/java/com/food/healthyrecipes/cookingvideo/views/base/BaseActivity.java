package com.food.healthyrecipes.cookingvideo.views.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    private CompositeDisposable disposable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
        }

        ButterKnife.setDebug(true);
        unbinder = ButterKnife.bind(this);
        initPresenter();
        attachView();
        disposable = new CompositeDisposable();
        onSubscribeEventRx();
        initOnCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable.clear();
        }
        onDestroyPresenter();
    }

    protected abstract int getLayoutID();

    /**
     * Initialize Presenter
     */
    protected void initPresenter() {
    }

    /**
     * Attach View to it
     */
    protected void attachView() {
    }


    protected abstract void initOnCreate(Bundle savedInstanceState);

    /**
     * Subsrcibe event rx
     *
     * @param object
     */
    protected void onSubscribeEvent(Object object) {
    }

    /**
     * Destroy (Detach View from) Presenter. Also unsubscribes from Subscriptions
     */
    protected void onDestroyPresenter() {
    }

    private synchronized void onSubscribeEventRx() {
        disposable.add(RxBus.getInstance()
                .receive()
                .subscribeOn(Schedulers.io())
                .delay(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        onSubscribeEvent(object);
                    }
                }));
    }
}
