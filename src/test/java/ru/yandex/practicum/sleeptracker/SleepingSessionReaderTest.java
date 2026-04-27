package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SleepingSessionReaderTest {

    private final SleepingSessionReader reader = new SleepingSessionReader();

    @Test
    void shouldReadAllSessionsFromResourceFile() throws IOException, URISyntaxException {
        List<SleepingSession> sessions = reader.readFromFile(getResourcePath());

        assertEquals(13, sessions.size());
    }

    @Test
    void shouldReadFirstSessionCorrectlyFromResourceFile() throws IOException, URISyntaxException {
        List<SleepingSession> sessions = reader.readFromFile(getResourcePath());
        SleepingSession firstSession = sessions.getFirst();

        assertEquals(LocalDateTime.of(2025, 10, 1, 23, 15), firstSession.getSleepTime());
        assertEquals(LocalDateTime.of(2025, 10, 2, 7, 30), firstSession.getWakeTime());
        assertEquals(SleepQuality.GOOD, firstSession.getQuality());
    }

    private String getResourcePath() throws URISyntaxException {
        return Path.of(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("sleep_log.txt")
                ).toURI()
        ).toString();
    }
}
