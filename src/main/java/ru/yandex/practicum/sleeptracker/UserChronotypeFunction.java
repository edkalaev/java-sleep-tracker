package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class UserChronotypeFunction
        implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Хронотип пользователя", Chronotype.PIGEON);
        }

        LocalDate firstNightDate = getFirstNightDate(sessions.getFirst());
        LocalDate lastNightDate = sessions.getLast().getWakeTime().toLocalDate();

        List<Chronotype> chronotypes = firstNightDate.isAfter(lastNightDate)
                ? List.of()
                : firstNightDate.datesUntil(lastNightDate.plusDays(1))
                .map(nightDate -> getNightSession(sessions, nightDate))
                .filter(session -> session != null)
                .map(this::getNightChronotype)
                .toList();

        long owlsCount = chronotypes.stream()
                .filter(chronotype -> chronotype == Chronotype.OWL)
                .count();

        long larksCount = chronotypes.stream()
                .filter(chronotype -> chronotype == Chronotype.LARK)
                .count();

        long pigeonsCount = chronotypes.stream()
                .filter(chronotype -> chronotype == Chronotype.PIGEON)
                .count();

        Chronotype userChronotype = getUserChronotype(owlsCount, larksCount, pigeonsCount);

        return new SleepAnalysisResult("Хронотип пользователя", userChronotype);
    }

    private Chronotype getNightChronotype(SleepingSession session) {
        LocalTime sleepTime = session.getSleepTime().toLocalTime();
        LocalTime wakeTime = session.getWakeTime().toLocalTime();

        if (sleepTime.isAfter(LocalTime.of(23, 0)) && wakeTime.isAfter(LocalTime.of(9, 0))) {
            return Chronotype.OWL;
        }

        if (sleepTime.isBefore(LocalTime.of(22, 0)) && wakeTime.isBefore(LocalTime.of(7, 0))) {
            return Chronotype.LARK;
        }

        return Chronotype.PIGEON;
    }

    private Chronotype getUserChronotype(long owlsCount, long larksCount, long pigeonsCount) {
        if (owlsCount > larksCount && owlsCount > pigeonsCount) {
            return Chronotype.OWL;
        }

        if (larksCount > owlsCount && larksCount > pigeonsCount) {
            return Chronotype.LARK;
        }

        return Chronotype.PIGEON;
    }

    private SleepingSession getNightSession(List<SleepingSession> sessions, LocalDate nightDate) {
        List<SleepingSession> nightSessions = sessions.stream()
                .filter(session -> intersectsNight(session, nightDate))
                .toList();

        if (nightSessions.isEmpty()) {
            return null;
        }

        LocalDateTime sleepTime = nightSessions.stream()
                .map(SleepingSession::getSleepTime)
                .min(LocalDateTime::compareTo)
                .orElseThrow();

        LocalDateTime wakeTime = nightSessions.stream()
                .map(SleepingSession::getWakeTime)
                .max(LocalDateTime::compareTo)
                .orElseThrow();

        return new SleepingSession(sleepTime, wakeTime, SleepQuality.NORMAL);
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