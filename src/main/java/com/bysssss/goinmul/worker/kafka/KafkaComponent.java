package com.bysssss.goinmul.worker.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaComponent {
	@Value("${spring.kafka.template.default-topic}")
	public String topic;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendKafka(String topic, String key, String data) {
		ListenableFuture<SendResult<String, String>> res = kafkaTemplate.send(topic, key, data);
		log.info("KafkaComponent sendKafka() : key={}, isDone={}", key, res.isDone());
	}

	public void sendKafka(String key, String data) {
		ListenableFuture<SendResult<String, String>> res = kafkaTemplate.send(this.topic, key, data);
		log.info("KafkaComponent sendKafka() : key={}, isDone={}", key, res.isDone());
	}
	
	@KafkaListener(topics = "#{'${spring.kafka.template.default-topic}'.split(',')}", concurrency = "1")
	public void listenKafkaSentryKtr(
			@Payload String payload, // Acknowledgment acknowledgment,
			@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) String offset) {
		log.info("KafkaComponent listenKafka() : payload={}", payload);
		log.info("KafkaComponent listenKafka() : topic={}", topic);
		log.info("KafkaComponent listenKafka() : key={}", key);
		log.info("KafkaComponent listenKafka() : partition={}", partition);
		log.info("KafkaComponent listenKafka() : offset={}", offset);
	}
}
