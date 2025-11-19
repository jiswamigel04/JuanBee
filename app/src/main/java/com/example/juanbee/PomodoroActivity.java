package com.example.juanbee;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PomodoroActivity extends AppCompatActivity {

    private TextView textTimer;
    private Button btnStart, btnPause, btnReset, btnBreak, btnBack;

    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;
    private boolean isBreakTime = false;

    private long pomodoroTime = 25 * 60 * 1000; // 25 mins
    private long breakTime = 5 * 60 * 1000; // 5 mins
    private long timeLeftInMillis = pomodoroTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        textTimer = findViewById(R.id.textTimer);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);
        btnBreak = findViewById(R.id.btnBreak);
        btnBack = findViewById(R.id.btnBack);

        updateTimerText();
        updateButtonStates(true, false, true);

        // Start Timer
        btnStart.setOnClickListener(v -> {
            if (!isTimerRunning) startTimer();
        });

        // Pause Timer
        btnPause.setOnClickListener(v -> {
            if (isTimerRunning) pauseTimer();
        });

        // Reset Timer
        btnReset.setOnClickListener(v -> resetTimer());

        // Break Button: only switches mode
        btnBreak.setOnClickListener(v -> {
            if (!isTimerRunning) {
                toggleBreakMode();
            } else {
                Toast.makeText(this, "Pause the timer first to switch mode!", Toast.LENGTH_SHORT).show();
            }
        });

        // Back Button
        btnBack.setOnClickListener(v -> finish());
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                updateButtonStates(true, false, true);

                if (isBreakTime) {
                    Toast.makeText(PomodoroActivity.this, "Break finished! Back to work.", Toast.LENGTH_SHORT).show();
                    toggleBreakMode(); // switch back to Pomodoro
                } else {
                    Toast.makeText(PomodoroActivity.this, "Pomodoro done! Time for a break!", Toast.LENGTH_SHORT).show();
                    toggleBreakMode(); // switch to break mode
                }
            }
        }.start();

        isTimerRunning = true;
        updateButtonStates(false, true, true); // Break button stays enabled
    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        isTimerRunning = false;
        updateButtonStates(true, false, true);
    }

    private void resetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        isTimerRunning = false;
        timeLeftInMillis = isBreakTime ? breakTime : pomodoroTime;
        updateTimerText();
        updateButtonStates(true, false, true);
    }

    private void toggleBreakMode() {
        isBreakTime = !isBreakTime;
        timeLeftInMillis = isBreakTime ? breakTime : pomodoroTime;
        updateTimerText();
        Toast.makeText(this, isBreakTime ? "Break mode (5 min)" : "Pomodoro mode (25 min)", Toast.LENGTH_SHORT).show();
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        textTimer.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void updateButtonStates(boolean startEnabled, boolean pauseEnabled, boolean breakEnabled) {
        btnStart.setEnabled(startEnabled);
        btnPause.setEnabled(pauseEnabled);
        btnBreak.setEnabled(breakEnabled);
    }
}
