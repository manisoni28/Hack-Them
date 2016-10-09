package net.appic.hack.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;

import net.appic.hack.R;
import net.appic.hack.fragment.FragmentChat;
import net.appic.hack.fragment.FragmentSear;
import net.appic.hack.fragment.FragmentSearch;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class LandingActivity extends AppCompatActivity implements MaterialTabListener {
    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private MiniDrawer miniResult = null;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;
    private ViewPager mpager;
    private MaterialTabHost tas;
    public static final int TAB_SEARCH_RESULTS = 0;
    //int corresponding to our 1st tab corresponding to the Fragment where box office hits are dispalyed
    public static final int TAB_HITS = 1;
    //int corresponding to our 2nd tab corresponding to the Fragment where upcoming movies are displayed
    public static final int TAB_UPCOMING = 2;
    //int corresponding to the number of tabs in our Activity
    public static final int TAB_COUNT = 3;
    private ViewPagerAdapter mAdapter;
    public static Context context;
    private boolean mCanQuit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_sample_dark_toolbar);

        //Remove line to test RTL support
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details


        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.video)
                .withSavedInstance(savedInstanceState)
                .build();


        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDrawerLayout(R.layout.crossfade_material_drawer)
                .withHasStableIds(true)
                .withDrawerWidthDp(72)
                .withGenerateMiniDrawer(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_eighth).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_first).withIcon(MaterialDesignIconic.Icon.gmi_3d_rotation).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_ninth).withIcon(MaterialDesignIconic.Icon.gmi_select_all).withIdentifier(9),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_second).withIcon(FontAwesome.Icon.faw_bookmark).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_third).withIcon(FontAwesome.Icon.faw_gamepad).withIdentifier(4),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_fourth).withIcon(MaterialDesignIconic.Icon.gmi_code_setting).withIdentifier(5),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_fifth).withIcon(MaterialDesignIconic.Icon.gmi_star).withIdentifier(6),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_sixth).withIcon(MaterialDesignIconic.Icon.gmi_mail_send).withIdentifier(7),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_seventh).withIcon(FontAwesome.Icon.faw_github).withIdentifier(8).withSelectable(false)
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1) {
                            mpager = (ViewPager) findViewById(R.id.pager);
                            mpager.setCurrentItem(1);
                        }
                        if (drawerItem.getIdentifier() == 2) {
                            mpager = (ViewPager) findViewById(R.id.pager);
                            mpager.setCurrentItem(0);
                        }
                        if (drawerItem.getIdentifier() == 3) {
                           Intent i=new Intent(LandingActivity.this,HomeActivity.class);
                            startActivity(i);
                        }
                        if (drawerItem.getIdentifier() == 4) {
                            mpager = (ViewPager) findViewById(R.id.pager);
                            mpager.setCurrentItem(2);
                        }
                        if (drawerItem.getIdentifier() == 5) {
                            Intent i=new Intent(LandingActivity.this,GeneralPrefsActivity.class);
                            startActivity(i);
                        }
                        if (drawerItem.getIdentifier() == 6) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("market://details?id=net.appic.hack"));
                            startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 7) {
                            String text="Hello,Do you want to know hacking techniques and different hacks and wonder other people by you tricks.\nThen click on the link below to download the Awesome hacking App,HackThem:- https://play.google.com/store/apps/details?id=net.appic.hack";
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                            sendIntent.setType("text/plain");
                            startActivity(sendIntent);
                        }
                        if (drawerItem.getIdentifier() == 8) {
                            Intent i=new Intent(LandingActivity.this,AboutActivity.class);
                            startActivity(i);
                        }
                        if (drawerItem.getIdentifier() == 9) {
                            Intent i=new Intent(LandingActivity.this,AddGoogleNewsActivity.class);
                            startActivity(i);
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(false)
                .build();

        //get out our drawerLyout
        crossfadeDrawerLayout = (CrossfadeDrawerLayout) result.getDrawerLayout();

        //define maxDrawerWidth
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(this));
        //add second view (which is the miniDrawer)
        MiniDrawer miniResult = result.getMiniDrawer();
        //build the view for the MiniDrawer
        View view = miniResult.build(this);
        //set the background of the MiniDrawer as this would be transparent
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(this, com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.material_drawer_background));
        //we do not have the MiniDrawer view during CrossfadeDrawerLayout creation so we will add it here
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    result.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });

        //hook to the crossfade event
        crossfadeDrawerLayout.withCrossfadeListener(new CrossfadeDrawerLayout.CrossfadeListener() {
            @Override
            public void onCrossfade(View containerView, float currentSlidePercentage, int slideOffset) {
                //Log.e("CrossfadeDrawerLayout", "crossfade: " + currentSlidePercentage + " - " + slideOffset);
            }
        });
        mpager = (ViewPager) findViewById(R.id.pager);
        mAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        mpager.setAdapter(mAdapter);
        tas = (MaterialTabHost) findViewById(R.id.materialTabHost);
        mpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tas.setSelectedNavigationItem(position);

            }
        });
        for (int i = 0; i < mAdapter.getCount(); i++) {
            tas.addTab(
                    tas.newTab()
                            .setText(mAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        mpager.setCurrentItem(1);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        mpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int icons[] = {R.drawable.sort,
                R.drawable.sort,
                R.drawable.sort};
        String[] tabtext=getResources().getStringArray(R.array.drawer_tabs);

        FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        public Fragment getItem(int num) {
            Fragment fragment = null;
//            L.m("getItem called for " + num);
            switch (num) {
                case 1:
                    fragment = FragmentSearch.newInstance("", "");
                    break;
                case TAB_UPCOMING:
                    fragment = FragmentSear.newInstance("", "");
                    break;
                case 0:
                    fragment = FragmentChat.newInstance("", "");
                    break;

            }
            return fragment;

        }

        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabtext[position];
        }

        private Drawable getIcon(int position) {
            return getResources().getDrawable(icons[position]);
        }
    }
    @Override
    public void finish() {
        if (mCanQuit) {
            super.finish();
            return;
        }

        Toast.makeText(this, R.string.back_again_to_quit, Toast.LENGTH_SHORT).show();
        mCanQuit = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCanQuit = false;
            }
        }, 3000);
    }

}
