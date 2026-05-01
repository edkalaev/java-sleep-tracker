package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SleepingSessionReader {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public List<SleepingSession> readFromFile(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(fileName));

        return lines.stream()
                .map(this::parseLine)
                .toList();
    }

    private SleepingSession parseLine(String line) {
        String[] parts = line.split(";");
        LocalDateTime sleepTime = LocalDateTime.parse(parts[0], FORMATTER);
        LocalDateTime wakeTime = LocalDateTime.parse(parts[1], FORMATTER);
        SleepQuality quality = SleepQuality.valueOf(parts[2]);

        return new SleepingSession(sleepTime, wakeTime, quality);
    }
}
