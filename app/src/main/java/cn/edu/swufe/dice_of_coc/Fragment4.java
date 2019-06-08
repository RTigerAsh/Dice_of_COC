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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page4, container, false);

        LinearLayout linearLayout4 = view.findViewById(R.id.setting_rulebook);
        LinearLayout linearLayout3 = view.findViewById(R.id.setting_weapon);
        LinearLayout linearLayout2 = view.findViewById(R.id.setting_monster);
        LinearLayout linearLayout1 = view.findViewById(R.id.setting_skill);

        //从fragment4中跳转到规则书--RuleBookActivity页面
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),RuleBookActivity.class);
                startActivity(intent);
                Log.i("Fragment4", "onClick: 规则书点击事件响应");
            }
        });

        //点击linearLayout3跳转到武器资料列表
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),WeaponListActivity.class);
                startActivity(intent);
                Log.i("Fragment4", "onClick: 武器资料列表点击事件响应");
            }
        });

//        //长按事件显示注释
//        final HelpDialog helpDialog1 = new HelpDialog(getActivity().getApplicationContext());//建立自定义对话框
//        linearLayout3.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                TextView hptvm = view.findViewById(R.id.help_layout_main);
//
//                helpDialog1.setHptvm(""+hptvm.getText());
//                helpDialog1.show();
//                return true;
//            }
//        });

        //点击linearLayout2跳转到怪物资料列表
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),WeaponListActivity.class);
                startActivity(intent);
                Log.i("Fragment4", "onClick: 怪物资料列表点击事件响应");
            }
        });

        //点击linearLayout1跳转到职业技能资料列表
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),WeaponListActivity.class);
                startActivity(intent);
                Log.i("Fragment4", "onClick: 职业技能资料列表点击事件响应");
            }
        });
        return view;
    }
}
