package cn.edu.swufe.dice_of_coc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private FoldLayout foldlayout,foldLayout1,foldlayout2,foldlayout3,foldlayout4,foldlayout5,
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


        /**
         * 初始化第一个Menu的Item
         */
        views1=getItem(0,7);
        foldlayout.addItemView(views1);
        /**
         * 初始化第二个Menu的Item
         */
        views2=getItem(8,15);
        foldLayout1.addItemView(views2);

        views3=getItem(16,23);
        foldlayout2.addItemView(views3);

        views4=getItem(24,31);
        foldlayout3.addItemView(views4);

        views5=getItem(32,39);
        foldlayout4.addItemView(views5);

        views6=getItem(40,47);
        foldlayout5.addItemView(views6);

        views7=getItem(48,55);
        foldlayout6.addItemView(views7);

        views8=getItem(56,63);
        foldlayout7.addItemView(views8);

        /**
         * 设置Item的单击事件
         */
        foldlayout.setOnItemClickListener(new FoldLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(WeaponListActivity.this, "点击了第"+position+"个", Toast.LENGTH_SHORT).show();
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

        // 从Excel表中单元读取出来的数据容器。
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
                View view = item.setView(item);
                List1.add(view);
            }
            cursor.close();
        }
        db.close();
        return List1;
    }




}





