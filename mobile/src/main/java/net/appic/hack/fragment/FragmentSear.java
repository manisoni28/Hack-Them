package net.appic.hack.fragment;

/**
 * Created by mani on 4/10/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import net.appic.hack.R;

import android.webkit.WebViewClient;

/**
 * Created by mani on 4/9/2016.
 */
public class FragmentSear extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewGroup mRoot;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    WebView myview;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private WebView mWebView;

    public FragmentSear() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSear newInstance(String param1, String param2) {
        FragmentSear fragment = new FragmentSear();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.webv, container, false);
        myview=(WebView)rootview.findViewById(R.id.tutoriallayoutwebView1);
        myview.getSettings().setJavaScriptEnabled(true);
        myview.getSettings().setBuiltInZoomControls(true);
        myview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        myview.setScrollbarFadingEnabled(false);
        final Activity MyActivity = getActivity();
        myview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                MyActivity.setTitle("Loading...");
                MyActivity.setProgress(progress * 100);

                if (progress == 100)
                    MyActivity.setTitle(R.string.app_name);
            }
        });
        myview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    hideErrorPage(view);
            }
            private void hideErrorPage(WebView view) {
                // Here we configurating our custom error page
                // It will be blank
                view.loadDataWithBaseURL("file:///android_asset/", "<html>\n" +
                        "<body bgcolor=\"white\">\n" +
                        "    <table width=\"100%\" height=\"100%\">\n" +
                        "        <tr>\n" +
                        "            <td align=\"center\" valign=\"center\">\n" +
                        "                <img src=\"error.png\">\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "</body>", "text/html", "utf-8", "");
            }
        });
        myview.loadUrl("http://chatbutton.com/chatroom/18377914");
        myview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });
        return rootview;
    }


}
