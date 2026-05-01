package ru.yandex.practicum.sleeptracker;

import java.util.Objects;

public class SleepAnalysisResult {
    private String description;
    private Object value;

    public SleepAnalysisResult(String description, Object value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return description + ": " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepAnalysisResult that = (SleepAnalysisResult) o;
            return Objects.equals(description, that.description) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, value);
    }
}