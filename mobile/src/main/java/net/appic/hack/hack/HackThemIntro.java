package net.appic.hack.hack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;

import net.appic.hack.activity.LandingActivity;
import net.appic.hack.layout.IntroSlide1;
import net.appic.hack.layout.IntroSlide2;
import net.appic.hack.layout.IntroSlide3;
import net.appic.hack.layout.IntroSlide4;


public class HackThemIntro extends AppIntro2 {

	@Override
	public void init(Bundle savedInstanceState) {

		/*
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("Hack Them");
		titles.add("Get Latest Hacks!!");
		titles.add("Latest Hacker News!");
		titles.add("Chat Feature!");

		ArrayList<String> description = new ArrayList<String>();
		description.add("Learn Hacking the Easy Way!!");
		description.add("Latest Hacks deleivered right in the App");
		description.add("Read and Get Notified for Latest Hacker News");
		description.add("Chat with other users and clear your doubts easily!");

		ArrayList<Integer> imgDrawables = new ArrayList<Integer>();
		imgDrawables.add(R.drawable.fire2);
		imgDrawables.add(R.drawable.hacks);
		imgDrawables.add(R.drawable.notifications);
		imgDrawables.add(R.drawable.chat);

		ArrayList<Integer> color = new ArrayList<Integer>();
		color.add(getResources().getColor(R.color.md_brown_700));
		color.add(getResources().getColor(R.color.md_deep_orange_A700));
		color.add(getResources().getColor(R.color.md_light_blue_900));
		color.add(getResources().getColor(R.color.md_teal_500));

		IntroSlide1 sl1 = new IntroSlide1();
		addSlide(sl1);
		for(int i=0;i<4;i++) {
			addSlide(AppIntroFragment.newInstance(titles.get(i), description.get(i), imgDrawables.get(i), color.get(i)));
		}
		*/
		/*
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(android.R.drawable.ic_dialog_email);
		imageView.setBackgroundColor(Color.BLACK);
		imageView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		setBackgroundView(imageView);
		*/

		//setCustomTransformer(new ZoomOutPageTransformer());

		IntroSlide1 sl1 = new IntroSlide1();
		IntroSlide2 sl2 = new IntroSlide2();
		IntroSlide3 sl3 = new IntroSlide3();
		IntroSlide4 sl4 = new IntroSlide4();
		addSlide(sl1);
		addSlide(sl2);
		addSlide(sl3);
		addSlide(sl4);

		setSlideOverAnimation();

	}

	@Override
	public void onDonePressed() {
		loadMainActivity();
	}

	@Override
	public void onNextPressed() {

	}

	@Override
	public void onSlideChanged() {

	}

	private void loadMainActivity(){
		Intent intent = new Intent(this, LandingActivity.class);
		startActivity(intent);
	}

	public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
		private static final float MIN_SCALE = 0.85f;
		private static final float MIN_ALPHA = 0.5f;

		public void transformPage(View view, float position) {
			int pageWidth = view.getWidth();
			int pageHeight = view.getHeight();

			if (position < -1) { // [-Infinity,-1)
				// This page is way off-screen to the left.
				view.setAlpha(0);

			} else if (position <= 1) { // [-1,1]
				// Modify the default slide transition to shrink the page as well
				float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
				float vertMargin = pageHeight * (1 - scaleFactor) / 2;
				float horzMargin = pageWidth * (1 - scaleFactor) / 2;
				if (position < 0) {
					view.setTranslationX(horzMargin - vertMargin / 2);
				} else {
					view.setTranslationX(-horzMargin + vertMargin / 2);
				}

				// Scale the page down (between MIN_SCALE and 1)
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);

				// Fade the page relative to its size.
				view.setAlpha(MIN_ALPHA +
					(scaleFactor - MIN_SCALE) /
						(1 - MIN_SCALE) * (1 - MIN_ALPHA));

			} else { // (1,+Infinity]
				// This page is way off-screen to the right.
				view.setAlpha(0);
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
