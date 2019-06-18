package cn.edu.swufe.dice_of_coc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "mydata.db";

    static final String TABLE_NAME = "tb_weapons";
    static final String TABLE_NAME_1 ="tb_career";
    static final String TABLE_NAME_2 ="tb_skill";


    public static final String WEAPON_ID = "id";
    public static final String WEAPON_NAME = "name";
    public static final String WEAPON_SKILLS = "skills";
    public static final String WEAPON_DAMAGE = "damage";
    public static final String WEAPON_LIMIT = "limit1";
    public static final String WEAPON_VARTIME = "vartime";
    public static final String WEAPON_NUM = "num";
    public static final String WEAPON_PRICE = "price";
    public static final String WEAPON_FAULT = "fault";
    public static final String WEAPON_TIME = "time";

    public static final String CAREER_ID = "id";
    public static final String CAREER_NAME = "name";
    public static final String CAREER_REPUTATION = "reputation";
    public static final String CAREER_ATTRIBUTES = "attributes";
    public static final String CAREER_SKILL = "skill";

    public static final String SKILL_ID= "id";
    public static final String SKILL_NAME= "name";
    public static final String SKILL_ST= "start";

    private static DBHelper helper;


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    public static DBHelper getInstance(Context context) {
        if (helper == null) {
            helper = new DBHelper(context, DB_NAME, null,VERSION);
        }

        return helper;
    }



    public DBHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" + WEAPON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WEAPON_NAME + " varchar(60)," + WEAPON_SKILLS + " varchar(30)," + WEAPON_DAMAGE
                + " TEXT,"+ WEAPON_LIMIT + " varchar(60)," + WEAPON_VARTIME + " varchar(60)," + WEAPON_NUM
                + " varchar(60)," + WEAPON_PRICE + " varchar(60)," + WEAPON_FAULT
                + " varchar(60)," + WEAPON_TIME + " varchar(60)"+");");


        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME_1+" (" + CAREER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CAREER_NAME + " varchar(60)," + CAREER_REPUTATION + " varchar(15)," + CAREER_ATTRIBUTES
                + " TEXT,"+ CAREER_SKILL + " varchar(90)" +");");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME_2+" (" + SKILL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SKILL_NAME + " varchar(40)," + SKILL_ST + " varchar(15)" +");");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
