package cn.edu.swufe.dice_of_coc;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import cn.edu.swufe.dice_of_coc.loopview.LoopView;
import cn.edu.swufe.dice_of_coc.loopview.OnItemSelectedListener;


public class DiceDialog extends Dialog {

    private LoopView loopView_year;
    private LoopView loopView_day;
    private TextView datatv;
    private TextView dataCounttv;
    private ArrayList<String> list_times = new ArrayList<String>();
    private ArrayList<String> list_end = new ArrayList<String>();
    private Button dice_button ;

    int times;
    int endnum;
    int count = 1;
    int a;


    //构造方法
    public DiceDialog(Context context) {
        super(context, R.style.mdialog);
        //通过LayoutInflater获取布局
        View view = LayoutInflater.from(getContext()).
                inflate(R.layout.dialog_dice, null);

        //设置显示的视图
        setContentView(view);

        //初始化
        a =1;
        initView();
        initData();
        initEvent();


    }

    private void initView() {
        loopView_year = (LoopView) findViewById(R.id.loopView_year);
        loopView_day = (LoopView) findViewById(R.id.loopView_day);
        datatv = (TextView)findViewById(R.id.dice_data);
        dataCounttv= (TextView)findViewById(R.id.dice_datacount);

        dice_button = (Button) findViewById(R.id.dice_button);
        dice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fin = 0 ;

                for (int i = 1; i <= times; i++){
                    int ed = 1+(int)(Math.random()*endnum);
                    fin=fin+ed;
                }
                String datastr =fin+"";

                if(count == 1&&a == 1){
                    datatv.setTextSize(50);
                    a++;
                    Log.i("onClick", "用于第一次启动dialog的参数a--> "+a);
                }
                datatv.setText(datastr);
                dataCounttv.setText("点击次数："+count);
                count++;
            }
        });

    }

    private void initData() {
        //设置是否不循环播放
//        loopView_year.setNotLoop();
        times = 1;
        endnum = 3;

        //复投次数
        for (int i = 0; i < 8; i++) {
            list_times.add("" + i);
        }
        //设置原始数据
        loopView_year.setItems(list_times);
        loopView_year.setCurrentPosition(1);
//        for (int i = 0; i < list_times.size(); i++) {
//            if (Integer.parseInt(list_times.get(i)) == 6) {
//                loopView_year.setCurrentPosition(i);
//            }
//        }



        //骰子截止数值

        list_end.add("2");
        list_end.add("3");
        list_end.add("4");
        list_end.add("6");
        list_end.add("10");
        list_end.add("20");
        list_end.add("100");

        //设置原始数据
        loopView_day.setItems(list_end);
        loopView_day.setCurrentPosition(1);


    }

    private void initEvent() {
        //滚动监听
        loopView_year.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int index) {
                times = Integer.parseInt(list_times.get(index));
            }

  });

        loopView_day.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int index) {
                endnum = Integer.parseInt(list_end.get(index));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        dataCounttv.setText("点击次数：0");datatv.setText("0");
        loopView_year.setCurrentPosition(1);
        loopView_day.setCurrentPosition(1);
        count = 1;
        times=1;
    }


}