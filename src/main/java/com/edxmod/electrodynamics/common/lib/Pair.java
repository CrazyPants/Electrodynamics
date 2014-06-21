package com.edxmod.electrodynamics.common.lib;

/**
 * @author dmillerw
 */
public class Pair<L, R> {

	public final L left;

	public final R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}
}