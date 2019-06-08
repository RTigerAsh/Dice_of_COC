package cn.edu.swufe.dice_of_coc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

import static org.litepal.LitePalApplication.getContext;

public class WeaponItem {

    int ID;
    private String weaponName;
    private String weaponskills;
    private String weapondamage;
    private String weaponlimit1;
    private String weaponvartime;
    private String weaponnum;
    private String weaponprice;
    private String weaponfault;
    private String weapontime;

    public WeaponItem() {
        super();
        this.weaponName = "";
    }

    public WeaponItem(String weaponName) {
        super();
        this.weaponName = weaponName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getWeaponskills() {
        return weaponskills;
    }

    public void setWeaponskills(String weaponskills) {
        this.weaponskills = weaponskills;
    }

    public String getWeapondamage() {
        return weapondamage;
    }

    public void setWeapondamage(String weapondamage) {
        this.weapondamage = weapondamage;
    }

    public String getWeaponlimit1() {
        return weaponlimit1;
    }

    public void setWeaponlimit1(String weaponlimit1) {
        this.weaponlimit1 = weaponlimit1;
    }

    public String getWeaponvartime() {
        return weaponvartime;
    }

    public void setWeaponvartime(String weaponvartime) {
        this.weaponvartime = weaponvartime;
    }

    public String getWeaponnum() {
        return weaponnum;
    }

    public void setWeaponnum(String weaponnum) {
        this.weaponnum = weaponnum;
    }

    public String getWeaponprice() {
        return weaponprice;
    }

    public void setWeaponprice(String weaponprice) {
        this.weaponprice = weaponprice;
    }

    public String getWeaponfault() {
        return weaponfault;
    }

    public void setWeaponfault(String weaponfault) {
        this.weaponfault = weaponfault;
    }

    public String getWeapontime() {
        return weapontime;
    }

    public void setWeapontime(String weapontime) {
        this.weapontime = weapontime;
    }

    public View setView(WeaponItem item){
        View itemView = null;
        if (itemView==null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.layout_item, null);
            //itemView=LayoutInflater.from(getContext()).inflate(getContext(),R.layout.layout_item, null));
        }

        //Map<String,String> map= (Map<String, String>) getItem(position);
        TextView weaName=(TextView)itemView.findViewById(R.id.weaponName);
        TextView weaSkills=(TextView)itemView.findViewById(R.id.weaponSkills);
        TextView weaDamage=(TextView)itemView.findViewById(R.id.weaponDamage);
        TextView weaLimit1=(TextView)itemView.findViewById(R.id.weaponLimit1);
        TextView weaVartime=(TextView)itemView.findViewById(R.id.weaponVartime);
        TextView weaNum=(TextView)itemView.findViewById(R.id.weaponNum);
        TextView weaPrice=(TextView)itemView.findViewById(R.id.weaponPrice);
        TextView weaFault=(TextView)itemView.findViewById(R.id.weaponFault);
        TextView weaTime=(TextView)itemView.findViewById(R.id.weaponTime);

        weaName.setText(""+item.getWeaponName());
        weaSkills.setText(""+item.getWeaponskills());
        weaDamage.setText(""+item.getWeapondamage());
        weaLimit1.setText(""+item.getWeaponlimit1());
        weaVartime.setText(""+item.getWeaponvartime());
        weaNum.setText(""+item.getWeaponnum());
        weaPrice.setText(""+item.getWeaponprice());
        weaFault.setText(""+item.getWeaponfault());
        weaTime.setText(""+item.getWeapontime());

        return itemView;

    }
}
