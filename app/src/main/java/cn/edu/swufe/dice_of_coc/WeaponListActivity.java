package cn.edu.swufe.dice_of_coc;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.litepal.LitePalApplication.getContext;


public class WeaponListActivity extends AppCompatActivity {
    private FoldLayout foldlayout, foldLayout1,
                        foldlayout2, foldlayout3,
                        foldlayout4, foldlayout5,
                        foldlayout6,foldlayout7;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_list);

        List<CellDataContainer> cellDataContainer = null;
        try {
            cellDataContainer = readDataFromExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException ei){
            ei.printStackTrace();
        }
        writeDataToSQLite(cellDataContainer);

        initView();


    }

    private void initOnLongCliclk(){
        /**
         * 设置布局的长按事件实现注释显示
         */
        final HelpDialog helpDialog = new HelpDialog(this);

        foldlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView hptvm = findViewById(R.id.help_layout_main1);

                helpDialog.setHptvm(""+hptvm.getText());

                helpDialog.show();
                return true;
            }
        });
        foldLayout1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView hptvm = findViewById(R.id.help_layout_main2);

                helpDialog.setHptvm(""+hptvm.getText());

                helpDialog.show();
                return true;
            }
        });
        foldlayout2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView hptvm = findViewById(R.id.help_layout_main3);

                helpDialog.setHptvm(""+hptvm.getText());

                helpDialog.show();
                return true;
            }
        });
        foldlayout3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView hptvm = findViewById(R.id.help_layout_main4);

                helpDialog.setHptvm(""+hptvm.getText());

                helpDialog.show();
                return true;
            }
        });
        foldlayout4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView hptvm = findViewById(R.id.help_layout_main5);

                helpDialog.setHptvm(""+hptvm.getText());

                helpDialog.show();
                return true;
            }
        });
        foldlayout5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView hptvm = findViewById(R.id.help_layout_main6);

                helpDialog.setHptvm(""+hptvm.getText());

                helpDialog.show();
                return true;
            }
        });
        foldlayout6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView hptvm = findViewById(R.id.help_layout_main7);

                helpDialog.setHptvm(""+hptvm.getText());

                helpDialog.show();
                return true;
            }
        });
        foldlayout7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView hptvm = findViewById(R.id.help_layout_main8);

                helpDialog.setHptvm(""+hptvm.getText());

                helpDialog.show();
                return true;
            }
        });

    }
    private void initView() {

        foldlayout = (FoldLayout) findViewById(R.id.foldlayout);
        foldLayout1 = (FoldLayout) findViewById(R.id.foldlayout1);
        foldlayout2 = (FoldLayout) findViewById(R.id.foldlayout2);
        foldlayout3 = (FoldLayout) findViewById(R.id.foldlayout3);
        foldlayout4 = (FoldLayout) findViewById(R.id.foldlayout4);
        foldlayout5 = (FoldLayout) findViewById(R.id.foldlayout5);
        foldlayout6 = (FoldLayout) findViewById(R.id.foldlayout6);
        foldlayout7 = (FoldLayout) findViewById(R.id.foldlayout7);


        List<View> views1 = new ArrayList<>();
        List<View> views2 = new ArrayList<>();
        List<View> views3 = new ArrayList<>();
        List<View> views4 = new ArrayList<>();
        List<View> views5 = new ArrayList<>();
        List<View> views6 = new ArrayList<>();
        List<View> views7 = new ArrayList<>();
        List<View> views8 = new ArrayList<>();

        initOnLongCliclk();


        /**
         * 初始化Menu的Item
         */
        views1=getItem(0,16);
        foldlayout.addItemView(views1);

        views2=getItem(16,28);
        foldLayout1.addItemView(views2);

        views3=getItem(28,34);
        foldlayout2.addItemView(views3);

        views4=getItem(34,42);
        foldlayout3.addItemView(views4);

        views5=getItem(42,70);
        foldlayout4.addItemView(views5);

        views6=getItem(70,79);
        foldlayout5.addItemView(views6);

        views7=getItem(79,91);
        foldlayout6.addItemView(views7);

        views8=getItem(91,100);
        foldlayout7.addItemView(views8);

        final ConstomDialog mdialog = new ConstomDialog(this);//建立自定义对话框

        final List<View> finalViews = views1;
        final List<View> finalViews1 = views2;
        final List<View> finalViews2 = views3;
        final List<View> finalViews3 = views4;
        final List<View> finalViews4 = views5;
        final List<View> finalViews5 = views6;
        final List<View> finalViews6 = views7;
        final List<View> finalViews7 = views8;



        /**
         * 设置Item的单击事件
         */
        foldlayout.setOnItemClickListener(new FoldLayout.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //获得该Item的数据，放入对话框中显示
                TextView tv00 = finalViews.get(position).findViewById(R.id.weaponName);
                TextView tv11 = finalViews.get(position).findViewById(R.id.weaponSkills);
                TextView tv22 = finalViews.get(position).findViewById(R.id.weaponDamage);
                TextView tv33 = finalViews.get(position).findViewById(R.id.weaponLimit1);
                TextView tv44 = finalViews.get(position).findViewById(R.id.weaponVartime);
                TextView tv55 = finalViews.get(position).findViewById(R.id.weaponNum);
                TextView tv66 = finalViews.get(position).findViewById(R.id.weaponPrice);
                TextView tv77 = finalViews.get(position).findViewById(R.id.weaponFault);
                TextView tv88 = finalViews.get(position).findViewById(R.id.weaponTime);

                mdialog.setTv(""+tv00.getText());
                mdialog.setTv1(""+tv11.getText());
                mdialog.setTv2(""+tv22.getText());
                mdialog.setTv3(""+tv33.getText());
                mdialog.setTv4(""+tv44.getText());
                mdialog.setTv5(""+tv55.getText());
                mdialog.setTv6(""+tv66.getText());
                mdialog.setTv7(""+tv77.getText());
                mdialog.setTv8(""+tv88.getText());

                //列表显示
                //Toast.makeText(WeaponListActivity.this, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
                mdialog.show();

            }
        });

        foldLayout1.setOnItemClickListener(new FoldLayout.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //获得该Item的数据，放入对话框中显示
                TextView tv00 = finalViews1.get(position).findViewById(R.id.weaponName);
                TextView tv11 = finalViews1.get(position).findViewById(R.id.weaponSkills);
                TextView tv22 = finalViews1.get(position).findViewById(R.id.weaponDamage);
                TextView tv33 = finalViews1.get(position).findViewById(R.id.weaponLimit1);
                TextView tv44 = finalViews1.get(position).findViewById(R.id.weaponVartime);
                TextView tv55 = finalViews1.get(position).findViewById(R.id.weaponNum);
                TextView tv66 = finalViews1.get(position).findViewById(R.id.weaponPrice);
                TextView tv77 = finalViews1.get(position).findViewById(R.id.weaponFault);
                TextView tv88 = finalViews1.get(position).findViewById(R.id.weaponTime);

                mdialog.setTv(""+tv00.getText());
                mdialog.setTv1(""+tv11.getText());
                mdialog.setTv2(""+tv22.getText());
                mdialog.setTv3(""+tv33.getText());
                mdialog.setTv4(""+tv44.getText());
                mdialog.setTv5(""+tv55.getText());
                mdialog.setTv6(""+tv66.getText());
                mdialog.setTv7(""+tv77.getText());
                mdialog.setTv8(""+tv88.getText());

                //列表显示
                //Toast.makeText(WeaponListActivity.this, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
                mdialog.show();

            }
        });
        foldlayout2.setOnItemClickListener(new FoldLayout.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //获得该Item的数据，放入对话框中显示
                TextView tv00 = finalViews2.get(position).findViewById(R.id.weaponName);
                TextView tv11 = finalViews2.get(position).findViewById(R.id.weaponSkills);
                TextView tv22 = finalViews2.get(position).findViewById(R.id.weaponDamage);
                TextView tv33 = finalViews2.get(position).findViewById(R.id.weaponLimit1);
                TextView tv44 = finalViews2.get(position).findViewById(R.id.weaponVartime);
                TextView tv55 = finalViews2.get(position).findViewById(R.id.weaponNum);
                TextView tv66 = finalViews2.get(position).findViewById(R.id.weaponPrice);
                TextView tv77 = finalViews2.get(position).findViewById(R.id.weaponFault);
                TextView tv88 = finalViews2.get(position).findViewById(R.id.weaponTime);

                mdialog.setTv(""+tv00.getText());
                mdialog.setTv1(""+tv11.getText());
                mdialog.setTv2(""+tv22.getText());
                mdialog.setTv3(""+tv33.getText());
                mdialog.setTv4(""+tv44.getText());
                mdialog.setTv5(""+tv55.getText());
                mdialog.setTv6(""+tv66.getText());
                mdialog.setTv7(""+tv77.getText());
                mdialog.setTv8(""+tv88.getText());

                //列表显示
               // Toast.makeText(WeaponListActivity.this, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
                mdialog.show();

            }
        });

        foldlayout3.setOnItemClickListener(new FoldLayout.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //获得该Item的数据，放入对话框中显示
                TextView tv00 = finalViews3.get(position).findViewById(R.id.weaponName);
                TextView tv11 = finalViews3.get(position).findViewById(R.id.weaponSkills);
                TextView tv22 = finalViews3.get(position).findViewById(R.id.weaponDamage);
                TextView tv33 = finalViews3.get(position).findViewById(R.id.weaponLimit1);
                TextView tv44 = finalViews3.get(position).findViewById(R.id.weaponVartime);
                TextView tv55 = finalViews3.get(position).findViewById(R.id.weaponNum);
                TextView tv66 = finalViews3.get(position).findViewById(R.id.weaponPrice);
                TextView tv77 = finalViews3.get(position).findViewById(R.id.weaponFault);
                TextView tv88 = finalViews3.get(position).findViewById(R.id.weaponTime);

                mdialog.setTv(""+tv00.getText());
                mdialog.setTv1(""+tv11.getText());
                mdialog.setTv2(""+tv22.getText());
                mdialog.setTv3(""+tv33.getText());
                mdialog.setTv4(""+tv44.getText());
                mdialog.setTv5(""+tv55.getText());
                mdialog.setTv6(""+tv66.getText());
                mdialog.setTv7(""+tv77.getText());
                mdialog.setTv8(""+tv88.getText());

                //列表显示
                //Toast.makeText(WeaponListActivity.this, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
                mdialog.show();

            }
        });

        foldlayout4.setOnItemClickListener(new FoldLayout.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //获得该Item的数据，放入对话框中显示
                TextView tv00 = finalViews4.get(position).findViewById(R.id.weaponName);
                TextView tv11 = finalViews4.get(position).findViewById(R.id.weaponSkills);
                TextView tv22 = finalViews4.get(position).findViewById(R.id.weaponDamage);
                TextView tv33 = finalViews4.get(position).findViewById(R.id.weaponLimit1);
                TextView tv44 = finalViews4.get(position).findViewById(R.id.weaponVartime);
                TextView tv55 = finalViews4.get(position).findViewById(R.id.weaponNum);
                TextView tv66 = finalViews4.get(position).findViewById(R.id.weaponPrice);
                TextView tv77 = finalViews4.get(position).findViewById(R.id.weaponFault);
                TextView tv88 = finalViews4.get(position).findViewById(R.id.weaponTime);

                mdialog.setTv(""+tv00.getText());
                mdialog.setTv1(""+tv11.getText());
                mdialog.setTv2(""+tv22.getText());
                mdialog.setTv3(""+tv33.getText());
                mdialog.setTv4(""+tv44.getText());
                mdialog.setTv5(""+tv55.getText());
                mdialog.setTv6(""+tv66.getText());
                mdialog.setTv7(""+tv77.getText());
                mdialog.setTv8(""+tv88.getText());

                //列表显示
                //Toast.makeText(WeaponListActivity.this, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
                mdialog.show();

            }
        });

        foldlayout5.setOnItemClickListener(new FoldLayout.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //获得该Item的数据，放入对话框中显示
                TextView tv00 = finalViews5.get(position).findViewById(R.id.weaponName);
                TextView tv11 = finalViews5.get(position).findViewById(R.id.weaponSkills);
                TextView tv22 = finalViews5.get(position).findViewById(R.id.weaponDamage);
                TextView tv33 = finalViews5.get(position).findViewById(R.id.weaponLimit1);
                TextView tv44 = finalViews5.get(position).findViewById(R.id.weaponVartime);
                TextView tv55 = finalViews5.get(position).findViewById(R.id.weaponNum);
                TextView tv66 = finalViews5.get(position).findViewById(R.id.weaponPrice);
                TextView tv77 = finalViews5.get(position).findViewById(R.id.weaponFault);
                TextView tv88 = finalViews5.get(position).findViewById(R.id.weaponTime);

                mdialog.setTv(""+tv00.getText());
                mdialog.setTv1(""+tv11.getText());
                mdialog.setTv2(""+tv22.getText());
                mdialog.setTv3(""+tv33.getText());
                mdialog.setTv4(""+tv44.getText());
                mdialog.setTv5(""+tv55.getText());
                mdialog.setTv6(""+tv66.getText());
                mdialog.setTv7(""+tv77.getText());
                mdialog.setTv8(""+tv88.getText());

                //列表显示
                //Toast.makeText(WeaponListActivity.this, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
                mdialog.show();

            }
        });

        foldlayout6.setOnItemClickListener(new FoldLayout.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //获得该Item的数据，放入对话框中显示
                TextView tv00 = finalViews6.get(position).findViewById(R.id.weaponName);
                TextView tv11 = finalViews6.get(position).findViewById(R.id.weaponSkills);
                TextView tv22 = finalViews6.get(position).findViewById(R.id.weaponDamage);
                TextView tv33 = finalViews6.get(position).findViewById(R.id.weaponLimit1);
                TextView tv44 = finalViews6.get(position).findViewById(R.id.weaponVartime);
                TextView tv55 = finalViews6.get(position).findViewById(R.id.weaponNum);
                TextView tv66 = finalViews6.get(position).findViewById(R.id.weaponPrice);
                TextView tv77 = finalViews6.get(position).findViewById(R.id.weaponFault);
                TextView tv88 = finalViews6.get(position).findViewById(R.id.weaponTime);

                mdialog.setTv(""+tv00.getText());
                mdialog.setTv1(""+tv11.getText());
                mdialog.setTv2(""+tv22.getText());
                mdialog.setTv3(""+tv33.getText());
                mdialog.setTv4(""+tv44.getText());
                mdialog.setTv5(""+tv55.getText());
                mdialog.setTv6(""+tv66.getText());
                mdialog.setTv7(""+tv77.getText());
                mdialog.setTv8(""+tv88.getText());

                //列表显示
                //Toast.makeText(WeaponListActivity.this, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
                mdialog.show();

            }
        });

        foldlayout7.setOnItemClickListener(new FoldLayout.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //获得该Item的数据，放入对话框中显示
                TextView tv00 = finalViews7.get(position).findViewById(R.id.weaponName);
                TextView tv11 = finalViews7.get(position).findViewById(R.id.weaponSkills);
                TextView tv22 = finalViews7.get(position).findViewById(R.id.weaponDamage);
                TextView tv33 = finalViews7.get(position).findViewById(R.id.weaponLimit1);
                TextView tv44 = finalViews7.get(position).findViewById(R.id.weaponVartime);
                TextView tv55 = finalViews7.get(position).findViewById(R.id.weaponNum);
                TextView tv66 = finalViews7.get(position).findViewById(R.id.weaponPrice);
                TextView tv77 = finalViews7.get(position).findViewById(R.id.weaponFault);
                TextView tv88 = finalViews7.get(position).findViewById(R.id.weaponTime);

                mdialog.setTv(""+tv00.getText());
                mdialog.setTv1(""+tv11.getText());
                mdialog.setTv2(""+tv22.getText());
                mdialog.setTv3(""+tv33.getText());
                mdialog.setTv4(""+tv44.getText());
                mdialog.setTv5(""+tv55.getText());
                mdialog.setTv6(""+tv66.getText());
                mdialog.setTv7(""+tv77.getText());
                mdialog.setTv8(""+tv88.getText());

                //列表显示
                //Toast.makeText(WeaponListActivity.this, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
                mdialog.show();

            }
        });



    }


    // 第二阶段，把从Excel报表中读出来的数据导出，写入到SQLite数据库中。
    private void writeDataToSQLite(List<CellDataContainer> cellDataContainer) throws NullPointerException{
        SQLiteDatabase mSQLiteDatabase = DBHelper.getInstance(this).getWritableDatabase();

        // 从Excel报表中读取出来的数据首行（第0行）是列名，故跳过。
        for (int i = 1; i < cellDataContainer.size(); i++) {
            CellDataContainer container = cellDataContainer.get(i);

            ContentValues contentValues = getContentValues(container.id, container.name,
                    container.skills, container.damage,container.limit1,container.vartime,container.num,
                    container.price,container.fault,container.time);

            mSQLiteDatabase.insert(DBHelper.TABLE_NAME, null, contentValues);
        }

        mSQLiteDatabase.close();
    }

    private ContentValues getContentValues(String id, String name, String skills, String damage,String limit1,
                                           String vartime,String num,String price,String fault,String time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.WEAPON_ID, id);
        contentValues.put(DBHelper.WEAPON_NAME, name);
        contentValues.put(DBHelper.WEAPON_SKILLS, skills);
        contentValues.put(DBHelper.WEAPON_DAMAGE, damage);
        contentValues.put(DBHelper.WEAPON_LIMIT, limit1);
        contentValues.put(DBHelper.WEAPON_VARTIME, vartime);
        contentValues.put(DBHelper.WEAPON_NUM, num);
        contentValues.put(DBHelper.WEAPON_PRICE, price);
        contentValues.put(DBHelper.WEAPON_FAULT, fault);
        contentValues.put(DBHelper.WEAPON_TIME, time);
        return contentValues;
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

            HSSFSheet mSheet = mWorkbook.getSheet("Sheet1");

            int rowNumber = mSheet.getLastRowNum() + 1;
            Log.i("rowNumber", "读取表行数: fis="+mSheet.getLastRowNum()+1);
            Log.i("rowNumber", "读取表列数: fis="+mSheet.getRow(0).getPhysicalNumberOfCells()+1);


            List<CellDataContainer> cellDataContainer = new ArrayList<>();
            for (int row = 0; row < rowNumber; row++) {
                HSSFRow r = mSheet.getRow(row);

                CellDataContainer container = new CellDataContainer();
                container.id = r.getCell(0).toString();
                container.name = r.getCell(1).toString();
                container.skills = r.getCell(2).toString();
                container.damage = r.getCell(3).toString();
                container.limit1 = r.getCell(4).toString();
                container.vartime = r.getCell(5).toString();
                container.num = r.getCell(6).toString();
                container.price = r.getCell(7).toString();
                container.fault = r.getCell(8).toString();
                container.time = r.getCell(9).toString();
                Log.i("time", "time: "+container.time);

                cellDataContainer.add(container);
            }

            return cellDataContainer;
        }

    //从Excel表中单元读取出来的数据容器。
    private class CellDataContainer {
            public String id;
            public String name;
            public String skills;
            public String damage;
            public String limit1;
            public String vartime;
            public String num;
            public String price;
            public String fault;
            public String time;

        }

    //封装获得下拉Item的方法
    public List<View> getItem(int stPostion, int endPostion){
        //List<view> List1 = null;
        List<View> List1 = new ArrayList<>();
        SQLiteDatabase db = DBHelper.getInstance(this).getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        if (cursor!=null){
            List1 = new ArrayList<>();
            cursor.moveToPosition(stPostion); //移动到开始行
            while (cursor.moveToNext()&&cursor.getPosition()<=endPostion){
                WeaponItem item = new WeaponItem();
                item.setID(cursor.getInt(cursor.getColumnIndex("id")));
                item.setWeaponName(cursor.getString(cursor.getColumnIndex("name")));
                item.setWeaponskills(cursor.getString(cursor.getColumnIndex("skills")));
                item.setWeapondamage(cursor.getString(cursor.getColumnIndex("damage")));
                item.setWeaponlimit1(cursor.getString(cursor.getColumnIndex("limit1")));
                item.setWeaponvartime(cursor.getString(cursor.getColumnIndex("vartime")));
                item.setWeaponnum(cursor.getString(cursor.getColumnIndex("num")));
                item.setWeaponprice(cursor.getString(cursor.getColumnIndex("price")));
                item.setWeaponfault(cursor.getString(cursor.getColumnIndex("fault")));
                item.setWeapontime(cursor.getString(cursor.getColumnIndex("time")));

                View view = item.setView(item);
                List1.add(view);
            }
            cursor.close();
        }
        db.close();
        return List1;
    }

    //自定义显示数据的对话框
    public class ConstomDialog extends Dialog {

        /*标题文字*/
        private TextView tv;

        /*各列TextView*/
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private TextView tv6;
        private TextView tv7;
        private TextView tv8;

        //构造方法
        public ConstomDialog(Context context) {
            super(context, R.style.mdialog);
            //获取布局
            View view = LayoutInflater.from(getContext()).
                    inflate(R.layout.dialog_layout, null);

            tv = (TextView) view.findViewById(R.id.title);
            tv1 = (TextView) view.findViewById(R.id.dialog_skillText);
            tv2 = (TextView) view.findViewById(R.id.dialog_damageText);
            tv3 = (TextView) view.findViewById(R.id.dialog_limit1Text);
            tv4 = (TextView) view.findViewById(R.id.dialog_vartime1Text);
            tv5 = (TextView) view.findViewById(R.id.dialog_numText);
            tv6 = (TextView) view.findViewById(R.id.dialog_priceText);
            tv7 = (TextView) view.findViewById(R.id.dialog_faultText);
            tv8 = (TextView) view.findViewById(R.id.dialog_timeText);


            //设置显示的视图
            setContentView(view);
        }

        /**
         * 设置显示的标题文字
         */
        public void setTv(String string) {
            tv.setText(string);
        }

        public void setTv1(String string) {
            tv1.setText(string);
        }

        public void setTv2(String string) {
            tv2.setText(string);
        }

        public void setTv3(String string) {
            tv3.setText(string);
        }

        public void setTv4(String string) {
            tv4.setText(string);
        }

        public void setTv5(String string) {
            tv5.setText(string);
        }

        public void setTv6(String string) {
            tv6.setText(string);
        }

        public void setTv7(String string) {
            tv7.setText(string);
        }

        public void setTv8(String string) {
            tv8.setText(string);
        }
    }
}





