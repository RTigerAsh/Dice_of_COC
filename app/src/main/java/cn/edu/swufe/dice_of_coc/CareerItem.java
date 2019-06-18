package cn.edu.swufe.dice_of_coc;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import static org.litepal.LitePalApplication.getContext;

public class CareerItem {
    int id;
    private String careerName;
    private String careerReputation;
    private String careerAttributes;
    private String careerSkill;

    public CareerItem() {
        super();
        this.careerName = "";
    }

    public CareerItem(String careerName) {
        super();
        this.careerName = careerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public String getCareerReputation() {
        return careerReputation;
    }

    public void setCareerReputation(String careerReputation) {
        this.careerReputation = careerReputation;
    }

    public String getCareerAttributes() {
        return careerAttributes;
    }

    public void setCareerAttributes(String careerAttributes) {
        this.careerAttributes = careerAttributes;
    }

    public String getCareerSkill() {
        return careerSkill;
    }

    public void setCareerSkill(String careerSkill) {
        this.careerSkill = careerSkill;
    }

    public View setView(CareerItem item){
        View itemView = null;
        if (itemView==null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.layout_career_item, null);
            //itemView=LayoutInflater.from(getContext()).inflate(getContext(),R.layout.layout_item, null));
        }

        //Map<String,String> map= (Map<String, String>) getItem(position);
        TextView carName=(TextView)itemView.findViewById(R.id.careerName);
        TextView carReputation=(TextView)itemView.findViewById(R.id.careerReputation);
        TextView carAttributes=(TextView)itemView.findViewById(R.id.careerAttributes);
        TextView carSkill=(TextView)itemView.findViewById(R.id.careerSkill);


        carName.setText(""+item.getCareerName());
        carReputation.setText(""+item.getCareerReputation());
        carAttributes.setText(""+item.getCareerAttributes());
        carSkill.setText(""+item.getCareerSkill());


        return itemView;

    }
}
