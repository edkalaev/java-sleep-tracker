package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime sleepTime;
    private final LocalDateTime wakeTime;
    private final SleepQuality quality;

    public SleepingSession(LocalDateTime sleepTime, LocalDateTime wakeTime, SleepQuality quality) {
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
        this.quality = quality;
    }

    public LocalDateTime getSleepTime() {
        return sleepTime;
    }

    public LocalDateTime getWakeTime() {
        return wakeTime;
    }

    public SleepQuality getQuality() {
        return quality;
    }
}
