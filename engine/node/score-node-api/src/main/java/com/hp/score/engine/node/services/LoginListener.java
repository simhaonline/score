package com.hp.score.engine.node.services;

/**
 * Created by IntelliJ IDEA.
 * User: Amit Levin
 * Date: 21/11/12
 */
//TODO: Add Javadoc  Eliya
public interface LoginListener {

	public void preLogin(String uuid);
	
	public void postLogin(String uuid);
}
