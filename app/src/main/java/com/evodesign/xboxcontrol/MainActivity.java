package com.evodesign.xboxcontrol;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.provider.Settings;

import android.view.InputDevice;
import android.view.KeyEvent;

import com.ainirobot.coreservice.client.RobotApi;
import com.ainirobot.coreservice.client.listener.CommandListener;
import com.evodesign.xboxcontrol.R;

public class MainActivity extends AppCompatActivity {

    private static int reqId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button upButton = findViewById(R.id.upButton);
        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        Button downButton = findViewById(R.id.downButton);
        Button buttonA = findViewById(R.id.buttonA);
        Button buttonB = findViewById(R.id.buttonB);


// Inside onCreate method, after initializing your other buttons
        Button bluetoothButton = findViewById(R.id.bluetoothButton);
        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Bluetooth settings
                Intent intentBluetooth = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intentBluetooth);
            }
        });

//
//        upButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Handle button press (start moving forward)
//                        showToast("forward");
//                        RobotApi.getInstance().goForward(0, 0.4f, mMotionListener);
//                        return true; // Consume the event to prevent multiple ACTION_DOWN events
//
//                    case MotionEvent.ACTION_UP:
//                        // Handle button release (stop moving)
//                        showToast("stop");
//                        RobotApi.getInstance().stopMove(0, mMotionListener);
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
//
//
//        leftButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Handle button press (start moving forward)
//                        showToast("turn left");
//                        RobotApi.getInstance().turnLeft(0, 40, mMotionListener);
//                        return true; // Consume the event to prevent multiple ACTION_DOWN events
//
//                    case MotionEvent.ACTION_UP:
//                        // Handle button release (stop moving)
//                        showToast("stop");
//                        RobotApi.getInstance().stopMove(0, mMotionListener);
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
//
//        rightButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Handle button press (start moving forward)
//                        showToast("turn right");
//                        RobotApi.getInstance().turnRight(0, 40, mMotionListener);
//                        return true; // Consume the event to prevent multiple ACTION_DOWN events
//
//                    case MotionEvent.ACTION_UP:
//                        // Handle button release (stop moving)
//                        showToast("stop");
//                        RobotApi.getInstance().stopMove(0, mMotionListener);
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
//
//
//        downButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Handle button press (start moving forward)
//                        showToast("backward");
//                        RobotApi.getInstance().goBackward(0, 0.1f, mMotionListener);
//                        return true; // Consume the event to prevent multiple ACTION_DOWN events
//
//                    case MotionEvent.ACTION_UP:
//                        // Handle button release (stop moving)
//                        showToast("stop");
//                        RobotApi.getInstance().stopMove(0, mMotionListener);
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
//
//        buttonA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle A button click
//                showToast("A button clicked");
//                RobotApi.getInstance().moveHead(reqId++, "relative", "relative", 0, -10, mMotionListener);
//            }
//        });
//
//        buttonB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle B button click
//                showToast("B button clicked");
//                RobotApi.getInstance().moveHead(reqId++, "relative", "relative", 0, 10, mMotionListener);
//            }
//        });
    }


    private CommandListener mMotionListener = new CommandListener() {
        @Override
        public void onResult(int result, String message) {
            if ("succeed".equals(message)) {
            } else {
            }
        }
    };

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if ((event.getSource() & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK) {
            // Process joystick movement
            float x = event.getAxisValue(MotionEvent.AXIS_X);
            float y = event.getAxisValue(MotionEvent.AXIS_Y);

            // You can customize the following logic based on your needs
            if (x > 0.5) {
                // Joystick moved to the right
                RobotApi.getInstance().turnRight(0, 40, mMotionListener);
                showToast("Joystick moved to the right");
            } else if (x < -0.5) {
                // Joystick moved to the left
                RobotApi.getInstance().turnLeft(0, 40, mMotionListener);
                showToast("Joystick moved to the left");
            }

            else if (y > 0.5) {
                // Joystick moved down
                RobotApi.getInstance().goBackward(0, 0.1f, mMotionListener);
                showToast("Joystick moved down");
            } else if (y < -0.5) {
                // Joystick moved up
                RobotApi.getInstance().goForward(0, 0.4f, mMotionListener);
                showToast("Joystick moved up");
            }
            else if (x < 0.5 && y > -0.5) {
                // Joystick released, stop movement
                RobotApi.getInstance().stopMove(0, mMotionListener);
                showToast("Joystick released, stopping movement");
                return true;
            }

            return true;
        }
        return super.onGenericMotionEvent(event);
    }




}
