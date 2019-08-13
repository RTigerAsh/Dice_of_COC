package cn.edu.swufe.dice_of_coc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "mydata.db";//创建数据库
    //创建表名
    static final String TABLE_NAME = "tb_weapons";
    static final String TABLE_NAME_1 ="tb_career";
    static final String TABLE_NAME_2 ="tb_skill";
    static final String TABLE_NAME_3 ="tb_main";

    //武器列表
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
    //职业技能表
    public static final String CAREER_ID = "id";
    public static final String CAREER_NAME = "name";
    public static final String CAREER_REPUTATION = "reputation";
    public static final String CAREER_ATTRIBUTES = "attributes";
    public static final String CAREER_SKILL = "skill";
    //技能信息表
    public static final String SKILL_ID= "id";
    public static final String SKILL_NAME= "name";
    public static final String SKILL_ST= "start";
    //人物卡信息表
    public static final String MAIN_ID="id";
    public static final String MNIN_NAME="name";
    public static final String MAIN_SEX="sex";
    public static final String MAIN_TIMES="times";
    public static final String MAIN_LOCTION="loction";
    public static final String MAIN_HMTOWN="hometown";
    public static final String MAIN_STR="str";
    public static final String MAIN_CON="con";
    public static final String MAIN_SIZ="siz";
    public static final String MAIN_DEX="dex";
    public static final String MAIN_APP="app";
    public static final String MAIN_INT="int";
    public static final String MAIN_POW="pow";
    public static final String MAIN_EDU="edu";
    public static final String MAIN_LUCK="lukc";
    public static final String MAIN_AGE="age";
    public static final String MAIN_DB="db";
    public static final String MAIN_TX="tx";
    public static final String MAIN_SAN="san";
    public static final String MAIN_MOVE="move";
    //public static final String MAIN_LIFE="life";生命值
    public static final String MAIN_CAREER="career";
    //按照人物创建的技能信息表。获取人物卡创建个数和人物名作为主码
    public static final String NEWSKILL_ID= "id";//ID  1_name_n
    public static final String NEWSKILL_NAME= "name";
    public static final String NEWSKILL_ST= "start";

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
