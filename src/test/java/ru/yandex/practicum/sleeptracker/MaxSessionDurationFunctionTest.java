package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxSessionDurationFunctionTest {

    private final MaxSessionDurationFunction function = new MaxSessionDurationFunction();

    @Test
    void shouldReturnMaxSessionDuration() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 15),
                        LocalDateTime.of(2025, 10, 2, 8, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 14, 30),
                        LocalDateTime.of(2025, 10, 3, 15, 20),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 23, 30),
                        LocalDateTime.of(2025, 10, 4, 6, 20),
                        SleepQuality.BAD
                )
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Максимальная продолжительность сессии сна в минутах", result.getDescription());
        assertEquals(585L, result.getValue());
    }

    @Test
    void shouldReturnZeroForEmptySessionsList() {
        List<SleepingSession> sessions = List.of();

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Максимальная продолжительность сессии сна в минутах", result.getDescription());
        assertEquals(0L, result.getValue());
    }
}