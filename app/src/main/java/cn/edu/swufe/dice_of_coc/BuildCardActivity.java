package cn.edu.swufe.dice_of_coc;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BuildCardActivity extends AppCompatActivity {
    private TextView tv_STR,tv_CON,tv_SIZ,tv_DEX, tv_APP,tv_INT,tv_POW,tv_EDU,tv_Lucky;
    private TextView tv_STR_half,tv_STR_fif,
            tv_CON_half,tv_CON_fif,
            tv_SIZ_half,tv_SIZ_fif,
            tv_DEX_half,tv_DEX_fif,
            tv_APP_half,tv_APP_fif,
            tv_INT_half,tv_INT_fif,
            tv_POW_half,tv_POW_fif,
            tv_EDU_half,tv_EDU_fif;
    private EditText ed_AGE,ed_NAME,ed_XINGBIE,ed_ZHUDI,ed_guxiang;

    public int STR=0,CON=0,SIZ=0,DEX=0,APP=0,INT=0,POW=0,EDU=0,Lucky=0;

    private LinearLayout buttonlayout ;
    private LinearLayout mainAt ;
    private LinearLayout biao2_3;
    private LinearLayout layout_qued1;
    private Spinner spinner;

    private Button bt_Restart,bt_Qued,bt_Qued1,bt_FinishBuild;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_card);

        buttonlayout = findViewById(R.id.build_button_layout);
        layout_qued1= findViewById(R.id.layout_qued1);
        mainAt = findViewById(R.id.layout_main_Attributes);
        biao2_3 = findViewById(R.id.table_2_3);
        spinner = findViewById(R.id.build_spinner_shidai);



        List<CellDataContainer> cellDataContainerList = null;
        try {
            cellDataContainerList = readDataFromExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException ei){
            ei.printStackTrace();
        }
        List<CellDataContainer2> cellDataContainerList2 = null;
        try {
            cellDataContainerList2 = readDataFromExcel2();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException ei){
            ei.printStackTrace();
        }

        writeDataToSQLite(cellDataContainerList);
        writeDataToSQLite2(cellDataContainerList2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.times,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("选择");

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                intiFirst();

            }
        });
        thread.start();
        intiSecond();
        intiThird();
        intiFour();

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==2){
                    mainAt.setVisibility(View.VISIBLE);
                    buttonlayout.setVisibility(View.GONE);
                    ed_AGE.setEnabled(false);
                    Log.i("handleMessage", " msg.what="+msg.what);
                }else if (msg.what==1){
                    spinner.setEnabled(false);
                    ed_NAME.setEnabled(false);
                    ed_XINGBIE.setEnabled(false);
                    ed_ZHUDI.setEnabled(false);
                    ed_guxiang.setEnabled(false);
                    layout_qued1.setVisibility(View.GONE);
                }else if(msg.what==3){
                    biao2_3.setVisibility(View.VISIBLE);}
                super.handleMessage(msg);
            }
        };

    }



    private void intiFirst(){
        tv_STR=findViewById(R.id.build_STR);
        tv_CON=findViewById(R.id.build_CON);
        tv_SIZ=findViewById(R.id.build_SIZ);
        tv_DEX=findViewById(R.id.build_DEX);
        tv_APP=findViewById(R.id.build_APP);
        tv_INT=findViewById(R.id.build_INT);
        tv_POW=findViewById(R.id.build_POW);
        tv_EDU=findViewById(R.id.build_EDU);
        tv_Lucky=findViewById(R.id.build_LUCKY);

        ed_AGE=findViewById(R.id.build_AGE);
        ed_NAME=findViewById(R.id.build_Name);
        ed_XINGBIE=findViewById(R.id.build_xingbie);
        ed_ZHUDI =findViewById(R.id.build_zhudi);
        ed_guxiang=findViewById(R.id.build_guxiang);;

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
        bt_Qued1=findViewById(R.id.button_qued1);

        bt_Qued1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(BuildCardActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定之后数据无法修改");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //确定按钮
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Message msg = new Message();
                        msg.what = 1;
                        Log.i("OnClick", "bt_Qued1.setOnClickListener msg.what" + msg.what);
                        handler.sendMessage(msg);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
                AlertDialog b=builder.create();
                b.show();
            }
        });
        ed_AGE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()==false){

                    int value =Integer.valueOf(s.toString());
                    Log.i("onTextChanged", "onTextChanged: "+value);
                    if (value<0){
                        ed_AGE.setText("15");
                    }else if (value>=0&&value<15){
                        Toast.makeText(BuildCardActivity.this,"建议年龄不能小于15", Toast.LENGTH_SHORT).show();
                    }else if (value>90){
                        ed_AGE.setText("90");
                        Toast.makeText(BuildCardActivity.this,"建议年龄不能大于90", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        bt_Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final int STR,CON,SIZ,DEX,APP,INT,POW,EDU,Lucky;
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
                AlertDialog.Builder builder=new AlertDialog.Builder(BuildCardActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定之后数据无法修改");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //确定按钮
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        run();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                AlertDialog b=builder.create();
                b.show();

            }
        });

    }

    public void run() {
        String s = ed_AGE.getText().toString().trim();
        if (!s.equals("")) {

//                            LinearLayout buttonlayout = findViewById(R.id.build_button_layout);
//                            LinearLayout mainAt = findViewById(R.id.layout_main_Attributes);
//                            LinearLayout biao2_3 = findViewById(R.id.table_2_3);
            TextView tv_DB, tv_TX, tv_SAN, tv_MOV;
            tv_DB = findViewById(R.id.text_DB);
            tv_TX = findViewById(R.id.text_TX);
            tv_SAN = findViewById(R.id.text_SAN);
            tv_MOV = findViewById(R.id.text_MOV);

            int MOV = 0;
            if (DEX < SIZ && STR < SIZ) {
                MOV = 7;
            } else if (DEX <= SIZ || STR <= SIZ) {
                MOV = 8;
            } else if (DEX > SIZ || STR > SIZ) {
                MOV = 9;
            }

//                            mainAt.setVisibility(View.VISIBLE);
//                            buttonlayout.setVisibility(View.GONE);
//                            biao2_3.setVisibility(View.VISIBLE);

            int value = 0;
            value = Integer.parseInt(ed_AGE.getText().toString());
            int a = 0;

            if (15 <= value && value <= 19) {
                a = 1;
            } else if (20 <= value && value <= 39) {
                a = 2;
            } else if (40 <= value && value <= 49) {
                a = 3;
            } else if (50 <= value && value <= 59) {
                a = 4;
            } else if (60 <= value && value <= 69) {
                a = 5;
            } else if (70 <= value && value <= 79) {
                a = 6;
            } else if (80 <= value && value <= 89) {
                a = 7;
            }

            switch (a) { //                15-19 岁：力量和体型各减５点。教育减５点。
                case 1:

                    STR = STR - 5;
                    SIZ = SIZ - 5;
                    EDU = EDU - 5;
                    if (((int) (Math.random() * 100) + 1) > EDU) {
                        int eduint = (int) (Math.random() * 10) + 1;
                        EDU = EDU + eduint;
                        Looper.prepare();
                        Toast.makeText(BuildCardActivity.this, "教育检定成功，EDU+" + eduint, Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(BuildCardActivity.this, "教育检定失败", Toast.LENGTH_SHORT).show();

                    }

                    tv_STR.setText(STR + "");
                    tv_SIZ.setText(SIZ + "");
                    tv_EDU.setText(EDU + "");


                    tv_STR_half.setText(STR / 2 + "");
                    tv_STR_fif.setText(STR / 5 + "");
                    tv_SIZ_half.setText(SIZ / 2 + "");
                    tv_SIZ_fif.setText(SIZ / 5 + "");
                    tv_EDU_half.setText(EDU / 2 + "");
                    tv_EDU_fif.setText(EDU / 5 + "");
                    break;
                case 2://                    20-39 岁：对教育进行１次成长检定。
                    if (((int) (Math.random() * 100) + 1) > EDU) {
                        int eduint = (int) (Math.random() * 10) + 1;
                        EDU = EDU + eduint;
                        if (EDU > 99) {
                            EDU = 99;
                        }

                        Toast.makeText(BuildCardActivity.this, "教育检定成功，EDU+" + eduint, Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(BuildCardActivity.this, "教育检定失败", Toast.LENGTH_SHORT).show();

                    }
                    tv_EDU.setText(EDU + "");
                    tv_EDU_half.setText(EDU / 2 + "");
                    tv_EDU_fif.setText(EDU / 5 + "");
                    break;
                case 3://                    40-49 岁：对教育进行２次成长检定。力量体质
                       //                    敏捷合计减少５点。外貌减５点。
                    for (int i = 1; i < 3; i++) {
                        int eduint = (int) (Math.random() * 10) + 1;
                        if ( eduint> EDU) {
                            EDU = EDU + eduint;
                            if (EDU > 99) {
                                EDU = 99;
                            }
                        }while (i == 2) {

                            Toast.makeText(BuildCardActivity.this, "教育加值检定结束(暗骰)", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    int[] j5=RandomT.Randthree(5);

                    STR = STR - j5[0];
                    CON = CON - j5[1];
                    DEX = DEX - j5[2];
                    MOV = MOV - 1;


                    setTV();
                    break;

                case 4: //                    50-59 岁：对教育进行３次成长检定。力量体质敏
                        //                    捷合计减少１０点。外貌减１０点。
                    for (int i = 1; i < 4; i++) {
                        int eduint = (int) (Math.random() * 10) + 1;
                        if ( eduint> EDU&&EDU + eduint< 99) {
                            EDU = EDU + eduint;
                        }
                        while (i == 3) {
                            Toast.makeText(BuildCardActivity.this, "教育加值检定结束(暗骰)", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    int[] j10=RandomT.Randthree(10);

                    STR = STR - j10[0];
                    CON = CON - j10[1];
                    DEX = DEX - j10[2];
                    MOV = MOV - 2;


                    setTV();
                    break;

                case 5: //                    60-69 岁：对教育进行４次成长检定。力量体质敏
                        //                    捷合计减少２０点。外貌减１５点。
                    for (int i = 1; i < 5; i++) {
                        int eduint = (int) (Math.random() * 10) + 1;
                        if (  eduint> EDU&&EDU + eduint< 99) {
                            EDU = EDU + eduint;
                        }
                        while (i == 4) {

                            Toast.makeText(BuildCardActivity.this, "教育检定结束(暗骰)", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    int[] j20=RandomT.Randthree(20);
                    STR = STR - j20[0];
                    CON = CON - j20[1];
                    DEX = DEX - j20[2];
                    MOV = MOV - 3;

                    setTV();
                    break;
                case 6: //                    70-79 岁：对教育进行４次成长检定。力量体质敏
                        //                    捷合计减少４０点。外貌减２０点。
                    for (int i = 1; i < 5; i++) {
                        int eduint = (int) (Math.random() * 10) + 1;
                        if (  eduint> EDU&&EDU + eduint< 99) {
                            EDU = EDU + eduint;

                        }
                        while (i == 4) {

                            Toast.makeText(BuildCardActivity.this, "教育检定结束(暗骰)", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    int[] j40=RandomT.Randthree(40);

                    STR = STR - j40[0];
                    CON = CON - j40[1];
                    DEX = DEX - j40[2];
                    MOV = MOV - 4;


                    setTV();
                    break;
                case 7: //                    80-89 岁：对教育进行４次成长检定。力量体质敏
                        //                    捷合计减少８０点。外貌减２５点。
                    for (int i = 1; i < 5; i++) {
                        int eduint = (int) (Math.random() * 10) + 1;
                        if (  eduint> EDU&&EDU + eduint< 99) {
                            EDU = EDU + eduint;

                        }
                        while (i == 4) {
                            Toast.makeText(BuildCardActivity.this, "教育检定结束(暗骰)", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    int[] j80=RandomT.Randthree(80);

                    STR = STR - j80[0];
                    CON = CON - j80[1];
                    DEX = DEX - j80[2];
                    MOV = MOV - 5;


                    setTV();
                    break;
            }
            //                    15-19 岁：力量和体型各减５点。教育减５点。决
            //                    定幸运值时可以骰２次并取较好的一次。
            //                    20-39 岁：对教育进行１次成长检定。
            //                    40-49 岁：对教育进行２次成长检定。力量体质
            //                    敏捷合计减少５点。外貌减５点。
            //                    50-59 岁：对教育进行３次成长检定。力量体质敏
            //                    捷合计减少１０点。外貌减１０点。
            //                    60-69 岁：对教育进行４次成长检定。力量体质敏
            //                    捷合计减少２０点。外貌减１５点。
            //                    70-79 岁：对教育进行４次成长检定。力量体质敏
            //                    捷合计减少４０点。外貌减２０点。
            //                    80-89 岁：对教育进行４次成长检定。力量体质敏
            //                    捷合计减少８０点。外貌减２５点。

            //ed_AGE.setEnabled(false);

            int db = STR + SIZ;
            if (db >= 2 && db <= 64) {
                tv_DB.setText("" + "-2");
                tv_TX.setText("" + "-2");
            } else if (db >= 65 && db <= 84) {
                tv_DB.setText("" + "-1");
                tv_TX.setText("" + "-1");
            } else if (db >= 85 && db <= 124) {
                tv_DB.setText("" + "0");
                tv_TX.setText("" + "0");
            } else if (db >= 125 && db <= 164) {
                tv_DB.setText("" + "+1d4");
                tv_TX.setText("" + "1");
            } else if (db >= 165 && db <= 204) {
                tv_DB.setText("" + "+1d6");
                tv_TX.setText("" + "2");
            } else if (db >= 205 && db <= 284) {
                tv_DB.setText("" + "+2d6");
                tv_TX.setText("" + "3");
            } else if (db >= 285 && db <= 364) {
                tv_DB.setText("" + "+3d6");
                tv_TX.setText("" + "4");
            } else if (db >= 365 && db <= 444) {
                tv_DB.setText("" + "+4d6");
                tv_TX.setText("" + "5");
            } else if (db >= 445 && db <= 524) {
                tv_DB.setText("" + "+5d6");
                tv_TX.setText("" + "6");
            }

            Log.i("run", "run: db==" + db);

            tv_SAN.setText("" + INT);

            tv_MOV.setText("" + MOV);

            //处理完成后给handler发送消息  
            Message msg = new Message();
            msg.what = 2;
            Log.i("run", "run: msg.what" + msg.what);
            handler.sendMessage(msg);



        } else {

            Toast.makeText(BuildCardActivity.this, "年龄不能为空", Toast.LENGTH_SHORT).show();

        }
    }

    private void setTV() {
        tv_STR.setText(STR + "");
        tv_CON.setText(CON + "");
        tv_DEX.setText(DEX + "");

        tv_STR_half.setText(STR / 2 + "");
        tv_STR_fif.setText(STR / 5 + "");
        tv_CON_half.setText(CON / 2 + "");
        tv_CON_fif.setText(CON / 5 + "");
        tv_DEX_half.setText(DEX / 2 + "");
        tv_DEX_fif.setText(DEX / 5 + "");

        tv_EDU.setText(EDU + "");
        tv_EDU_half.setText(EDU / 2 + "");
        tv_EDU_fif.setText(EDU / 5 + "");
    }

    private void intiSecond() {
            //List<view> List1 = null;
        List<View> List1 = new ArrayList<>();
        SQLiteDatabase db = DBHelper.getInstance(this).getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME_1,null,null,null,null,null,null);
        if (cursor!=null){
            List1 = new ArrayList<>();
            //cursor.moveToPosition(stPostion); //移动到开始行
            while (cursor.moveToNext()){
                CareerItem item = new CareerItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("id")));
                item.setCareerName(cursor.getString(cursor.getColumnIndex("name")));
                item.setCareerReputation(cursor.getString(cursor.getColumnIndex("reputation")));
                item.setCareerAttributes(cursor.getString(cursor.getColumnIndex("attributes")));
                item.setCareerSkill(cursor.getString(cursor.getColumnIndex("skill")));

                View view = item.setView(item);
                List1.add(view);
            }
            cursor.close();
        }
        db.close();

        final LinearLayout careerlist = findViewById(R.id.career_list);
        final TextView careetv = findViewById(R.id.careerText);
        final TextView cartttvname = findViewById(R.id.careerName_tv);


        for (int i = 0; i < List1.size(); i++) {
            final int position = i;
            careerlist.addView(List1.get(i));

            //按照参数设置点击事件响应
            final List<View> finalList = List1;
            final int finalI = i;
            List1.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = finalList.get(finalI).findViewById(R.id.careerSkill);
                    TextView textView2 = finalList.get(finalI).findViewById(R.id.careerName);
                    TextView textView3 = finalList.get(finalI).findViewById(R.id.careerAttributes);

                    TextView textView1 = findViewById(R.id.careerText);
                    TextView textView11 = findViewById(R.id.build_own_point);
                    TextView textView12 = findViewById(R.id.build_like_point);

                    MyScrollView myScrollView,myView2;
                    myScrollView =findViewById(R.id.ScrollView_list);
                    myView2 =(MyScrollView)findViewById(R.id.skill_list_scrolview);

                    String str = textView.getText().toString();
                    String strName = textView2.getText().toString();
                    String strAtt = textView3.getText().toString();

                    cartttvname.setText(strName);
                    careetv.setText("职业本职技能：\n"+str);
                    if(strAtt.equals("教育×4")){
                        textView11.setText(""+EDU*4);
                    }else if(strAtt.equals("教育×2＋敏捷×2")){
                        textView11.setText(""+(EDU*2+DEX*2));
                    }else if(strAtt.equals("教育×2＋外貌×2")){
                        textView11.setText(""+(EDU*2+APP*2));
                    }else if(strAtt.equals("教育×2＋力量或敏捷×2")){
                        textView11.setText(""+(EDU*2+STR*2));
                    }else if(strAtt.equals("教育×2＋外貌或意志×2")){
                        textView11.setText(""+(EDU*2+APP*2));
                    }else if(strAtt.equals("教育×2＋敏捷或意志×2")){
                        textView11.setText(""+(EDU*2+DEX*2));
                    }else if(strAtt.equals("教育×2＋力量×2")){
                        textView11.setText(""+(EDU*2+STR*2));
                    }

                    textView12.setText(""+INT*2);
                    myScrollView.setVisibility(View.GONE);
                    myView2.setVisibility(View.VISIBLE);
                    textView1.setVisibility(View.VISIBLE);

                    Message msg = new Message();
                    msg.what = 3;
                    Log.i("OnClick", "list.setOnClickListener msg.what" + msg.what);
                    handler.sendMessage(msg);

                }
            });
        }
        Log.i("intiSecond", "careerlist----->"+careerlist);

    }

    private void intiThird() {
        List<View> List1 = new ArrayList<>();
        SQLiteDatabase db = DBHelper.getInstance(this).getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME_2,null,null,null,null,null,null);
        if (cursor!=null){
            List1 = new ArrayList<>();
            //cursor.moveToPosition(stPostion); //移动到开始行
            while (cursor.moveToNext()){
                SkillItem item = new SkillItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("id")));
                item.setSkillName(cursor.getString(cursor.getColumnIndex("name")));
                item.setSkillStart(cursor.getString(cursor.getColumnIndex("start")));

                View view = item.setView(item);
                List1.add(view);
            }
            cursor.close();
        }
        db.close();

        final LinearLayout skilllist = findViewById(R.id.skill_list_layout);

        for (int i = 0; i < List1.size(); i++) {
            final int position = i;
            skilllist.addView(List1.get(i));

            //按照参数设置点击事件响应
            final List<View> finalList2 = List1;
            final int final2 = i;

            //获得控件
            final TextView textView = finalList2.get(final2).findViewById(R.id.skill_point);
            final TextView textView_half = finalList2.get(final2).findViewById(R.id.build_skill_half);
            final TextView textView_fif = finalList2.get(final2).findViewById(R.id.build_skill_fif);

            final TextView textView11 = findViewById(R.id.build_own_point);
            final TextView textView12 = findViewById(R.id.build_like_point);

            RelativeLayout Add=finalList2.get(final2).findViewById(R.id.button_add);
            RelativeLayout Remove=finalList2.get(final2).findViewById(R.id.button_remove);

            final CheckBox checkBox = finalList2.get(final2).findViewById(R.id.checkBox);


            final int tv=0;


            //点击加减号，兴趣点，技能总点反应

            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!checkBox.isChecked()){
                        if ((int) Math.floor(Float.parseFloat(textView.getText().toString()))<100&&Integer.parseInt(textView12.getText().toString())>0) {

                            textView12.setText("" + (Integer.parseInt(textView12.getText().toString())-1));
                            textView.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +1));

                            textView_fif.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +1)/5);
                            textView_half.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +1)/2);

                        }else if((int) Math.floor(Float.parseFloat(textView.getText().toString()))>=100){
                            textView.setText(""+99);
                            textView_fif.setText("" + 99/5);
                            textView_half.setText("" + 99/2);
                        }else if(Integer.parseInt(textView12.getText().toString())<=0){ }
                    }else if (checkBox.isChecked()){
                        if ((int) Math.floor(Float.parseFloat(textView.getText().toString()))<100&&Integer.parseInt(textView11.getText().toString())>=0) {
                            textView11.setText("" + (Integer.parseInt(textView11.getText().toString())-1));
                            textView.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +1));

                            textView_fif.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +1)/5);
                            textView_half.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +1)/2);

                        }else if((int) Math.floor(Float.parseFloat(textView.getText().toString()))>=100){
                            textView.setText(""+99);
                            textView_fif.setText("" + 99/5);
                            textView_half.setText("" + 99/2);
                        }else if(Integer.parseInt(textView11.getText().toString())<0){ }
                    }
                }
            });

            Add.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (!checkBox.isChecked()){
                        if ((int) Math.floor(Float.parseFloat(textView.getText().toString())) < 100 && Integer.parseInt(textView12.getText().toString()) > 0) {
                            textView12.setText("" + (Integer.parseInt(textView12.getText().toString()) - 10));
                            textView.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) + 10));

                            textView_fif.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) + 10) / 5);
                            textView_half.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) + 10) / 2);

                        } else if ((int) Math.floor(Float.parseFloat(textView.getText().toString())) >= 100) {
                            textView.setText("" + 99);
                            textView_fif.setText("" + 99 / 5);
                            textView_half.setText("" + 99 / 2);
                        } else if (Integer.parseInt(textView12.getText().toString()) <= 0) {
                        }

                    }else if(checkBox.isChecked()){
                        if ((int) Math.floor(Float.parseFloat(textView.getText().toString()))<100&&Integer.parseInt(textView11.getText().toString())>0) {
                            textView11.setText("" + (Integer.parseInt(textView11.getText().toString())-10));
                            textView.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +10));

                            textView_fif.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +10)/5);
                            textView_half.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +10)/2);

                        }else if((int) Math.floor(Float.parseFloat(textView.getText().toString()))>=100){
                            textView.setText(""+99);
                            textView_fif.setText("" + 99/5);
                            textView_half.setText("" + 99/2);
                        }else if(Integer.parseInt(textView11.getText().toString())<=0){ }

                    }
                    return true;
                }
            });
            Remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!checkBox.isChecked()){
                        Log.i("onClick", "onClick:   tv =  " + tv);
                        if ((int) Math.floor(Float.parseFloat(textView.getText().toString())) > tv) {
                            textView12.setText("" + (Integer.parseInt(textView12.getText().toString()) + 1));
                            textView.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) - 1));

                            textView_fif.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) - 1) / 5);
                            textView_half.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) - 1) / 2);
                        } else if ((int) Math.floor(Float.parseFloat(textView.getText().toString())) <= tv) {
                            textView.setText("" + tv);
                        } else if (Integer.parseInt(textView12.getText().toString()) < 0) {
                        }
                }else if (checkBox.isChecked()){
                        if ((int) Math.floor(Float.parseFloat(textView.getText().toString()))>tv) {
                            textView11.setText("" + (Integer.parseInt(textView11.getText().toString())+1));
                            textView.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) -1));

                            textView_fif.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) -1)/5);
                            textView_half.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) -1)/2);
                        }else if((int) Math.floor(Float.parseFloat(textView.getText().toString()))<=tv){
                            textView.setText(""+tv);
                        }else if(Integer.parseInt(textView11.getText().toString())<0){ }
                    }
                }
            });
            Remove.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (!checkBox.isChecked()){

                        if ((int) Math.floor(Float.parseFloat(textView.getText().toString())) < 100 && Integer.parseInt(textView12.getText().toString()) > 0) {
                            textView12.setText("" + (Integer.parseInt(textView12.getText().toString()) + 10));
                            textView.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) - 10));

                            textView_fif.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) - 10) / 5);
                            textView_half.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) + 10) / 2);

                        } else if ((int) Math.floor(Float.parseFloat(textView.getText().toString())) >= 100) {
                            textView.setText("" + 99);
                            textView_fif.setText("" + 99 / 5);
                            textView_half.setText("" + 99 / 2);
                        } else if (Integer.parseInt(textView12.getText().toString()) <= 0) {
                        }
                    }else if (checkBox.isChecked()){
                        if ((int) Math.floor(Float.parseFloat(textView.getText().toString()))<100&&Integer.parseInt(textView11.getText().toString())>0) {
                            textView11.setText("" + (Integer.parseInt(textView11.getText().toString())+10));
                            textView.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) -10));

                            textView_fif.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) -10)/5);
                            textView_half.setText("" + ((int) Math.floor(Float.parseFloat(textView.getText().toString())) +10)/2);

                        }else if((int) Math.floor(Float.parseFloat(textView.getText().toString()))>=100){
                            textView.setText(""+99);
                            textView_fif.setText("" + 99/5);
                            textView_half.setText("" + 99/2);
                        }else if(Integer.parseInt(textView11.getText().toString())<=0){ }
                    }
                    return true;
                }
            });
        }
        Log.i("intiThird", "skilllist----->"+skilllist);
        }
        //第四步，完成车卡并储存在数据库中
    private void intiFour(){
        bt_FinishBuild=findViewById(R.id.finishBuild);
        bt_FinishBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textViewown=findViewById(R.id.build_own_point);
                TextView textViewlike=findViewById(R.id.build_like_point);
                if(Integer.parseInt(textViewown.getText().toString())>0||Integer.parseInt(textViewlike.getText().toString())>0){
                    //如果技能点未分配完毕，弹出提示框是否直接保存
                    AlertDialog.Builder builder=new AlertDialog.Builder(BuildCardActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("技能点未分配完毕，是否直接创建人物");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //确定按钮
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            dataToDB();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog b=builder.create();
                    b.show();
                }else if (Integer.parseInt(textViewown.getText().toString())==0||Integer.parseInt(textViewlike.getText().toString())==0){
                    //如果技能点分配完毕，弹出提示框,是否保存
                    AlertDialog.Builder builder=new AlertDialog.Builder(BuildCardActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("是否创建人物，数据无法更改");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //确定按钮
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            dataToDB();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog b=builder.create();
                    b.show();
                }
            }
        });
    }

    private void dataToDB(){
        //获取所有数据

        //数据放入数据库
        //返回人物卡创建页面

    }


    // 第二阶段，把从Excel报表中读出来的数据导出，写入到SQLite数据库中。
    private void writeDataToSQLite(List<CellDataContainer> cellDataContainer) throws NullPointerException{
        SQLiteDatabase mSQLiteDatabase = DBHelper.getInstance(this).getWritableDatabase();

        // 从Excel报表中读取出来的数据首行（第0行）是列名，故跳过。
        for (int i = 1; i < cellDataContainer.size(); i++) {
           CellDataContainer container = cellDataContainer.get(i);

            ContentValues contentValues = getContentValues(container.id, container.name,
                    container.reputation, container.attributes,container.skill);
            Log.i("container", "writeDataToSQLite: ");

            mSQLiteDatabase.insert(DBHelper.TABLE_NAME_1, null, contentValues);
        }

        mSQLiteDatabase.close();
    }

    private void writeDataToSQLite2(List<CellDataContainer2> cellDataContainer2) throws NullPointerException{
        SQLiteDatabase mSQLiteDatabase = DBHelper.getInstance(this).getWritableDatabase();

        // 从Excel报表中读取出来的数据首行（第0行）是列名，故跳过。
        for (int i = 1; i < cellDataContainer2.size(); i++) {
            CellDataContainer2 container2 = cellDataContainer2.get(i);

            ContentValues contentValues2 = getContentValues2(container2.skid, container2.skname,
                    container2.skstart);

            mSQLiteDatabase.insert(DBHelper.TABLE_NAME_2, null, contentValues2);
        }

        mSQLiteDatabase.close();
    }

    private ContentValues getContentValues(String id, String name,String reputation,String attributes,String skill) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.CAREER_ID, id);
        contentValues.put(DBHelper.CAREER_NAME, name);
        contentValues.put(DBHelper.CAREER_REPUTATION, reputation);
        contentValues.put(DBHelper.CAREER_ATTRIBUTES, attributes);
        contentValues.put(DBHelper.CAREER_SKILL, skill);

        return contentValues;
    }

    private ContentValues getContentValues2(String id, String name,String start) {
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(DBHelper.SKILL_ID, id);
        contentValues2.put(DBHelper.SKILL_NAME, name);
        contentValues2.put(DBHelper.SKILL_ST, start);

        return contentValues2;
    }


    // 第一阶段，从Excel报表中读出数据。
    private List<CellDataContainer> readDataFromExcel() throws IOException {
        //File xlsFile = new File(String.valueOf(getResources().getAssets().open("weapon.xls")), "weapon.xls");

        HSSFWorkbook mWorkbook = null;
        try {
            //FileInputStream fis = new FileInputStream(xlsFile);
            InputStream fis = getResources().getAssets().open("weapon.xls");
            Log.i("readDataFromExcel", "从表中读取数据: fis="+fis);
            mWorkbook = new HSSFWorkbook(fis);
            Log.i("readDataFromExcel", "从表中读取数据2: fis="+fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HSSFSheet mSheet = mWorkbook.getSheet("Sheet2");

        int rowNumber = mSheet.getLastRowNum() + 1;
        Log.i("rowNumber", "读取表行数: fis="+mSheet.getLastRowNum()+1);
        Log.i("rowNumber", "读取表列数: fis="+mSheet.getRow(0).getPhysicalNumberOfCells()+1);


        List<CellDataContainer> cellDataContainer = new ArrayList<>();
        for (int row = 0; row < rowNumber; row++) {
            HSSFRow r = mSheet.getRow(row);

            CellDataContainer container = new CellDataContainer();
            container.id = r.getCell(0).toString();
            container.name = r.getCell(1).toString();
            container.reputation = r.getCell(2).toString();
            container.attributes = r.getCell(3).toString();
            container.skill = r.getCell(4).toString();

            Log.i("id", "id: "+container.id);

            cellDataContainer.add(container);
        }

        return cellDataContainer;
    }

    private List<CellDataContainer2> readDataFromExcel2() throws IOException {
        //File xlsFile = new File(String.valueOf(getResources().getAssets().open("weapon.xls")), "weapon.xls");

        HSSFWorkbook mWorkbook = null;
        try {
            //FileInputStream fis = new FileInputStream(xlsFile);
            InputStream fis = getResources().getAssets().open("weapon.xls");
            Log.i("readDataFromExcel", "从表中读取数据: fis="+fis);
            mWorkbook = new HSSFWorkbook(fis);
            Log.i("readDataFromExcel", "从表中读取数据2: fis="+fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HSSFSheet mSheet = mWorkbook.getSheet("Sheet3");

        int rowNumber = mSheet.getLastRowNum() + 1;
        Log.i("rowNumber", "读取表行数: fis="+mSheet.getLastRowNum()+1);
        Log.i("rowNumber", "读取表列数: fis="+mSheet.getRow(0).getPhysicalNumberOfCells()+1);


        List<CellDataContainer2> cellDataContainer2 = new ArrayList<>();
        for (int row = 0; row < rowNumber; row++) {
            HSSFRow r = mSheet.getRow(row);

            CellDataContainer2 container2 = new CellDataContainer2();
            container2.skid = r.getCell(0).toString();
            container2.skname = r.getCell(1).toString();
            container2.skstart = r.getCell(2).toString();

            Log.i("id", "id: "+container2.skid);

            cellDataContainer2.add(container2);
        }

        return cellDataContainer2;
    }


    //从Excel表中单元读取出来的数据容器。
    private class CellDataContainer {
        public String id;
        public String name;
        public String reputation;
        public String attributes;
        public String skill;


    }

    private class CellDataContainer2{
        public String skid;
        public  String skname;
        public String skstart;

    }

    //手机返回键弹窗
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                        finish();
                        break;

                    case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                        break;

                    default:
                        break;

                }
            }
        };
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();

            // 设置对话框标题
            isExit.setTitle("系统提示");

            // 设置对话框消息
            isExit.setMessage("确定要退出吗？\n退出数据无法保存");

            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);

            // 显示对话框
            isExit.show();


        }
        return false;
    }


}
