package com.pnm.kube.canary;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.opentelemetry.api.trace.StatusCode;

import io.opentelemetry.api.logs.GlobalLoggerProvider;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.logs.*;
import io.opentelemetry.sdk.logs.LogRecordProcessor;

//import io.opentelemetry.sdk.logging.LogRecordBuilder;
//import io.opentelemetry.sdk.logging.LoggingConfiguration;
//import io.opentelemetry.sdk.logging.LoggingExporter;
//import io.opentelemetry.sdk.logging.data.LogRecordBuilderSdk;
//import io.opentelemetry.sdk.logging.data.LogRecordSdk;

import io.opentelemetry.sdk.logs.export.LogRecordExporter;
import io.opentelemetry.api.logs.LogRecordBuilder;
import io.opentelemetry.api.trace.TracerProvider;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.logs.LogRecordBuilder;
import io.opentelemetry.api.metrics.Meter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
//@Slf4j
@Log4j2
public class HelloService {
	  private static final Logger logger = LogManager.getLogger(HelloService.class);
	@RequestMapping("/svc")
	public String hello() throws IOException {

		new LogService().log();

		String OTEL_LOGS_EXPORTER = System.getenv("OTEL_LOGS_EXPORTER");
		String OTEL_EXPORTER_OTLP_PROTOCOL = 	System.getenv("OTEL_EXPORTER_OTLP_PROTOCOL");

		System.err.println("2OTEL_LOGS_EXPORTER->" + OTEL_LOGS_EXPORTER);
		System.err.println("2OTEL_EXPORTER_OTLP_PROTOCOL->" + OTEL_EXPORTER_OTLP_PROTOCOL);
		
		log.debug("In Testing Logs HelloService Service. debug");
		log.trace("In Testing Logs HelloService Service. trace");
		log.info("In Testing Logs HelloService Service. info");
		log.info(
		          "Order saved {}",
		          StructuredArguments.keyValue("orderId", "12345"),
		          StructuredArguments.keyValue("orderAmount", 100.0));
		
	
	   
	          return String.format("doctor-service This is hello from doctor service!!");

	}

}
