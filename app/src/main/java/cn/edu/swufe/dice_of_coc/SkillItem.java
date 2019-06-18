package cn.edu.swufe.dice_of_coc;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import static org.litepal.LitePalApplication.getContext;

public class SkillItem {
    int id;
    private String skillName;
    private String skillStart;

    public SkillItem() {
        super();
        this.skillName = "";
    }

    public SkillItem(String careerName) {
        super();
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillStart() {
        return skillStart;
    }

    public void setSkillStart(String skillStart) {
        this.skillStart = skillStart;
    }

    public View setView(SkillItem item){
        View itemView = null;
        if (itemView==null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.layout_skill_item, null);
            //itemView=LayoutInflater.from(getContext()).inflate(getContext(),R.layout.layout_item, null));
        }

        //Map<String,String> map= (Map<String, String>) getItem(position);
        TextView skiName=(TextView)itemView.findViewById(R.id.skill_list_nametext);
        TextView skiStart=(TextView)itemView.findViewById(R.id.skill_point);
        TextView skiHalf=(TextView)itemView.findViewById(R.id.build_skill_half);
        TextView skiFif=(TextView)itemView.findViewById(R.id.build_skill_fif);


        skiName.setText(""+item.getSkillName());
        skiStart.setText(""+item.getSkillStart());
        skiHalf.setText(""+String.valueOf((int)Math.floor(Float.parseFloat(item.getSkillStart())/2)));
        skiFif.setText(""+String.valueOf((int)Math.floor(Float.parseFloat(item.getSkillStart())/5)));

        return itemView;

    }
}
