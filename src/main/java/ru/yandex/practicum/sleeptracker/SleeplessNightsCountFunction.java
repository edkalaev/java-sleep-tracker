package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsCountFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0L);
        }

        LocalDate firstNightDate = getFirstNightDate(sessions.getFirst());
        LocalDate lastNightDate = sessions.getLast().getWakeTime().toLocalDate();

        if (firstNightDate.isAfter(lastNightDate)) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0L);
        }

        long sleeplessNights = firstNightDate.datesUntil(lastNightDate.plusDays(1))
                .filter(nightDate -> {
                    long sessionsCount = sessions.stream()
                            .filter(session -> intersectsNight(session, nightDate))
                            .count();

                    return sessionsCount == 0;
                })
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей", sleeplessNights);
    }

    private LocalDate getFirstNightDate(SleepingSession session) {
        LocalDate sleepDate = session.getSleepTime().toLocalDate();
        LocalTime sleepTime = session.getSleepTime().toLocalTime();

        if (sleepTime.isBefore(LocalTime.NOON)) {
            return sleepDate;
        }

        return sleepDate.plusDays(1);
    }

    private boolean intersectsNight(SleepingSession session, LocalDate nightDate) {
        LocalDateTime nightStart = nightDate.atStartOfDay();
        LocalDateTime nightEnd = nightDate.atTime(6, 0);

        return session.getSleepTime().isBefore(nightEnd)
                && session.getWakeTime().isAfter(nightStart);
    }
}
