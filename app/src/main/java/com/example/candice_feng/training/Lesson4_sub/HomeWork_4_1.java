package com.example.candice_feng.training.Lesson4_sub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.candice_feng.training.BaseActivity;
import com.example.candice_feng.training.R;

public class HomeWork_4_1 extends BaseActivity {
    private LinearLayout checkBoxGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_4_1);

        checkBoxGroup = findViewById(R.id.checkBoxGroup);
        final Button showToastButton = findViewById(R.id.showToastBtn);
        showToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = getString(R.string.topping);
                for (int i = 0; i < checkBoxGroup.getChildCount(); i++) {
                    CheckBox checkBox = (CheckBox) checkBoxGroup.getChildAt(i);
                    if (checkBox.isChecked())
                        selected += " " + checkBox.getText().toString();
                }

                showSelected(selected);
            }
        });

    }

    private void showSelected(String selected_text) {
        Toast.makeText(this, selected_text, Toast.LENGTH_SHORT).show();
    }
}
