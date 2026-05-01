package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageSessionDurationFunctionTest {

    private final AverageSessionDurationFunction function = new AverageSessionDurationFunction();

    @Test
    void shouldReturnAverageSessionDuration() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 0),
                        LocalDateTime.of(2025, 10, 3, 5, 0),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 14, 0),
                        LocalDateTime.of(2025, 10, 3, 15, 1),
                        SleepQuality.BAD
                )
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Средняя продолжительность сессии сна в минутах", result.getDescription());
        assertEquals(300L, result.getValue());
    }

    @Test
    void shouldReturnZeroForEmptySessionsList() {
        List<SleepingSession> sessions = List.of();

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Средняя продолжительность сессии сна в минутах", result.getDescription());
        assertEquals(0L, result.getValue());
    }
}