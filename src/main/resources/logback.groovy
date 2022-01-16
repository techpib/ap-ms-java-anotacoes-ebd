import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import java.nio.charset.Charset
import static ch.qos.logback.classic.Level.*

appender("CONSOLE", ConsoleAppender) {
    filter(ThresholdFilter) {
        level = INFO
    }

    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %class.%M %L - %msg%n"
        charset = Charset.forName("utf8")
    }
}

logger("br.com.techpib.ap.ms_anotacoes_ebd", INFO)
root(DEBUG, ["CONSOLE"])