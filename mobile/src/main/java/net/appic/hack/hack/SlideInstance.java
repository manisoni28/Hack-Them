package net.appic.hack.hack;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mani on 4/16/2016.
 */
public class SlideInstance extends Fragment {
	private static final String ARG_LAYOUT_RES_ID = "layoutResId";

	public static SlideInstance newInstance(int layoutResId) {
		SlideInstance   slideInstance = new SlideInstance();

		Bundle args = new Bundle();
		args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
		slideInstance.setArguments(args);

		return slideInstance;
	}

	private int layoutResId;

	public SlideInstance() {}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID))
			layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(layoutResId, container, false);
	}
}
