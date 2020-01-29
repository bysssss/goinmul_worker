package com.bysssss.goinmul.worker;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {
	@Autowired
	private Environment env;
	
	@Autowired
	private ApplicationContext appCtx;
	
	public static void main(String[] args) {
		String profile = System.getProperty("spring.profiles.active");
		if( !"local".equals(profile) && !"dev".equals(profile) && !"stg".equals(profile) && !"prod".equals(profile)) {
			log.error("main() : profile={}", profile);
			return;
		}
		
		SpringApplication.run(App.class, args);
    }
	
	@PostConstruct
    public void onInitiate() {
    	StringBuffer sb = new StringBuffer();
    	for( String profile : env.getActiveProfiles()) {
    		sb.append(profile).append(";");
		}
    	log.info("onInitiate() : {}", sb.toString());
    }

    @PreDestroy
    public void onTerminate() {
    	log.info("onTerminate() : appCtx={}", appCtx);
    	((ConfigurableApplicationContext)appCtx).close();
    	log.info("appContext.close()");
    }

	public void run(String... args) throws Exception {
		StringBuffer sb = new StringBuffer();
    	for( int i=0;i<args.length;++i) {
    		sb.append(args[i]).append(";");
		}
		log.info("run() : {}", sb.toString());
	}
}
