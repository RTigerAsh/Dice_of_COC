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

    public View setView(WeaponItem item){
        View itemView = null;
        if (itemView==null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.layout_item, null);
            //itemView=LayoutInflater.from(getContext()).inflate(getContext(),R.layout.layout_item, null));
        }

        //Map<String,String> map= (Map<String, String>) getItem(position);
        TextView weaName=(TextView)itemView.findViewById(R.id.weaponName);

        weaName.setText(""+item.getWeaponName());

        return itemView;

    }
}
