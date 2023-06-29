package com.pnm.kube.canary;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.opentelemetry.api.trace.StatusCode;

<<<<<<< HEAD
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
=======
import lombok.extern.slf4j.Slf4j;

>>>>>>> branch 'main' of https://github.com/projects-demo/online-appointment-doctor-service/
@RestController
<<<<<<< HEAD
//@Slf4j
@Log4j2
public class HelloService {
	  private static final Logger logger = LogManager.getLogger(HelloService.class);
=======
@Slf4j
public class HelloService {	
		 @RequestMapping("/svc")
	    public String hello() {		 
		    	log.debug("In Testing Logs HelloService Service. debug");
		    	log.trace("In Testing Logs HelloService Service. trace");
		    	log.info("In Testing Logs HelloService Service. info");
		      return String.format("doctor-service This is hello from doctor service!!");	 
		      
	    }	
}
>>>>>>> branch 'main' of https://github.com/projects-demo/online-appointment-doctor-service/

	 // private static final Logger logger = LoggerFactory.getLogger(MyClass.class);
	 // private static final Tracer tracer = GlobalOpenTelemetry.getTracer("my-library-name", "1.0.0");
	  private static final Tracer tracer = GlobalOpenTelemetry.getTracer("doctor-service-name");
	  private static final Meter meter = GlobalOpenTelemetry.getMeter("doctor-service-name");
	private static final AttributeKey<String> adRequestTypeKey =
		      AttributeKey.stringKey("app.ads.ad_request_type");
		  private static final AttributeKey<String> adResponseTypeKey =
		      AttributeKey.stringKey("app.ads.ad_response_type");

	@RequestMapping("/svc")
	public String hello() throws IOException {
		new Application().main();

		log.debug("In Testing Logs HelloService Service. debug");
		log.trace("In Testing Logs HelloService Service. trace");
		log.info("In Testing Logs HelloService Service. info");
		logger.info("Hello Logger Info");
		log.info(
		          "Order saved {}",
		          StructuredArguments.keyValue("orderId", "12345"),
		          StructuredArguments.keyValue("orderAmount", 100.0));
		
	
	      Span span = Span.current();
	      span.setAttribute("app.ads.contextKeys", "value3");
	      span.setAttribute("app.ads.contextKeys.count", "value4");
	      span.addEvent(span.toString());
	      span.addEvent(
	              "Error", Attributes.of(AttributeKey.stringKey("exception.message"), span.toString()));
	          span.setStatus(StatusCode.ERROR);
		
	          return String.format("doctor-service This is hello from doctor service!!");

	}
/**	
	public void myMethod() {
	    Span span = tracer.spanBuilder("myMethod").startSpan();
	    try {
	      // Your code here
	      log.info("My log message with JSON structure", Attributes.of(adRequestTypeKey, "value1", adResponseTypeKey, "value2"));
	    } catch (Exception e) {
	      span.recordException(e);
	    } finally {
	      span.end();
	    }
	  }*/
}
