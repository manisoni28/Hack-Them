package net.appic.hack.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.appic.hack.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroSlide3 extends Fragment {


	public IntroSlide3() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v=inflater.inflate(R.layout.fragment_intro_slide3, container, false);
		return v;
	}

}
