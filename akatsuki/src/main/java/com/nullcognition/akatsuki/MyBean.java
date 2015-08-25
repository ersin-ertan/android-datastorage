package com.nullcognition.akatsuki;
// ersin 25/08/15 Copyright (c) 2015+ All rights reserved.

import com.sora.util.akatsuki.Retained;

public class MyBean{

	@Retained public       int    retained;
	@Retained              int    retainedProtected;
//	transient              int    notRetained;
//	@Retained(skip = true) String notRetained_forDebugging;
}
