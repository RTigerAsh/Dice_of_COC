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
        final TextView hptvm1 = (TextView)view.findViewById(R.id.help_text1);
        final TextView hptvm2 = (TextView)view.findViewById(R.id.help_text2);
        final TextView hptvm3 = (TextView)view.findViewById(R.id.help_layout_main);
        final TextView hptvm4 = (TextView)view.findViewById(R.id.help_text4);

        // 建立自定义对话框
        final HelpDialog helpDialog = new HelpDialog(this.getActivity());

        //从fragment4中跳转到规则书--RuleBookActivity页面
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),RuleBookActivity.class);
                startActivity(intent);
                Log.i("Fragment4", "onClick: 规则书点击事件响应");
            }
        });

        //长按事件显示注释
        linearLayout4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                helpDialog.setHptvm(""+hptvm4.getText());
                helpDialog.show();
                return true;
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


        //长按事件显示注释
        linearLayout3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                helpDialog.setHptvm(""+hptvm3.getText());
                helpDialog.show();
                return true;
            }
        });

        //点击linearLayout2跳转到怪物资料列表
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),MonsterActivity.class);
                startActivity(intent);
                Log.i("Fragment4", "onClick: 怪物资料列表点击事件响应");
            }
        });

        //长按事件显示注释

        linearLayout2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                helpDialog.setHptvm(""+hptvm2.getText());
                helpDialog.show();
                Log.i("Fragment4", "onLongClick: 怪物资料列表长点击事件响应");
                return true;
            }
        });

        //点击linearLayout1跳转到职业技能资料列表
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),MainSkillsActivity.class);
                startActivity(intent);
                Log.i("Fragment4", "onClick: 职业技能资料列表点击事件响应");
            }
        });

        //长按事件显示注释
        linearLayout1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                helpDialog.setHptvm(""+hptvm1.getText());
                helpDialog.show();
                return true;
            }
        });
        return view;
    }
}
