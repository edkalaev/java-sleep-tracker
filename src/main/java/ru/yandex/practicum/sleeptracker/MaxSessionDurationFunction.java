package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSessionDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long maxDuration = sessions.stream()
                .mapToLong(session -> Duration.between(
                        session.getSleepTime(),
                        session.getWakeTime()
                ).toMinutes())
                .max()
                .orElse(0);

        return new SleepAnalysisResult(
                "Максимальная продолжительность сессии сна в минутах",
                maxDuration
        );
    }
}
