package com.nullcognition.akatsuki.v0_0_3;
// ersin 28/08/15 Copyright (c) 2015+ All rights reserved.


import com.sora.util.akatsuki.Retained;


public class BeanWithGeneric<myType extends String>{

// in this case, we cannot assing a default value because type is unknown except for the bounds,
// thus will be set to null and  be unclickable(invisible)
	@Retained public myType myType;
}
