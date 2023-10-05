package com.tobyspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com.tobyspring.config.autoConfig.TomcatWebServerConfig",
                "com.tobyspring.config.autoConfig.DispatcherServletConfig"
        };
    }
}
