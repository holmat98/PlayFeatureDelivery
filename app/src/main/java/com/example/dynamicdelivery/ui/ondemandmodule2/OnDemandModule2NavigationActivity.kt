package com.example.dynamicdelivery.ui.ondemandmodule2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dynamicdelivery.databinding.ActivityModule4Binding
import com.example.dynamicdelivery.extensions.with
import com.example.dynamicdelivery.managers.LanguageAssistantEntryPoint
import com.google.android.play.core.splitcompat.SplitCompat
import dagger.hilt.android.EntryPointAccessors

class OnDemandModule2NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModule4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModule4Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun attachBaseContext(newBase: Context) {
        val languageAssistant = EntryPointAccessors.fromApplication(
            newBase,
            LanguageAssistantEntryPoint::class.java
        ).languageAssistant

        super.attachBaseContext(newBase with languageAssistant.language)
        SplitCompat.install(this)
    }

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, OnDemandModule2NavigationActivity::class.java)
    }
}