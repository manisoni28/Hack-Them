package net.appic.hack.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import net.appic.hack.R;
import net.appic.hack.adapters.CardStackAdapter;
import net.appic.hack.animation.RippleAnimation;
import net.appic.hack.cardstatck.cardstack.CardStack;
import net.appic.hack.cardstatck.cardstack.DefaultStackEventListener;

import java.util.List;

/**
 * Created by mani on 4/9/2016.
 */
public class FragmentChat extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Person> persons;
    private RecyclerView rv;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private WebView mWebView;
    RippleAnimation rippleBackground1, rippleBackground2;
    public static Context context;
    CardStack cardStack;
    //this class is using for swipe the AdapterView
    CardStackAdapter mCardAdapter;
    public FragmentChat() {
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
    public static FragmentChat newInstance(String param1, String param2) {
        FragmentChat fragment = new FragmentChat();
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
        View rootview = inflater.inflate(R.layout.activity_error, container, false);
        rippleBackground1 = (RippleAnimation) rootview.findViewById(R.id.content);

        //child ripple background initialization
        // rippleBackground2 = (RippleBackground) view.findViewById(R.id.content2);

        //cardStack initialization
        cardStack = (CardStack) rootview.findViewById(R.id.frame);

        //at begin setting rippleBackground visibility as VISIBLE and setting CardStack visibility as GONE
        rippleBackground1.setVisibility(View.VISIBLE);
        cardStack.setVisibility(View.GONE);

        //creating adapter
        mCardAdapter = new CardStackAdapter(getActivity().getApplicationContext(), 0);
        doRippleBackground(); //s
        return rootview;


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

    //cardStack view will set it as visible and load the information into stack.
    public void callCardStack() {

        cardStack.setVisibility(View.VISIBLE);
        rippleBackground1.setVisibility(View.GONE);

        stopAnimation();//start the ripple background animation.

        //Setting Resource of CardStack
        cardStack.setContentResource(R.layout.card_stack_item);

        //Adding 30 dummy info for CardStack
        for (int i = 0; i <= 30; i++)
            mCardAdapter.add("" + (i));
        cardStack.setAdapter(mCardAdapter);

        //Setting Listener and passing distance as a parameter ,
        //based on the distance card will discard
        //if dragging card distance would be more than specified distance(100) then card will discard or else card will reverse on same position.
        cardStack.setListener(new DefaultStackEventListener(300));

    }

}
