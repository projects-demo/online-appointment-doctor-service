package com.pnm.kube.canary;
import io.opentelemetry.api.OpenTelemetry;

import java.util.Collection;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;

import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.logs.GlobalLoggerProvider;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.metrics.OtlpGrpcMetricExporter;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;


import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessorBuilder;
import java.util.concurrent.TimeUnit;



public class TelemetryConfig {

	private static String oltpEndpointEnv = "my-otel-demo-otelcol-contrib"; // System.getenv("OTEL_EXPORTER_OTLP_ENDPOINT")
	private static  String oltpEndpoint = "http://101.132.174.5:4317/";

	public void initializeOpenTelemetry() {
//          value: service.name=$(OTEL_SERVICE_NAME),service.instance.id=$(OTEL_K8S_POD_UID),service.namespace=opentelemetry-demo,k8s.namespace.name=$(OTEL_K8S_NAMESPACE),k8s.node.name=$(OTEL_K8S_NODE_NAME),k8s.pod.name=$(OTEL_K8S_POD_NAME)
		
		Resource resource = Resource.getDefault()
				.merge(Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, "doctor-service-k8s")));
//						ResourceAttributes.CONTAINER_ID, System.getenv("OTEL_K8S_POD_NAME"),
//						ResourceAttributes.K8S_POD_UID, System.getenv("OTEL_K8S_POD_UID"),
//						ResourceAttributes.K8S_NODE_NAME, System.getenv("OTEL_K8S_NODE_NAME"),
//						ResourceAttributes.K8S_NAMESPACE_NAME, System.getenv("OTEL_K8S_NAMESPACE"))));
		
		//initializeOpenTelemetryLogs();

/**
		GlobalLoggerProvider
				.set(SdkLoggerProvider.builder()
						.setResource(Resource.getDefault().toBuilder()
								.put(ResourceAttributes.SERVICE_NAME, "doc-service-local").build())
						.addLogRecordProcessor(
								BatchLogRecordProcessor
										.builder(OtlpGrpcLogRecordExporter.builder()
												.setEndpoint(oltpEndpoint).build())
										.build())
						.build());
*/
/**		
		
				SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
				  .addSpanProcessor(BatchSpanProcessor.builder(OtlpGrpcSpanExporter.builder().setEndpoint(oltpEndpoint).build()).build())
				  .setResource(resource)
				  .build();

				SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder()
				  .registerMetricReader(PeriodicMetricReader.builder(OtlpGrpcMetricExporter.builder().setEndpoint(oltpEndpoint).build()).build())
				  .setResource(resource)
				  .build();
			
				SdkLoggerProvider sdkLoggerProvider = SdkLoggerProvider.builder()
				  .addLogRecordProcessor(BatchLogRecordProcessor.builder(OtlpGrpcLogRecordExporter.builder().setEndpoint(oltpEndpoint).build()).build())
				  .setResource(resource)
				  .build();

				OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
				  .setTracerProvider(sdkTracerProvider)
				  .setMeterProvider(sdkMeterProvider)
				  .setLoggerProvider(sdkLoggerProvider)
				  .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
				  .buildAndRegisterGlobal();
				
*/		
	}
	

	
	 private static void initializeOpenTelemetryLogs() {
		    OpenTelemetrySdk sdk =
		        OpenTelemetrySdk.builder()
		            .setTracerProvider(SdkTracerProvider.builder().setSampler(Sampler.alwaysOn()).build())
		            .setLoggerProvider(
		                SdkLoggerProvider.builder()
		                    .setResource(
		                        Resource.getDefault().toBuilder()
		                            .put(ResourceAttributes.SERVICE_NAME, "log4j-example")
		                            .build())
		                    .addLogRecordProcessor(
		                        BatchLogRecordProcessor.builder(
		                                OtlpGrpcLogRecordExporter.builder()
		                                    .setEndpoint(oltpEndpoint)
		                                    .build())
		                            .build())
		                    .build())
		            .build();
		    GlobalOpenTelemetry.set(sdk);

		    // Add hook to close SDK, which flushes logs
		    Runtime.getRuntime().addShutdownHook(new Thread(sdk::close));
		  }


	
	
	
	
	
	
	
	
}
