package com.study.activitypermissionclient

import android.content.ComponentName
import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_permission.setOnClickListener {
            Intent("com.study.permission_activity.view").apply {
                startActivity(this)
            }
        }

        bt_special_component.setOnClickListener {
            Intent(Intent.ACTION_MAIN).apply {
                val component = ComponentName(
                    "com.study.activitypermissionserver",
                    "com.study.activitypermissionserver.SpecialActivity"
                )
                setComponent(component)
                putExtra("client", "我来自Client")
                startActivity(this)
            }
        }

        bt_other_app.setOnClickListener {
            packageManager.getLaunchIntentForPackage("com.study.activitypermissionserver")?.apply {
                putExtra("client", "我来自Client")
                //添加这个会新开一个进程，如果不添加这个就是在当前进程打开。当前这种方式打开，不添加这个标记也可以在新进程中打开应用
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
            }
        }

        bt_uri_app.setOnClickListener {
            Intent().apply {
                data =
                    Uri.parse("openstudy://com.study.activitypermissionserver/permission?client=我来自client")
                //添加这个会新开一个进程，如果不添加这个就是在当前进程打开
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
            }
        }

        bt_run_app.setOnClickListener {
//            Log.e("MainActivity::",Utils.getAppOpenIntentByPackageName(this,"com.study.activitypermissionserver").toString())
            Utils.openPackage(MainActivity@this,"com.study.activitypermissionserver")
        }

        bt_test_reflect.setOnClickListener {
//            Log.e("MainActivity::", ReflectionLimit.clearLimit().toString())
            //反射创建AssetManager 与 Resource
            val assetManager = AssetManager::class.java.newInstance()
            //资源路径设置 目录或压缩包
            val addAssetPath = assetManager.javaClass.getMethod(
                "addAssetPath",
                String::class.java
            )
            Log.e("MainActivity::", assetManager.toString())
            Log.e("MainActivity::", addAssetPath.toString())
        }
    }
}
