# RulerRecyclerAdapter
## 一个超轻量的Adapter实现一个尺子

* 超轻量
* 更多的自定义选项(颜色，长度，间隔等)
* 监听刻度变化
* 可单独为每个刻度设置间隔以及长度(目前有三种刻度长度可设置)
* 简单易用，扩展性高

##预览

![](https://github.com/adgvcxz/RulerRecyclerView/blob/master/img/ruler1.gif)

##初始化

```java
recyclerView.setAdapter(new RulerAdapter.Builder(recyclerView).setLineAndScale(lineWidth, scaleWidth)
                .setNumberAndGroup(2700, 10)
                .setOnRulerScrollListener(this)
                .setLineLength(0.9f, 0.5f)
                .setSecondScale(5, 0.75f)
                .setTextSizeAndColor(12, Color.GRAY)
                .setEdge(true)
                .setScaleLineColor(Color.GRAY)
                .setStartScaleLine(10)
                .build());
```

如果刻度很密集，滑动过程中会有些许闪烁，可关闭硬件加速:``recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);``

##导入

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
	compile 'com.github.adgvcxz:RulerRecyclerView:1.1.0'
}
```

##下一步

* 支持垂直方向

##更新日志

####1.1.0:

* 修复主要是在刻度很少的情况下，右边界与左边界出现显示异常
* 修复主要刻度如果为奇数时，右边界宽度有1像素出入
* 新增一条中间刻度，可自由设置中间刻度的间隔已经长度，类似于支持上5cm处


####1.0.0:

* 基本完成一把尺子的所有功能

## LICENSE

    Copyright 2016 adgvcxz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

