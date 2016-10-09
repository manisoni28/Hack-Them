

package net.appic.hack.widget;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import net.appic.hack.R;

public class ColorPickerDialogPreference extends DialogPreference {

    private SeekBar mRedSeekBar;
    private SeekBar mGreenSeekBar;
    private SeekBar mBlueSeekBar;
    private SeekBar mTransparencySeekBar;

    private int mColor;

    public ColorPickerDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mColor = WidgetProvider.STANDARD_BACKGROUND;
    }

    @Override
    protected View onCreateDialogView() {
        final View view = super.onCreateDialogView();

        view.setBackgroundColor(mColor);

        mRedSeekBar = (SeekBar) view.findViewById(R.id.seekbar_red);
        mGreenSeekBar = (SeekBar) view.findViewById(R.id.seekbar_green);
        mBlueSeekBar = (SeekBar) view.findViewById(R.id.seekbar_blue);
        mTransparencySeekBar = (SeekBar) view.findViewById(R.id.seekbar_transparency);

        int _color = mColor;

        mTransparencySeekBar.setProgress(((_color / 0x01000000) * 100) / 255);
        _color %= 0x01000000;
        mRedSeekBar.setProgress(((_color / 0x00010000) * 100) / 255);
        _color %= 0x00010000;
        mGreenSeekBar.setProgress(((_color / 0x00000100) * 100) / 255);
        _color %= 0x00000100;
        mBlueSeekBar.setProgress((_color * 100) / 255);

        OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int red = (mRedSeekBar.getProgress() * 255) / 100;

                int green = (mGreenSeekBar.getProgress() * 255) / 100;

                int blue = (mBlueSeekBar.getProgress() * 255) / 100;

                int transparency = (mTransparencySeekBar.getProgress() * 255) / 100;

                mColor = transparency * 0x01000000 + red * 0x00010000 + green * 0x00000100 + blue;
                view.setBackgroundColor(mColor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        mRedSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        mGreenSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        mBlueSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        mTransparencySeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        return view;
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            persistInt(mColor);
        }
        super.onDialogClosed(positiveResult);
    }

}
