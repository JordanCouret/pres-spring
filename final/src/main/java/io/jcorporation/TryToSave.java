package io.jcorporation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TryToSave implements ApplicationRunner {
    private final static Logger log = LoggerFactory.getLogger(TryToSave.class);

    private final List<IUserService> userServiceList;
    private final UserService userService;

    public TryToSave(List<IUserService> userServiceList, UserService userService) {
        this.userServiceList = userServiceList;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("{} beans trouvées pour l'interface {}", userServiceList.size(), IUserService.class.getSimpleName());
        log.info("On utilise tout de même la class {}", userService.getClass());
        userService.create();
    }
}
