

package net.appic.hack.utils;

import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;

public abstract class ThrottledContentObserver extends ContentObserver {

    private final long mUpdateThrottle;
    private final Handler mHandler;
    private Runnable mOnChangeTask = null;

    public ThrottledContentObserver(Handler handler, long delayMS) {
        super(handler);
        mUpdateThrottle = delayMS;
        mHandler = handler;
    }

    @Override
    public void onChange(final boolean selfChange) {
        if (mOnChangeTask == null) {
            mOnChangeTask = new Runnable() {
                @Override
                public void run() {
                    onChangeThrottled();
                    mOnChangeTask = null;
                }
            };

            long now = SystemClock.uptimeMillis();
            mHandler.postAtTime(mOnChangeTask, now + mUpdateThrottle);
        }
        super.onChange(selfChange);
    }

    abstract public void onChangeThrottled();
}
