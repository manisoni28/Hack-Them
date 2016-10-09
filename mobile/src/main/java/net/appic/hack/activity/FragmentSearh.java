package net.appic.hack.activity;

/**
 * Created by mani on 4/10/2016.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import net.appic.hack.R;

import android.content.Intent;
import android.widget.Toast;

import com.orhanobut.simplelistview.SimpleListView;

import net.appic.hack.adapters.CardStackAdapter;
import net.appic.hack.animation.RippleAnimation;
import net.appic.hack.cardstatck.cardstack.CardStack;
import net.appic.hack.cardstatck.cardstack.DefaultStackEventListener;
import net.appic.hack.fragment.FragmentChat;
import net.appic.hack.fragment.Person;

import java.util.List;

/**
 * Created by mani on 4/9/2016.
 */
public class FragmentSearh extends Fragment {
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
    CardStack cardStack;
    //this class is using for swipe the AdapterView
    CardStackAdapter mCardAdapter;
    RippleAnimation rippleBackground1, rippleBackground2;
    private String mParam2;
    private WebView mWebView;
    private List<Person> persons;
    private RecyclerView rv;
    public FragmentSearh() {
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
    public static FragmentSearh newInstance(String param1, String param2) {
        FragmentSearh fragment = new FragmentSearh();
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview= inflater.inflate(R.layout.activity_card, container, false);
        SimpleListView listView = (SimpleListView) rootview.findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                new String[]{
                        "asdfads",
                        "Babilla",
                        "Orhan Obut",
                        "is great",
                        "awesome"
                }
        );

        listView.setOnItemClickListener(new SimpleListView.OnItemClickListener() {
            @Override
            public void onItemClick(Object item, View view, int position) {
                //
                if(position==0){
                    Toast.makeText(getActivity(), "YOOO", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getActivity(),FragmentChat.class);
                }
            }
        });
        listView.setAdapter(adapter);
        listView.setDividerView(R.layout.divider);
        //It will refresh the listview
        adapter.notifyDataSetChanged();

        return rootview;
    }
    public void callCardStack() {
        //Setting Resource of CardStack
        cardStack.setContentResource(R.layout.card_stack_item);

        //Adding 30 dummy info for CardStack
        for (int i = 0; i <= 36; i++)
            mCardAdapter.add("" + i);
        cardStack.setAdapter(mCardAdapter);

        //Setting Listener and passing distance as a parameter ,
        //based on the distance card will discard
        //if dragging card distance would be more than specified distance(100) then card will discard or else card will reverse on same position.
        cardStack.setListener(new DefaultStackEventListener(300));

    }
    public void doRippleBackground() {


        //start ripple background animations
        startAnimation();

        //handler created to handle cardStack as well as timer...
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                callCardStack();
            }
        }, 20);

    }

    //start the background ripple animation...
    private void startAnimation() {
        //if it's not running
        if (!rippleBackground1.isRippleAnimationRunning()) {
            rippleBackground1.startRippleAnimation();//start root ripple animation
            // rippleBackground2.startRippleAnimation();//start child ripple animation
        }
    }

    //this method will stop background ripple animation. if it's running.
    private void stopAnimation() {
        if (rippleBackground1.isRippleAnimationRunning()) {
            rippleBackground1.stopRippleAnimation();
            // rippleBackground2.stopRippleAnimation();
        }
    }
}
