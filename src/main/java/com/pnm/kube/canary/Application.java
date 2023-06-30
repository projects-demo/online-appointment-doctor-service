package com.pnm.kube.canary;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.logs.Severity;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Scope;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.message.MapMessage;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Application {

  private static final org.apache.logging.log4j.Logger log4jLogger =
      LogManager.getLogger("log4j-logger");
  private static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger("slf4j-logger");
  private static final java.util.logging.Logger julLogger = Logger.getLogger("jul-logger");

  private static final AttributeKey<String> adRequestTypeKey =
      AttributeKey.stringKey("app.ads.ad_request_type");
  private static final AttributeKey<String> adResponseTypeKey =
      AttributeKey.stringKey("app.ads.ad_response_type");
  
  public void main() {
    //initializeOpenTelemetry();

    // Route JUL logs to slf4j
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();

    // Log using log4j API
    maybeRunWithSpan(() -> log4jLogger.info("A log4j log message with a span"), true);
    Map<String, Object> mapMessage = new HashMap<>();
    mapMessage.put("key2", "value2");
    mapMessage.put("message2", "A log4j structured message");
    maybeRunWithSpan(() -> log4jLogger.info(new MapMessage<>(mapMessage)), true);
    ThreadContext.clearAll();

    maybeRunWithSpan(() -> slf4jLogger.info("A slf4j log message with a span"), true);

    maybeRunWithSpan(() -> julLogger.info("A julLogger log message with a span"), true);

    

    // Log using OpenTelemetry Log Bridge API
    // WARNING: This illustrates how to write appenders which bridge logs from
    // existing frameworks into the OpenTelemetry Log Bridge API. These APIs
    // SHOULD NOT be used by end users in place of existing log APIs (i.e. Log4j, Slf4, JUL).
    io.opentelemetry.api.logs.Logger customAppenderLogger =
        GlobalOpenTelemetry.get().getLogsBridge().get("custom-log-appender");
    Attributes attribs = Attributes.of(
            adRequestTypeKey, "req_value", adResponseTypeKey, "res_value");

	maybeRunWithSpan(() -> customAppenderLogger.logRecordBuilder().setSeverity(Severity.INFO)
			.setBody("A log message from a custom appender with a span")
			.setAllAttributes(attribs)
			.emit(), true);
  }

 
  private static void maybeRunWithSpan(Runnable runnable, boolean withSpan) {
    if (!withSpan) {
      runnable.run();
      return;
    }
    Span span = GlobalOpenTelemetry.getTracer("my-tracer").spanBuilder("my-span").startSpan();
    try (Scope unused = span.makeCurrent()) {
		span.setAttribute("myAttribute", "myValue");
		span.addEvent("myEvent");
		runnable.run();
    } finally {
      span.end();
    }
  }
}