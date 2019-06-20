package cn.edu.swufe.dice_of_coc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page2, container, false);
        Button bt = (Button) view.findViewById(R.id.bt);
        ImageView imageView = view.findViewById(R.id.page2_img);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "页面加载中，这可能需要3S", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(),BuildCardActivity.class);
                startActivity(intent);

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "页面加载中，这可能需要3S", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(),BuildCardActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
