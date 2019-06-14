package cn.edu.swufe.dice_of_coc;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


public class HelpDialog extends Dialog {

    /*注释文字*/
    private TextView tvm;

    //构造方法
    public HelpDialog(Context context) {
        super(context, R.style.mdialog);
        //通过LayoutInflater获取布局
        View view = LayoutInflater.from(getContext()).
                inflate(R.layout.dialog_help_layout, null);

        tvm = view.findViewById(R.id.help_layout_main0);


        //设置显示的视图
        setContentView(view);
    }

    /**
     * 设置显示的Tips
     */

    public void setHptvm(String string) {
        tvm.setText(string);
    }

}