package vision.madhvi.tutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tutorial.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Vision_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Vision_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<Vision_Modal> product;
    View v;

    public Home_Vision_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Vision_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Vision_Fragment newInstance(String param1, String param2) {
        Home_Vision_Fragment fragment = new Home_Vision_Fragment();
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
        v = inflater.inflate(R.layout.fragment_home__vision_, container, false);
        recyclerView = v.findViewById(R.id.Rv_Home_Vision);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        product=new ArrayList<>();
        product.add(new Vision_Modal(R.drawable.homescrren, "Career advancement and hobbies", "Career advancement and hobbies"+
                "    Studying online more flexibility.you can worke and fit your work schedule(and your hobbies) around"  +
                "     your course worke more easily; even more so if you are taking an asychronous class: an online class where" +
                "    you dont have to log in at spicific time for a live session" +
                "    your course worke more easily; even more so if you are taking an asychronous class: an online class where" +
                "  you dont have to log in at spicific time for a live session "));
        product.add(new Vision_Modal(R.drawable.homescrren, "Career advancement and hobbies", "Career advancement and hobbies"+
                "  Studying online more flexibility.you can worke and fit your work schedule(and your hobbies) around"  +
                "   your course worke more easily; even more so if you are taking an asychronous class: an online class where" +
                "  you dont have to log in at spicific time for a live session" +
                "  your course worke more easily; even more so if you are taking an asychronous class: an online class where" +
                " you dont have to log in at spicific time for a live session "));
        Vision_Adapter adapter=new Vision_Adapter(getContext(),product);
        recyclerView.setAdapter(adapter);
        return v;
    }
}