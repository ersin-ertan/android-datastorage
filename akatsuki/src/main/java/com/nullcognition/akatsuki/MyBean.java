package com.nullcognition.akatsuki;
// ersin 25/08/15 Copyright (c) 2015+ All rights reserved.

import com.sora.util.akatsuki.Retained;

// showcasing inheritance
public class MyBean extends RootBean{
	// <Typ extends String>
	// inheritance works if at least one @Retained field is in child - should be fixed
	@Retained public String retainedChild = "retained in child";
//	@Retained public Typ     retainedGeneric;
	@Retained        Model model;


	static class Model{

		@Retained String name = "defaultName";
	}

}



