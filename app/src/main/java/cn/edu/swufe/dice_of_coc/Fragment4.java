package cn.edu.swufe.dice_of_coc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page4, container, false);
        LinearLayout linearLayout4 = view.findViewById(R.id.setting_rulebook);
        //从fragment4中跳转到RuleBookActivity页面
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),RuleBookActivity.class);
                startActivity(intent);
            }
        });

//        Button bt = (Button) view.findViewById(R.id.bt);
////        bt.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(getActivity(), "Fragment4上的按钮被点击了", Toast.LENGTH_SHORT).show();
////            }
////        });
        return view;
    }
}
