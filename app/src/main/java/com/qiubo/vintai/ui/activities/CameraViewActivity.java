package com.qiubo.vintai.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.controls.Grid;
import com.qiubo.vintai.R;

public class CameraViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);

        final CameraView cameraView = findViewById(R.id.vw_camera_view);
        cameraView.setLifecycleOwner(this);
        cameraView.set(Grid.DRAW_3X3);

        final AppCompatButton pictureGrid = findViewById(R.id.picture_grid);
        pictureGrid.setText("3x3");
        pictureGrid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cameraView.getGrid() == Grid.DRAW_3X3) {
                    cameraView.set(Grid.DRAW_PHI);
                    pictureGrid.setText("PHI");
                } else {
                    cameraView.set(Grid.DRAW_3X3);
                    pictureGrid.setText("3x3");
                }

            }
        });
    }
}
