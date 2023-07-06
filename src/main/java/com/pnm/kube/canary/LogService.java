package com.pnm.kube.canary;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.logs.Severity;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Scope;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.data.Body;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import io.opentelemetry.sdk.logs.data.LogRecordData;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.message.MapMessage;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

import io.opentelemetry.sdk.logs.data.Body;
import io.opentelemetry.sdk.logs.data.LogRecordData;
import io.opentelemetry.sdk.logs.export.LogRecordExporter;
import io.opentelemetry.sdk.logs.export.SimpleLogRecordProcessor;
@Component
public class LogService {
	@Value("classpath:Event-Structure-ERROR-sample.json")
	org.springframework.core.io.Resource resourceErrorEvent;
	
	private static final String jsonPayload = "{\r\n"
			+ "    \"jsonPayload\": {\r\n"
			+ "        \"key1\": \"val11\",\r\n"
			+ "		\"key2\": \"val2\"\r\n"
			+ "		}\r\n"
			+ "}";
	
  private static final org.apache.logging.log4j.Logger log4jLogger =
      LogManager.getLogger("log4j-logger");
  private static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger("slf4j-logger");
  private static final java.util.logging.Logger julLogger = Logger.getLogger("jul-logger");

  private static final AttributeKey<String> adRequestTypeKey =
      AttributeKey.stringKey("app.ads.ad_request_type");
  private static final AttributeKey<String> adResponseTypeKey =
      AttributeKey.stringKey("app.ads.ad_response_type");
  
  public void log() {
    //initializeOpenTelemetry();

    // Route JUL logs to slf4j
	    Body body = Body.string(jsonPayload);
	   // maybeRunWithSpan(() -> slf4jLogger.info(body.asString()), true);
	    slf4jLogger.info(body.asString());
	    slf4jLogger.info("Simple Logs");


	    String OTEL_LOGS_EXPORTER = System.getenv("OTEL_LOGS_EXPORTER");
		String OTEL_EXPORTER_OTLP_PROTOCOL = 	System.getenv("OTEL_EXPORTER_OTLP_PROTOCOL");

		System.err.println("3OTEL_LOGS_EXPORTER->" + OTEL_LOGS_EXPORTER);
		System.err.println("3OTEL_EXPORTER_OTLP_PROTOCOL->" + OTEL_EXPORTER_OTLP_PROTOCOL);
		
		 String jsonPayload2 = "{\r\n"
				+ "    \"jsonPayload2\": {\r\n"
				+ "        \"OTEL_LOGS_EXPORTER\": \""+OTEL_LOGS_EXPORTER+"\",\r\n"
				+ "		\"OTEL_EXPORTER_OTLP_PROTOCOL\": \""+OTEL_EXPORTER_OTLP_PROTOCOL+"\"\r\n"
				+ "		}\r\n"
				+ "}";
		
	    Body body2 = Body.string(jsonPayload2);
	    slf4jLogger.error(body2.asString());
	    
		File file; String jsonPayLoadErrorEvent = StringUtils.EMPTY;
		JSONObject json = null;
		try {
			file = resourceErrorEvent.getFile();
			jsonPayLoadErrorEvent = FileUtils.readFileToString(file);
			 json = new JSONObject(jsonPayLoadErrorEvent);  

		} catch (IOException e) {
			e.printStackTrace();
		}		
	    Body body3 = Body.string(jsonPayLoadErrorEvent);
	    slf4jLogger.error(body3.asString());
	    SLF4JBridgeHandler.removeHandlersForRootLogger();
	    SLF4JBridgeHandler.install();

    // Log using log4j API
   // maybeRunWithSpan(() -> log4jLogger.info("A log4j log message with a span"), true);
    Map<String, Object> mapMessage = new HashMap<>();
    mapMessage.put("key2", "value2");
    mapMessage.put("message2", "A log4j structured message");
  //  maybeRunWithSpan(() -> log4jLogger.info(new MapMessage<>(mapMessage)), true);
    ThreadContext.clearAll();

    //maybeRunWithSpan(() -> slf4jLogger.info("A slf4j log message with a span"), true);

    //maybeRunWithSpan(() -> julLogger.info("A julLogger log message with a span"), true);

    

    // Log using OpenTelemetry Log Bridge API
    // WARNING: This illustrates how to write appenders which bridge logs from
    // existing frameworks into the OpenTelemetry Log Bridge API. These APIs
    // SHOULD NOT be used by end users in place of existing log APIs (i.e. Log4j, Slf4, JUL).
    io.opentelemetry.api.logs.Logger customAppenderLogger =
        GlobalOpenTelemetry.get().getLogsBridge().get("custom-log-appender");

 /**   
    Attributes attribs = Attributes.of(
            adRequestTypeKey, "req_value", adResponseTypeKey, "res_value");

	maybeRunWithSpan(() -> customAppenderLogger.logRecordBuilder().setSeverity(Severity.INFO)
			.setBody("A log message from a custom appender with a span")
			.setAllAttributes(attribs)
			.emit(), true);
			
*/
  }

 
  private static void maybeRunWithSpan(Runnable runnable, boolean withSpan) {
	 // withSpan = false;
   if (!withSpan) 
    {
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
