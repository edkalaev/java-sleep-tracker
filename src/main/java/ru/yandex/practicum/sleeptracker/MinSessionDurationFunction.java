package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinSessionDurationFunction  implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long minDuration = sessions.stream()
                .mapToLong(session -> Duration.between(
                        session.getSleepTime(),
                        session.getWakeTime()
                ).toMinutes())
                .min()
                .orElse(0);

        return new SleepAnalysisResult(
                "Минимальная продолжительность сессии сна в минутах",
                minDuration
        );
    }
}
