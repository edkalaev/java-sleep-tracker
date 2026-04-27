package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserChronotypeFunctionTest {

    private final UserChronotypeFunction function = new UserChronotypeFunction();

    @Test
    void shouldReturnOwlWhenOwlsAreMajority() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 9, 30),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 40),
                        LocalDateTime.of(2025, 10, 3, 10, 0),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 22, 30),
                        LocalDateTime.of(2025, 10, 4, 8, 0),
                        SleepQuality.BAD
                )
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Хронотип пользователя", result.getDescription());
        assertEquals(Chronotype.OWL, result.getValue());
    }

    @Test
    void shouldReturnLarkWhenLarksAreMajority() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 21, 30),
                        LocalDateTime.of(2025, 10, 2, 6, 30),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 21, 45),
                        LocalDateTime.of(2025, 10, 3, 6, 20),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 23, 30),
                        LocalDateTime.of(2025, 10, 4, 9, 30),
                        SleepQuality.BAD
                )
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Хронотип пользователя", result.getDescription());
        assertEquals(Chronotype.LARK, result.getValue());
    }


    @Test
    void shouldIgnoreDaySessionsAndSleeplessNights() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 9, 30),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 14, 0),
                        LocalDateTime.of(2025, 10, 3, 15, 0),
                        SleepQuality.NORMAL
                )
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals("Хронотип пользователя", result.getDescription());
        assertEquals(Chronotype.OWL, result.getValue());
    }
}
