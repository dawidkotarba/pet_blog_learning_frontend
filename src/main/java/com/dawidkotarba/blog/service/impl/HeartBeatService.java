package com.dawidkotarba.blog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named
@Slf4j
class HeartBeatService {

    private static final int HEARTBEAT_SCHEDULE_SECS = 60 * 1000;

    @PersistenceContext
    private EntityManager entityManager;

    @Scheduled(fixedDelay = HEARTBEAT_SCHEDULE_SECS)
    public void doHeartBeat() {
        final Query heartBeatQuery = entityManager.createNativeQuery("SELECT 1 FROM DUAL");
        log.info("Heartbeat result : " + heartBeatQuery.getSingleResult());
    }

}
