package com.nullcognition.akatsuki;
// ersin 25/08/15 Copyright (c) 2015+ All rights reserved.


import com.sora.util.akatsuki.Retained;

public class RootBean{
	public RootBean(){}
	@Retained public    String retained          = "bean.retained default";
	@Retained protected String retainedProtected = "bean.retainedPro default";
	transient public       int notRetained;
	@Retained(skip = true) int notRetained_forDebugging;
}
