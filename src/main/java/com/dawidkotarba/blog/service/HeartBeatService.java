package com.dawidkotarba.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Dawid Kotarba on 16.01.2016.
 */

@Named
@Slf4j
public class HeartBeatService {

    @PersistenceContext
    private EntityManager entityManager;

    @Scheduled(fixedDelay = 60000)
    public void doHeartBeat() {
        Query heartBeatQuery = entityManager.createNativeQuery("SELECT 1 FROM DUAL");
        log.info("Heartbeat result : " + heartBeatQuery.getSingleResult());
    }

}
