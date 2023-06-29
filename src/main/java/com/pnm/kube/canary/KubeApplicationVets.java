package com.pnm.kube.canary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

<<<<<<< HEAD
import io.opentelemetry.api.logs.GlobalLoggerProvider;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
=======
>>>>>>> branch 'main' of https://github.com/projects-demo/online-appointment-doctor-service/
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KubeApplicationVets {

	public static void main(String[] args) {
<<<<<<< HEAD

		new TelemetryConfig().main();
		log.debug("In Testing Logs KubeApplicationVets Service. debug");
		log.trace("In Testing Logs KubeApplicationVets Service. trace");
		log.info("In Testing Logs KubeApplicationVets Service. info");
=======
    	log.debug("In Testing Logs KubeApplicationVets Service. debug");
    	log.trace("In Testing Logs KubeApplicationVets Service. trace");
    	log.info("In Testing Logs KubeApplicationVets Service. info");
>>>>>>> branch 'main' of https://github.com/projects-demo/online-appointment-doctor-service/
		SpringApplication.run(KubeApplicationVets.class, args);

	}

}
