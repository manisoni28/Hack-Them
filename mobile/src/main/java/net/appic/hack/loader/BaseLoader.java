package net.appic.hack.loader;


import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Loader which extends AsyncTaskLoaders and handles caveats as pointed out in http://code.google.com/p/android/issues/detail?id=14944.
 * <p/>
 * Based on CursorLoader.java in the Fragment compatibility package
 *
 * @param <D> data type
 * @author Alexander Blom (me@alexanderblom.se)
 */
public abstract class BaseLoader<D> extends AsyncTaskLoader<D> {
    private D mData;

    protected BaseLoader(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(D data) {
        if (isReset()) {
            // An async query came in while the loader is stopped
            return;
        }

        mData = data;

        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }

        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        mData = null;
    }
}