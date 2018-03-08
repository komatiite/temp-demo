package com.example.kcroz.demotemp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

// 1. Implement SensorEventListener interface
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // 2. Declare SensorManager, Sensor, and TextView (for display)
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView tvOutput;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3. Initialize SensorManager, Senor, and TextView
        tvOutput = findViewById(R.id.tvOutput);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        // 4. Check to see if sensor exists in device
        if (sensor == null) {

            // 5. Warn by Toast if absent
            Toast.makeText(this, "No ambient temperature sensor available!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 6. Register listener for sensor manager
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // 7. Grab temperature from sensor event
        float temperature = event.values[0];

        // 8. Display to screen
        tvOutput.setText(String.valueOf(temperature));
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Overridden for interface
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 9. Unregister listener if activity is paused
        sensorManager.unregisterListener(this);
    }
}
