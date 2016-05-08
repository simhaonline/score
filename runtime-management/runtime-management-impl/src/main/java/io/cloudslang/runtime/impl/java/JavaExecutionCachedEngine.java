/*******************************************************************************
 * (c) Copyright 2014 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/

package io.cloudslang.runtime.impl.java;

import io.cloudslang.dependency.api.services.DependencyService;
import io.cloudslang.runtime.impl.ExecutionCachedEngine;
import org.python.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Genadi Rabinovich, genadi@hpe.com on 05/05/2016.
 */
@Component
public class JavaExecutionCachedEngine extends ExecutionCachedEngine<JavaExecutor> implements JavaExecutionEngine {
    @Autowired
    private DependencyService dependencyService;

    @Value("#{systemProperties['java.executor.cache.size'] != null ? systemProperties['java.executor.cache.size'] : 100}")
    private int cacheSize;

    @Override
    public Object execute(String dependency, String className, String methodName, Object ... args) {
        return allocateExecutor((dependency == null || dependency.isEmpty()) ? Sets.<String>newHashSet() :
                Sets.newHashSet(dependency)).execute(className, methodName, args);
    }

    @Override
    protected DependencyService getDependencyService() {
        return dependencyService;
    }

    @Override
    protected int getCacheSize() {
        return cacheSize;
    }

    @Override
    protected JavaExecutor createNewExecutor(Set<String> filePaths) {
        return new JavaExecutor(filePaths);
    }
}
