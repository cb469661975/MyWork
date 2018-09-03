package com.example.cheng.myapplication

import android.Manifest
import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.cheng.myapplication.cbus.CBus
import com.example.cheng.myapplication.exentions.dp2px
import com.example.cheng.myapplication.fresco.FrescoActiviy
import com.example.cheng.myapplication.jsbridge.vassonic.HostSonicRuntime

import com.example.cheng.myapplication.kotlin.KotlinActivity
import com.example.cheng.myapplication.kotlin.TestFragmentTransActivity
import com.example.cheng.myapplication.plugin.PluginActivity
import com.example.cheng.myapplication.proxy.ProxyActivity
import com.example.cheng.myapplication.ui.PaintTestActivity
import com.tencent.sonic.sdk.SonicConfig
import com.tencent.sonic.sdk.SonicEngine
import kotlinx.android.synthetic.main.ac_scroll.*
import kotlin.reflect.KProperty


/**
 * Created by biao.cheng on 2017/11/16.
 */

class ScrollStartActivity : BaseActivity() {

    companion object {
        const val MODE_DEFAULT = 0

        const val MODE_SONIC = 1

        const val MODE_SONIC_WITH_OFFLINE_CACHE = 2

        private val PERMISSION_REQUEST_CODE_STORAGE = 123
    }

    var tvText: String by StrClass()

    inner class StrClass {
        operator fun getValue(scrollStartActivity: ScrollStartActivity, property: KProperty<*>): String {
            Log.i("strClass", "你拿我的值干什么、")
            return "getMyWord"
        }

        operator fun setValue(scrollStartActivity: ScrollStartActivity, property: KProperty<*>, s: String) {
            tv_t1.text = s
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_scroll)

        if (hasPermission()) {
            init()
        } else {
            requestPermission()
        }

    }

    private fun hasPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        } else true
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (PERMISSION_REQUEST_CODE_STORAGE == requestCode) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermission()
            } else {
                init()
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun init() {
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(HostSonicRuntime(application), SonicConfig.Builder().build())
        }
    }

    var isShow = false
    var isAnim = false
    fun onClickAnimShow(v: View) {


        if (!isShow && !isAnim) {
            tvText = "我也不知道"
            isShow = !isShow
            isAnim = true

            var movey = if (isShow) 50f else -50f
            val animator = ObjectAnimator.ofFloat(tv_show, "translationY", -dp2px(50f).toFloat(), 0f)
            animator.duration = 400
            animator.interpolator = LinearInterpolator()
            animator.start()
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator?) {
                    isAnim = false; tv_show.visibility = View.VISIBLE
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
        } else {
            tvText = "我就是知道"
            isShow = !isShow
            val animator = ObjectAnimator.ofFloat(tv_show, "translationY", 0f, -dp2px(50f).toFloat())
            animator.duration = 400
            animator.interpolator = LinearInterpolator()
            animator.start()
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator?) {
                    isAnim = false
                    tv_show.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
        }
    }

    fun onClickViewPager(v: View) {
        startActivity(ViewPagerActivity::class.java)
    }

    fun onClickClipDrawable(v: View) {
        startActivity(TestActivity::class.java)
    }

    fun onClickLotteanim(view: View) {
        startActivity(LottieActivity::class.java)
    }

    fun onClickKotlin(view: View) {
        startActivity(KotlinActivity::class.java)
    }

    fun onClickTest(view: View) {
        startActivity(TestActivity::class.java)
    }

    fun onClickWebview(view: View) {
        startActivity(WebActivity::class.java)
    }

    fun onClickTablayout(view: View) {
        startActivity(TabLayoutActivity::class.java)
    }

    fun onClickCoord(view: View) {
        startActivity(CoordActivity::class.java)
    }

    fun onClickFragTestTras(view: View) {
        startActivity(TestFragmentTransActivity::class.java)
    }

    fun testViewTracker(view: View) {
        startActivity(TestViewTouchActivity::class.java)
    }

    fun onTestNestScroll(view: View) {
        startActivity(TestNestScrollActivity::class.java)
    }

    fun onWebJs(view: View) {
        val intent = Intent(this, TestWebActivity::class.java)
        intent.putExtra(TestWebActivity.PARAM_MODE, MODE_SONIC)
//        startActivity(TestWebActivity::class.java)
        startActivity(intent)
    }

    fun onPlugin(view: View) {
        startActivity(PluginActivity::class.java)
    }

    fun onProxy(view: View) {
        startActivity(ProxyActivity::class.java)
    }

    fun onConstaint(view: View) {
        startActivity(TestConstraintActivity::class.java)
    }
    fun onPaint(view: View) {
        startActivity(PaintTestActivity::class.java)
    }
    fun onRecycleView(view: View) {
        startActivity(RecycleViewActivity::class.java)
    }
    fun onFresco(view: View) {
        startActivity(FrescoActiviy::class.java)
    }
    fun onDatabinding(view: View) {
        startActivity(TestDataBindingActivity::class.java)
    }
}
