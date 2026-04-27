package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SleeplessNightsCountFunctionTest {

    private final SleeplessNightsCountFunction function = new SleeplessNightsCountFunction();

    @Test
    void shouldReturnZeroForEmptySessionsList() {
        List<SleepingSession> sessions = List.of();

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Количество бессонных ночей", result.getDescription());
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldReturnZeroWhenEveryNightHasSleep() {
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
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 23, 30),
                        LocalDateTime.of(2025, 10, 4, 6, 20),
                        SleepQuality.BAD
                )
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Количество бессонных ночей", result.getDescription());
        assertEquals(0L, result.getValue());
    }

    @Test
    void shouldReturnOneSleeplessNightWhenThereIsOnlyDaySleep() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 22, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 7, 0),
                        LocalDateTime.of(2025, 10, 3, 11, 0),
                        SleepQuality.NORMAL
                )
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Количество бессонных ночей", result.getDescription());
        assertEquals(1L, result.getValue());
    }

    @Test
    void shouldCountSleeplessNightsCorrectlyAcrossDifferentMonths() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 31, 23, 0),
                        LocalDateTime.of(2025, 11, 1, 3, 0),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 11, 2, 23, 30),
                        LocalDateTime.of(2025, 11, 3, 6, 30),
                        SleepQuality.GOOD
                )
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Количество бессонных ночей", result.getDescription());
        assertEquals(1L, result.getValue());
    }
}
