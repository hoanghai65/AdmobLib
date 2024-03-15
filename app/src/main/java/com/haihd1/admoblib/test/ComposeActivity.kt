package com.haihd1.admoblib.test

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner.BannerModel
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume.AppOpenAdManager
import com.haihd1.admoblib.test.ui.theme.AdmobLibTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdmobLibTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//                    Greeting("Android")
                    AdmobBanner(modifier = Modifier
                        .fillMaxWidth())
                    AppOpenAdManager.getInstance().disableAppOpenResumeActivity(this)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun AdmobBanner(modifier: Modifier = Modifier) {
    val bannerModel = BannerModel()
    Box( modifier = modifier.wrapContentSize()) {
        AndroidView(
            factory = { context ->
                val frameLayout = FrameLayout(context)
                bannerModel.initLayout(frameLayout)
                bannerModel.initView(context)
                bannerModel.loadBannerCompose(context)
                frameLayout
            },
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdmobLibTheme {
        Greeting("Android")
    }
}