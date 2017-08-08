package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import java.net.MulticastSocket;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/8 0008
 * ***************************************
 */

public abstract class CacheObservable {

    public Observable<Image> getImage(final String url) {
        return Observable.create(new ObservableOnSubscribe<Image>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Image> e) throws Exception {

                if (!e.isDisposed()) {
                    Image image = getDataFromCache(url);
                    e.onNext(image);
                    e.onComplete();
                }
            }
        });
    }

    public abstract Image getDataFromCache(String url);

    public abstract void putDataToCache(Image image);

}
