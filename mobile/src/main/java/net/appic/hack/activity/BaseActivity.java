
package net.appic.hack.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import net.appic.hack.Constants;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String STATE_IS_NORMAL_FULLSCREEN = "STATE_IS_NORMAL_FULLSCREEN";
    private static final String STATE_IS_IMMERSIVE_FULLSCREEN = "STATE_IS_IMMERSIVE_FULLSCREEN";
    private boolean mIsNormalFullScreen, mIsImmersiveFullScreen;
    private View mDecorView;
    private OnFullScreenListener mOnFullScreenListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDecorView = getWindow().getDecorView();

        // For immersive mode
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            mDecorView.setOnSystemUiVisibilityChangeListener
                    (new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) { // We are not in fullscreen mode

                                if (mIsImmersiveFullScreen) { // It was fullscreen, we need to change it
                                    setImmersiveFullScreen(false);
                                    mIsImmersiveFullScreen = false;

                                    if (mOnFullScreenListener != null) {
                                        mOnFullScreenListener.onFullScreenDisabled();
                                    }
                                }
                            } else { // We are now in fullscreen mode
                                if (!mIsImmersiveFullScreen) { // It was not-fullscreen, we need to change it
                                    mIsImmersiveFullScreen = true;

                                    if (mOnFullScreenListener != null) {
                                        mOnFullScreenListener.onFullScreenEnabled(true, false);
                                    }
                                }
                            }
                        }
                    });
        }
        if (android.os.Build.VERSION.SDK_INT >= 21) { // Lollipop
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Tweak to allow setting status bar color
        }
    }

    @Override
    protected void onResume() {
        if (Constants.NOTIF_MGR != null) {
            Constants.NOTIF_MGR.cancel(0);
        }

        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_IS_NORMAL_FULLSCREEN, mIsNormalFullScreen);
        outState.putBoolean(STATE_IS_IMMERSIVE_FULLSCREEN, mIsImmersiveFullScreen);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.getBoolean(STATE_IS_IMMERSIVE_FULLSCREEN)) {
            setImmersiveFullScreen(true);
        } else if (savedInstanceState.getBoolean(STATE_IS_NORMAL_FULLSCREEN)) {
            setNormalFullScreen(true);
        }

        super.onRestoreInstanceState(savedInstanceState);
    }

    public void setOnFullscreenListener(OnFullScreenListener listener) {
        mOnFullScreenListener = listener;
    }

    public boolean isFullScreen() {
        return mIsNormalFullScreen || mIsImmersiveFullScreen;
    }

    public boolean isNormalFullScreen() {
        return mIsNormalFullScreen;
    }

    public void setNormalFullScreen(boolean fullScreen) {
        setNormalFullScreen(fullScreen, false);
    }

    public boolean isImmersiveFullScreen() {
        return mIsImmersiveFullScreen;
    }

    @SuppressLint("InlinedApi")
    public void setImmersiveFullScreen(boolean fullScreen) {
        if (fullScreen) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().hide();
                }
                mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            } else {
                setNormalFullScreen(true, true);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().show();
                }
                mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            } else {
                setNormalFullScreen(false, true);
            }
        }
    }

    private void setNormalFullScreen(boolean fullScreen, boolean isImmersiveFallback) {
        if (fullScreen) {
            mIsNormalFullScreen = true;

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

            if (mOnFullScreenListener != null) {
                mOnFullScreenListener.onFullScreenEnabled(false, isImmersiveFallback);
            }
        } else {
            mIsNormalFullScreen = false;

            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            if (mOnFullScreenListener != null) {
                mOnFullScreenListener.onFullScreenDisabled();
            }
        }
    }

    public interface OnFullScreenListener {
        void onFullScreenEnabled(boolean isImmersive, boolean isImmersiveFallback);

        void onFullScreenDisabled();
    }
}
