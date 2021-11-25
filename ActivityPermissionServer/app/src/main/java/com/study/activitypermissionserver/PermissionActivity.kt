package com.study.activitypermissionserver

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        Log.e("PermissionActivity",intent.data?.query.toString())
        tv.text = intent.data?.getQueryParameter("client")?:""
    }
}
