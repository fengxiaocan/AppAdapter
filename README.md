# AppAdapterView
#### http://blog.csdn.net/lmj623565791/article/details/45460089；

方案根据【张鸿洋的博客】得到启发

大家在Android开发时，肯定会觉得屏幕适配是个尤其痛苦的事，各种屏幕尺寸适配起来蛋疼无比。

历经千辛万苦,终于找到了解决了Android开发最大的难题机型适配问题。

Android开发者最大的困难就是为各种各样的机型适配，一般来说我们开发只需要适配大中小三款大众机型就可以了，但是总会遇到无法适配的或者适配不理想的情况。

我就是一个悲催的人，最近在做一个项目，给客户开发一款门店广告机的app，超大的屏幕，大概30英寸的宽屏彩电（其实我不知道有多大，总之就是很大），结果在使用鸿洋大神的适配方案的时候，终于碰到头破血流。

这机器的屏幕是1080*1920的像素，但是就有50台普通机子加起来那么大的屏幕，怎么适配？客户还要求我给Android各种手机做一下适配，当时我就对他说很难，他问，你们安卓本身不是做了适配吗？我就呵呵了。。

之后我就尝试使用百分比布局的方法，但是在这台大机器上也碰到坑了，自定义View不起作用（手机端没问题）。。而且百分比还有个弊端，就是UI给你的标准尺寸你必须要慢慢计算出百分比才行。

所以我就想尽办法，后来终于想到鸿洋大神的方案，他是在Android开发应用时给手机做好各种适配，但是有个问题，有一些机器根本不能正常适配，比如我同事的客户的手机LG G5（1440X2560，适配文件上也有这个适配），简直就是少了好几个尺寸。

于是我就把鸿洋大神适配方案放到app安装到手机上那一刻做适配（聪明吧？哈哈哈）

那么我根据鸿洋大神的博客文章得到启发，找到了一种能够解决Android工程师和UI妹妹间的矛盾~UI给出一个固定尺寸的设计稿，然后你在编写布局的时候不用思考，无脑照抄上面标识的像素值，就能达到完美适配，理想丰不丰满~~。根本就不用再使用百分比适配，不用再慢慢计算百分比了。

从此，你就不必对Android各种不同的屏幕适配烦恼了？

####使用方法：

##步骤 1. Add the JitPack repository to your build file

gradle

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
##步骤 2. Add the dependency

	dependencies {
		compile 'com.github.fengxiaocan:AppAdapterView:v1.0'
	}
  
  然后在application的onCreate()内

  @Override
    public void onCreate() {
        super.onCreate();
        AppAdapt.initAdapt(this);
    }

    然后在布局文件中使用

     <com.fxc.adapt.LinearLayout
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_height="180"
        app:layout_marginTop="100"
        app:layout_width="180"/>
        
        这两个一定要有,如果你要指定宽高尺寸,就只能wrap_content

        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        
        180代表UI妹子给你的尺寸标注,默认的是720*1280的屏幕下做的标注,如果你的UI妹子比较特别,使用的是320*480标注怎么办?很简单,

        在application的onCreate()内
          @Override
          public void onCreate() {
              super.onCreate();
              AppAdapt.initAdapt(this,320,480);
          }


       从此以后,你就只直接按标注来写就搞定了.

       这些View布局我没做其他的改动,只对其进行尺寸的适配,兼容性非常好.

       View属性说明

textSize             字体大小是以宽度的尺寸为标准

textSizeH             字体大小是以高度的尺寸为标准

drawablePadding       以宽度的尺寸为标准

layout_width          布局的宽度,以宽度的尺寸为标准

layout_height         布局的高度,以高度的尺寸为标准

layout_padding        布局的padding,以宽度的尺寸为标准

layout_paddingLeft    布局的padding,以宽度的尺寸为标准

layout_paddingRight   布局的padding,以宽度的尺寸为标准

layout_paddingTop     布局的padding,以高度的尺寸为标准

layout_paddingBottom  布局的padding,以高度的尺寸为标准

layout_margin         布局的margin,以宽度的尺寸为标准

layout_marginLeft     布局的margin,以宽度的尺寸为标准

layout_marginRight    布局的margin,以宽度的尺寸为标准

layout_marginTop      布局的margin,以高度的尺寸为标准

layout_marginBottom   布局的margin,以高度的尺寸为标准

maxHeight             布局的maxHeight,以高度的尺寸为标准

maxWidth              布局的maxHeight,以宽度的尺寸为标准

minHeight             布局的maxHeight,以高度的尺寸为标准

minWidth              布局的maxHeight,以宽度的尺寸为标准

暂时无法实现shape中的radius的适配,已想到解决的方案,但还未能完全解决,解决完成后再更新

欢迎大家加我的QQ讨论,或使用email对我提出建议:

QQ:1066537317

email:fengxiaocan@gmail.com
    
#  #