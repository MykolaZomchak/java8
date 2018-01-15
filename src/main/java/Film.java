import java.time.Duration;
import java.time.LocalDate;

public class Film {

    private String name;
    private LocalDate date;
    private Duration runtime;

    public Film(String name, String date, int runtime) {
        this.name = name;
        this.date = LocalDate.parse(date);
        this.runtime = Duration.ofMinutes(runtime);
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Duration getRuntime() {
        return runtime;
    }

    @Override
    public String toString() {
        return name + ", " + date.toString() + ", " + runtime.toHours() + ":" + runtime.toMinutesPart();
    }
}
