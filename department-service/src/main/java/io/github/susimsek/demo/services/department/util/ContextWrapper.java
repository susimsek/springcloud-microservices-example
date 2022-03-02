package io.github.susimsek.demo.services.department.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
class ContextWrapper {

    private static ApplicationContext context;

    @Autowired
    public ContextWrapper(ApplicationContext ac) {
        context = ac;
    }

    public static ApplicationContext getContext() {
        return context;
    }

}