package cn.edu.swufe.dice_of_coc;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class WeaponListActivity extends AppCompatActivity {
    private FoldLayout foldlayout,foldLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_list);

        List<CellDataContainer> cellDataContainer = null;
        try {
            cellDataContainer = readDataFromExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeDataToSQLite(cellDataContainer);

        initView();


    }
    private void initView() {

        foldlayout = (FoldLayout) findViewById(R.id.foldlayout);
        foldLayout1 = (FoldLayout) findViewById(R.id.foldlayout1);
        List<View> views1 = new ArrayList<>();
        List<View> views2 = new ArrayList<>();

        /**
         * 初始化第一个Menu的Item
         */
        for (int i = 0;i<2;i++) {
            views1.add(getLayoutInflater().inflate(R.layout.layout_item,null));
        }
        foldlayout.addItemView(views1);
        /**
         * 初始化第二个Menu的Item
         */
        for (int i = 0;i<5;i++) {
            views2.add(getLayoutInflater().inflate(R.layout.layout_item,null));
        }
        foldLayout1.addItemView(views2);

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


        // 第一阶段，从Excel报表中读出数据。
        private List<CellDataContainer> readDataFromExcel() throws IOException {
            //File xlsFile = new File(String.valueOf(getResources().getAssets().open("weapon.xls")), "weapon.xls");

            HSSFWorkbook mWorkbook = null;
            try {
                //FileInputStream fis = new FileInputStream(xlsFile);
                InputStream fis = getResources().getAssets().open("weapon.xls");
                mWorkbook = new HSSFWorkbook(fis);
            } catch (Exception e) {
                e.printStackTrace();
            }

            HSSFSheet mSheet = mWorkbook.getSheet("Sheet1");

            int rowNumber = mSheet.getLastRowNum() + 1;

            List<CellDataContainer> cellDataContainer = new ArrayList<>();
            for (int row = 0; row < rowNumber; row++) {
                HSSFRow r = mSheet.getRow(row);

                CellDataContainer container = new CellDataContainer();
                container.id = r.getCell(0).toString();
                container.name = r.getCell(1).toString();
                container.skills = r.getCell(2).toString();
                container.damage = r.getCell(3).toString();
                container.limit = r.getCell(4).toString();
                container.vartime = r.getCell(5).toString();
                container.num = r.getCell(6).toString();
                container.price = r.getCell(7).toString();
                container.fault = r.getCell(8).toString();
                container.time = r.getCell(9).toString();

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
            public String limit;
            public String vartime;
            public String num;
            public String price;
            public String fault;
            public String time;

        }
    // 第二阶段，把从Excel报表中读出来的数据导出，写入到SQLite数据库中。
    private void writeDataToSQLite(List<CellDataContainer> cellDataContainer) {
        SQLiteDatabase mSQLiteDatabase = DBHelper.getInstance(this).getWritableDatabase();

        // 从Excel报表中读取出来的数据首行（第0行）是列名，故跳过。
        for (int i = 1; i < cellDataContainer.size(); i++) {
            CellDataContainer container = cellDataContainer.get(i);

            ContentValues contentValues = getContentValues(Integer.parseInt(container.id), container.name,
                    container.skills, container.damage,container.limit,container.vartime,container.num,
                    container.price,container.fault,container.time);

            mSQLiteDatabase.insert(DBHelper.TABLE_NAME, null, contentValues);
        }

        mSQLiteDatabase.close();
    }

    private ContentValues getContentValues(int id, String name, String skills, String damage,String limit,
                                           String vartime,String num,String price,String fault,String time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.WEAPON_ID, id);
        contentValues.put(DBHelper.WEAPON_NAME, name);
        contentValues.put(DBHelper.WEAPON_SKILLS, skills);
        contentValues.put(DBHelper.WEAPON_DAMAGE, damage);
        contentValues.put(DBHelper.WEAPON_LIMIT, limit);
        contentValues.put(DBHelper.WEAPON_VARTIME, vartime);
        contentValues.put(DBHelper.WEAPON_NUM, num);
        contentValues.put(DBHelper.WEAPON_PRICE, price);
        contentValues.put(DBHelper.WEAPON_FAULT, fault);
        contentValues.put(DBHelper.WEAPON_TIME, time);
        return contentValues;
    }
}





