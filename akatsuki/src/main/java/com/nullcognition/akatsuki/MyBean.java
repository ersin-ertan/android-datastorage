package com.nullcognition.akatsuki;
// ersin 25/08/15 Copyright (c) 2015+ All rights reserved.

import com.sora.util.akatsuki.Retained;

// showcasing inheritance
public class MyBean<T extends String> extends RootBean{

	public MyBean(){super();}

	// inheritance works if at least one @Retained field is in child
	@Retained public String retainedChild = "retained in child";
	@Retained public T retainedGeneric;
}



