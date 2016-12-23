# RulerRecyclerAdapter
## 一个超轻量的Adapter实现一个尺子

* 超轻量
* 更多的自定义选项
* 监听刻度变化
* 简单易用，扩展性高

##预览

![](https://github.com/adgvcxz/RulerRecyclerView/blob/master/img/ruler1.gif)

##初始化

```java
recyclerView.setAdapter(new RulerAdapter.Builder(recyclerView).setLineAndScale(lineWidth, scaleWidth)
                .setNumberAndGroup(2700, 10)
                .setOnRulerScrollListener(this)
                .setLineLength(0.9f, 0.5f)
                .setTextSizeAndColor(12, Color.GRAY)
                .setEdge(true)
                .setScaleLineColor(Color.GRAY)
                .setStartScaleLine(300).build());
```

如果刻度很密集，滑动过程中会些许闪烁，可关闭硬件加速:``recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);``

##导入

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
	compile 'com.github.adgvcxz:RulerRecyclerView:1.0.0'
}
```

##下一步

* 增加一条中间刻度
* 支持垂直方向

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

