package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SleepingSessionReaderTest {

    private final SleepingSessionReader reader = new SleepingSessionReader();

    @Test
    void shouldReadAllSessionsFromResourceFile() throws IOException {
        List<SleepingSession> sessions = reader.readFromFile(getResourcePath());
        assertEquals(13, sessions.size());
    }

    @Test
    void shouldReadFirstSessionCorrectlyFromResourceFile() throws IOException {
        List<SleepingSession> sessions = reader.readFromFile(getResourcePath());
        SleepingSession firstSession = sessions.get(0);

        assertEquals(LocalDateTime.of(2025, 10, 1, 23, 15), firstSession.getSleepTime());
        assertEquals(LocalDateTime.of(2025, 10, 2, 7, 30), firstSession.getWakeTime());
        assertEquals(SleepQuality.GOOD, firstSession.getQuality());
    }

    private String getResourcePath() {
        return Path.of("src", "test", "resources", "sleep_log.txt").toString();
    }
}
