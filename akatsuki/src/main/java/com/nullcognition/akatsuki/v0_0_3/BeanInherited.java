package com.nullcognition.akatsuki.v0_0_3;
// ersin 28/08/15 Copyright (c) 2015+ All rights reserved.

import com.sora.util.akatsuki.Retained;

public class BeanInherited extends BeanRoot{

	@Retained String beanString = "beanString default";
}


class BeanRoot{

	@Retained String beanRootString = "beanRootString default";

}
