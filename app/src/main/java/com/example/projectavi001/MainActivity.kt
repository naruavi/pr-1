package com.example.projectavi001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AddPaymentDetailViewModel
    private var isObserverSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var mImageView = findViewById<ImageView>(R.id.imageview_id)
//        var original = BitmapFactory.decodeResource(resources, R.drawable.someimage5)
//        var mask = BitmapFactory.decodeResource(resources, R.drawable.mask2)
//        var result = Bitmap.createBitmap(mask.width, mask.height, Bitmap.Config.ARGB_8888)
//        var mCanvas = Canvas(result)
//        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
//        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
//        mCanvas.drawBitmap(original, 0f, 0f, null);
//        mCanvas.drawBitmap(mask, 0f, 0f, paint);
//        paint.xfermode = null;
//        mImageView.setImageBitmap(result);
//        mImageView.scaleType = ImageView.ScaleType.CENTER;
//        mImageView.setBackgroundResource(R.drawable.mask)

        viewModel = ViewModelProviders.of(this).get(AddPaymentDetailViewModel::class.java)
        setObservers()
        supportFragmentManager.beginTransaction()
            .replace(R.id.ll_home_container, HomeFragment()).commit()
    }

    private fun setObservers() {
        viewModel.addPaymentBtn.observe(
            this, Observer {
                if (it) {
                    startActivity(Intent(this, EventActivity::class.java))
//                    supportFragmentManager.beginTransaction()
//                        .addToBackStack(AddPaymentDetailFragment::class.java.simpleName)
//                        .replace(R.id.ll_home_container, AddPaymentDetailFragment()).commit()
                }
            }
        )
//        viewModel.updatePaymentDetail.observe(
//            this, Observer {
//                if(isObserverSet){
//                    val fragment = AddPaymentDetailFragment.newInstance(it, "update")
//                    supportFragmentManager.beginTransaction()
//                        .addToBackStack(AddPaymentDetailFragment::class.java.simpleName)
//                        .replace(R.id.ll_home_container, fragment).commit()
//                }
//            }
//        )
    }
}
