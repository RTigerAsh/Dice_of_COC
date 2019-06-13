package cn.edu.swufe.dice_of_coc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.swufe.dice_of_coc.loopview.LoopView;
import cn.edu.swufe.dice_of_coc.loopview.OnItemSelectedListener;

import java.util.ArrayList;

/**
 * 时间选择页面
 */
public class DiceActivity extends Activity {

    LoopView loopView_year;
    LoopView loopView_day;
    TextView datatv;
    ArrayList<String> list_times = new ArrayList<String>();
    ArrayList<String> list_end = new ArrayList<String>();

    int times;
    int endnum;
    int data = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        initView();
        initData();
        initEvent();


    }

    private void initView() {
        loopView_year = (LoopView) findViewById(R.id.loopView_year);
        loopView_day = (LoopView) findViewById(R.id.loopView_day);
        datatv = (TextView)findViewById(R.id.dice_data);

    }

    private void initData() {
        //设置是否不循环播放
//        loopView_year.setNotLoop();
        times = 1;
        endnum = 3;

        //复投次数
        for (int i = 0; i < 6; i++) {
            list_times.add("" + i);
        }
        //设置原始数据
        loopView_year.setItems(list_times);
        for (int i = 0; i < list_times.size(); i++) {
            if (Integer.parseInt(list_times.get(i)) == 1) {
                loopView_year.setCurrentPosition(i);
            }
        }



        //骰子截止数值
        for (int i = 1; i < 101; i++) {
            list_end.add("" + i);
        }
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


    public void selectData(View view) {
        String datastr = (times*(1+(int)(Math.random()*endnum)))+"";
        datatv.setText(datastr);
        //Toast.makeText(this, "你选中的复投次数是：" + times + "截止：" + endnum , Toast.LENGTH_SHORT).show();
    }


}