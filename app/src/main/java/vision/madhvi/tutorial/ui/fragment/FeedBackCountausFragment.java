package vision.madhvi.tutorial.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import vision.madhvi.tutorial.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedBackCountausFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedBackCountausFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tvphone,tvemail,tvaddress;
    String temail,taddress;
    public FeedBackCountausFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedBackCountausFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedBackCountausFragment newInstance(String param1, String param2) {
        FeedBackCountausFragment fragment = new FeedBackCountausFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_feed_back_countaus, container, false);

        tvemail=v.findViewById(R.id.TvContactE);
        tvphone=v.findViewById(R.id.TvContactP);

        tvaddress=v.findViewById(R.id.TvContactA);
        temail=tvemail.getText().toString();
        taddress=tvaddress.getText().toString();
        // email click
        tvemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               sendMail();

            }
        });

        // phone click

        tvphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7490967163"));
                startActivity(intent);
            }
        });


        // click address
        tvaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DisplayTrack("","Madhvi Shiksha Samiti");
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//               intent.setData(Uri.parse("geo:26.996285399999998 75.87675999999999"));
//                //intent.setData(Uri.parse("geo:26.9854865 75.8513454"));
//
//                Intent.createChooser(intent,"Choose Maps");
//
//                startActivity(intent);
            }
        });
        return v;
    }
     // display google map
    private void DisplayTrack(String sSource,String sDestination){

        try {

            Uri uri=Uri.parse("https://www.google.co.in/maps/dir/"  + "/" + sDestination);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }catch (ActivityNotFoundException e){

            Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }

    }

    // send email
    public void sendMail(){
        String recipinList=temail;
        String[]recipients=recipinList.split(",");

        String subject="Madhvi Vision ";
        String message="This is message";

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("message/rfc822");
        if (intent.resolveActivity(getActivity().getPackageManager())!=null){
            //startActivity(intent);
            startActivity(Intent.createChooser(intent,"Choose email"));

        } else {
            Toast.makeText(getContext(), "Not support action", Toast.LENGTH_SHORT).show();
        }
    }
}