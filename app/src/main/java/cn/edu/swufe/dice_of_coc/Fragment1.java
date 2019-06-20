package cn.edu.swufe.dice_of_coc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page1, container, false);


        Button bt = (Button) view.findViewById(R.id.bt);
        ImageView imageView = view.findViewById(R.id.page1_img);
        final DiceDialog diceDialog = new DiceDialog(this.getActivity());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceDialog.show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceDialog.show();
            }
        });
        return view;
    }
}
