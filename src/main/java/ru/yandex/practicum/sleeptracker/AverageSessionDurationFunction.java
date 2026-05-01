package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageSessionDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long averageDuration = (long) sessions.stream()
                .mapToLong(session -> Duration.between(
                        session.getSleepTime(),
                        session.getWakeTime()
                ).toMinutes())
                .average()
                .orElse(0);

        return new SleepAnalysisResult(
                "Средняя продолжительность сессии сна в минутах",
                averageDuration
        );
    }
}