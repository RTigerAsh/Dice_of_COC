package cn.edu.swufe.dice_of_coc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BuildCardActivity extends AppCompatActivity {
    private Spinner spinner ;
    private TextView tv_STR,tv_CON,tv_SIZ,tv_DEX, tv_APP,tv_INT,tv_POW,tv_EDU,tv_Lucky;
    private TextView tv_STR_half,tv_STR_fif,
            tv_CON_half,tv_CON_fif,
            tv_SIZ_half,tv_SIZ_fif,
            tv_DEX_half,tv_DEX_fif,
            tv_APP_half,tv_APP_fif,
            tv_INT_half,tv_INT_fif,
            tv_POW_half,tv_POW_fif,
            tv_EDU_half,tv_EDU_fif;
    private Button bt_Restart,bt_Qued;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_card);

        spinner =findViewById(R.id.build_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinner,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("选择");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//通过此方法为下拉列表设置点击事件
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text= spinner.getItemAtPosition(i).toString();
                //Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        intiFirst();
        intiSecond();
        return;
    }
    private void intiFirst() {
        tv_STR=findViewById(R.id.build_STR);
        tv_CON=findViewById(R.id.build_CON);
        tv_SIZ=findViewById(R.id.build_SIZ);
        tv_DEX=findViewById(R.id.build_DEX);
        tv_APP=findViewById(R.id.build_APP);
        tv_INT=findViewById(R.id.build_INT);
        tv_POW=findViewById(R.id.build_POW);
        tv_EDU=findViewById(R.id.build_EDU);
        tv_Lucky=findViewById(R.id.build_LUCKY);

        tv_STR_half=findViewById(R.id.build_STR_half);tv_STR_fif=findViewById(R.id.build_STR_fif);
        tv_CON_half=findViewById(R.id.build_CON_half);tv_CON_fif=findViewById(R.id.build_CON_fif);
        tv_SIZ_half=findViewById(R.id.build_SIZ_half);tv_SIZ_fif=findViewById(R.id.build_SIZ_fif);
        tv_DEX_half=findViewById(R.id.build_DEX_half);tv_DEX_fif=findViewById(R.id.build_DEX_fif);
        tv_APP_half=findViewById(R.id.build_APP_half);tv_APP_fif=findViewById(R.id.build_APP_fif);
        tv_INT_half=findViewById(R.id.build_INT_half);tv_INT_fif=findViewById(R.id.build_INT_fif);
        tv_POW_half=findViewById(R.id.build_POW_half);tv_POW_fif=findViewById(R.id.build_POW_fif);
        tv_EDU_half=findViewById(R.id.build_EDU_half);tv_EDU_fif=findViewById(R.id.build_EDU_fif);

        bt_Restart=findViewById(R.id.build_button_restart);
        bt_Qued=findViewById(R.id.build_button_qued);


        bt_Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int STR,CON,SIZ,DEX,APP,INT,POW,EDU,Lucky;
                STR = 5*(3+(int)(Math.random()*6)+(int)(Math.random()*6)+(int)(Math.random()*6));
                CON = 5*(3+(int)(Math.random()*6)+(int)(Math.random()*6)+(int)(Math.random()*6));
                SIZ= 5*(8+(int)(Math.random()*6)+(int)(Math.random()*6));
                DEX = 5*(3+(int)(Math.random()*6)+(int)(Math.random()*6)+(int)(Math.random()*6));
                APP = 5*(3+(int)(Math.random()*6)+(int)(Math.random()*6)+(int)(Math.random()*6));
                INT= 5*(8+(int)(Math.random()*6)+(int)(Math.random()*6));
                POW = 5*(3+(int)(Math.random()*6)+(int)(Math.random()*6)+(int)(Math.random()*6));
                EDU= 5*(8+(int)(Math.random()*6)+(int)(Math.random()*6));
                Lucky= 5*(3+(int)(Math.random()*6)+(int)(Math.random()*6)+(int)(Math.random()*6));

                tv_STR.setText(STR+"");
                tv_CON.setText(CON+"");
                tv_SIZ.setText(SIZ+"");
                tv_DEX.setText(DEX+"");
                tv_APP.setText(APP+"");
                tv_INT.setText(INT+"");
                tv_POW.setText(POW+"");
                tv_EDU.setText(EDU+"");
                tv_Lucky.setText(Lucky+"");

                tv_STR_half.setText(STR/2+"");
                tv_STR_fif.setText(STR/5+"");
                tv_CON_half.setText(CON/2+"");
                tv_CON_fif.setText(CON/5+"");
                tv_SIZ_half.setText(SIZ/2+"");
                tv_SIZ_fif.setText(SIZ/5+"");
                tv_DEX_half.setText(DEX/2+"");
                tv_DEX_fif.setText(DEX/5+"");
                tv_APP_half.setText(APP/2+"");
                tv_APP_fif.setText(APP/5+"");
                tv_INT_half.setText(INT/2+"");
                tv_INT_fif.setText(INT/5+"");
                tv_POW_half.setText(POW/2+"");
                tv_POW_fif.setText(POW/5+"");
                tv_STR_half.setText(STR/2+"");
                tv_STR_fif.setText(STR/5+"");
                tv_EDU_half.setText(EDU/2+"");
                tv_EDU_fif.setText(EDU/5+"");


            }
        });

        bt_Qued.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void intiSecond() {

    }


}
