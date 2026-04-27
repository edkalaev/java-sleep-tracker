package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> FUNCTIONS = List.of(
            new TotalSessionsCountFunction(),
            new MinSessionDurationFunction(),
            new MaxSessionDurationFunction(),
            new AverageSessionDurationFunction(),
            new BadQualitySessionsCountFunction(),
            new SleeplessNightsCountFunction(),
            new UserChronotypeFunction()
    );

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Не указан путь к файлу.");
            return;
        }

        SleepingSessionReader reader = new SleepingSessionReader();

        try {
            List<SleepingSession> sessions = reader.readFromFile(args[0]);

            FUNCTIONS.stream()
                    .map(function -> function.apply(sessions))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}