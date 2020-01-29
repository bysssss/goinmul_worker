package com.bysssss.goinmul.worker.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile("local")
@EnableScheduling
@Component
public class Scheduler {
	@Autowired
	private TestJob testJob;
	
	@Scheduled(cron = "0 0/1 * * * *")
    public void test() {

		// TODO : zookeeper 

		log.info("Scheduler test()");
		testJob.polling("테스트");
	}
}
