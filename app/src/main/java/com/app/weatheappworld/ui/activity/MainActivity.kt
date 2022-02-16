package com.app.weatheappworld.ui.activity

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import com.app.weatheappworld.R
import com.app.weatheappworld.ui.BaseActivity
import java.util.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(getLang()=="en"){
            setLocale("en")
        }else{
            setLocale("ar")
        }
    }
    open fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res: Resources = resources
        val dm: DisplayMetrics = res.getDisplayMetrics()
        val conf: Configuration = res.getConfiguration()
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
//
//        val refresh = Intent(this,MainActivity::class.java)
//        finish()
//        startActivity(refresh)
    }
}