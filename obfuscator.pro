

-injars      'xXx_dir_xXx/injars'
-libraryjars 'xXx_dir_xXx/libraryjars'
-outjars     'xXx_dir_xXx/outjars'

-libraryjars '<java.home>/lib/rt.jar'
-libraryjars '<java.home>/lib/jce.jar'


-ignorewarnings
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers


-repackageclasses com.bobsgame.client

-keep public class com.bobsgame.client.ClientMain
{
	public *;
}
