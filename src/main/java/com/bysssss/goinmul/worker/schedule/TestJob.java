package com.bysssss.goinmul.worker.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bysssss.goinmul.worker.kafka.KafkaComponent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestJob {
	@Autowired
	private KafkaComponent kafkaComponent;
	
	public void polling(String test) {
		log.info("TestJob polling() : test={}", test);
		kafkaComponent.sendKafka("키", "데이터");
	}
}
