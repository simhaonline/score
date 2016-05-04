package io.cloudslang.dependency.impl.services;

/*******************************************************************************
 * (c) Copyright 2014 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/

import io.cloudslang.dependency.api.services.MavenConfig;

import java.io.File;

/**
 * @author Alexander Eskin
 */
@SuppressWarnings("unused")
public class MavenConfigImpl implements MavenConfig {
    public static final String MAVEN_REPO_LOCAL = "cloudslang.maven.repo.local";
    public static final String USER_HOME        = "user.home";
    @Override
    public String getLocalMavenRepoPath() {
        String defValue = System.getProperty(USER_HOME) + File.separator + ".m2" + File.separator + "repository";
        return System.getProperty(MAVEN_REPO_LOCAL, defValue);
    }

    @Override
    public String getRemoteMaveRepoUrl() {
        //TODO
        return null;
    }
}
