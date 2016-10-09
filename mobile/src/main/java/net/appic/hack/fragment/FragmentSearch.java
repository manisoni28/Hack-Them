package net.appic.hack.fragment;

/**
 * Created by mani on 4/9/2016.
 */
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.ViewPager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.webkit.WebView;
        import android.widget.Button;

        import net.appic.hack.R;
        import net.appic.hack.activity.AddGoogleNewsActivity;
        import net.appic.hack.activity.GeneralPrefsActivity;
        import net.appic.hack.activity.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSearch extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewGroup mRoot;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private WebView mWebView;

    public FragmentSearch() {
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
    public static FragmentSearch newInstance(String param1, String param2) {
        FragmentSearch fragment = new FragmentSearch();
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
        View rootview= inflater.inflate(R.layout.fragment_search, container, false);
        mButton1 = (Button) rootview.findViewById(R.id.button_1);
        mButton2 = (Button) rootview.findViewById(R.id.button_2);
        mButton3 = (Button) rootview.findViewById(R.id.button_3);
        mButton4 = (Button) rootview.findViewById(R.id.button_4);
        mButton5 = (Button) rootview.findViewById(R.id.add);
        mButton1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                           Intent i=new Intent(getActivity(), HomeActivity.class);
                                            startActivity(i);
                                        }
                                    }
        );
        mButton2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ViewPager viewPager = (ViewPager) getActivity().findViewById(
                                                    R.id.pager);
                                            viewPager.setCurrentItem(2);
                                        }
                                    }
        );
        mButton3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ViewPager viewPager = (ViewPager) getActivity().findViewById(
                                                    R.id.pager);
                                            viewPager.setCurrentItem(0);
                                        }
                                    }
        );
        mButton4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent i=new Intent(getActivity(), GeneralPrefsActivity.class);
                                            startActivity(i);
                                        }
                                    }
        );
        mButton5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent i1=new Intent(getActivity(),AddGoogleNewsActivity.class);
                                            startActivity(i1);
                                        }
                                    }
        );
        return rootview;
    }
}
