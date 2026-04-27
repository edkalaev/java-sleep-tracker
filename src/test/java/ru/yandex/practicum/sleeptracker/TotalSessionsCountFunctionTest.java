package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalSessionsCountFunctionTest {

    private final TotalSessionsCountFunction function = new TotalSessionsCountFunction();

    @Test
    void shouldReturnCorrectSessionsCount() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 15),
                        LocalDateTime.of(2025, 10, 2, 8, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 0),
                        LocalDateTime.of(2025, 10, 3, 8, 0),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 14, 30),
                        LocalDateTime.of(2025, 10, 3, 15, 20),
                        SleepQuality.NORMAL
                )
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Общее количество сессий сна за период", result.getDescription());
        assertEquals(3, result.getValue());
    }

    @Test
    void shouldReturnZeroForEmptySessionsList() {
        List<SleepingSession> sessions = List.of();

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Общее количество сессий сна за период", result.getDescription());
        assertEquals(0, result.getValue());
    }
}