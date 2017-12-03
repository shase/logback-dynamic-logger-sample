import java.nio.charset.StandardCharsets;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

public class GeneralLoggerFactory {
	public Logger getLogger(String loggerName, String path) {

		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		PatternLayoutEncoder ple = new PatternLayoutEncoder();
		ple.setPattern("%msg%n");
		ple.setContext(lc);
		ple.setCharset(StandardCharsets.UTF_8);
		ple.start();

		RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
		TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<>();
		rollingPolicy.setFileNamePattern(path);
		rollingPolicy.setMaxHistory(14);
		rollingPolicy.setParent(fileAppender);
		rollingPolicy.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
		rollingPolicy.start();

		fileAppender.setAppend(true);
		fileAppender.setEncoder(ple);
		fileAppender.setRollingPolicy(rollingPolicy);
		fileAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
		fileAppender.start();

		Logger logger = (Logger) LoggerFactory.getLogger(loggerName);
		logger.addAppender(fileAppender);
		logger.setLevel(Level.INFO);
		logger.setAdditive(false);

		return logger;
	}
}
