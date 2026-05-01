package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.util.Objects;

public class SleepingSession {
    private LocalDateTime sleepTime;
    private LocalDateTime wakeTime;
    private SleepQuality quality;

    public SleepingSession(LocalDateTime sleepTime, LocalDateTime wakeTime, SleepQuality quality) {
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
        this.quality = quality;
    }

    public LocalDateTime getSleepTime() {
        return sleepTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepingSession that = (SleepingSession) o;
        return Objects.equals(sleepTime, that.sleepTime) && Objects.equals(wakeTime, that.wakeTime) && quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sleepTime, wakeTime, quality);
    }

    public void setQuality(SleepQuality quality) {
        this.quality = quality;
    }

    public void setWakeTime(LocalDateTime wakeTime) {
        this.wakeTime = wakeTime;
    }

    public void setSleepTime(LocalDateTime sleepTime) {
        this.sleepTime = sleepTime;
    }

    public LocalDateTime getWakeTime() {
        return wakeTime;
    }

    public SleepQuality getQuality() {
        return quality;
    }
}
