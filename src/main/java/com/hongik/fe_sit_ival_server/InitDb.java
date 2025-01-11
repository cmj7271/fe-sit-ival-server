package com.hongik.fe_sit_ival_server;

import com.hongik.fe_sit_ival_server.organizer.domain.Organizer;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.organizerInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void organizerInit() {

            Organizer admin = Organizer.createMockOrganizer();
            em.persist(admin);
        }

    }
}
