package com.doodleblue.doodlebluerestaurant

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget

class MainActivity : Activity() {

    lateinit var ivCover : ImageView
    lateinit var btnAdd1 : Button
    lateinit var btnAdd2 : Button
    lateinit var btnAdd3 : Button
    lateinit var ll1 : LinearLayout
    lateinit var ll2 : LinearLayout
    lateinit var ll3 : LinearLayout
    lateinit var tvInc1 : TextView
    lateinit var tvInc2 : TextView
    lateinit var tvInc3 : TextView
    lateinit var tvCount1 : TextView
    lateinit var tvCount2 : TextView
    lateinit var tvCount3 : TextView
    lateinit var tvDec1 : TextView
    lateinit var tvDec2 : TextView
    lateinit var tvDec3 : TextView
    lateinit var tvViewCart : TextView
    lateinit var rlViewCart : RelativeLayout
    var count : Int = 0
    var productCount : Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivCover = findViewById(R.id.ivCover) as ImageView
        btnAdd1 = findViewById(R.id.btnAdd1) as Button
        btnAdd2 = findViewById(R.id.btnAdd2) as Button
        btnAdd3 = findViewById(R.id.btnAdd3) as Button
        ll1 = findViewById(R.id.ll1) as LinearLayout
        ll2 = findViewById(R.id.ll2) as LinearLayout
        ll3 = findViewById(R.id.ll3) as LinearLayout
        tvInc1 = findViewById(R.id.tvInc1) as TextView
        tvInc2 = findViewById(R.id.tvInc2) as TextView
        tvInc3 = findViewById(R.id.tvInc3) as TextView
        tvCount1 = findViewById(R.id.tvCount1) as TextView
        tvCount2 = findViewById(R.id.tvCount2) as TextView
        tvCount3 = findViewById(R.id.tvCount3) as TextView
        tvDec1 = findViewById(R.id.tvDec1) as TextView
        tvDec2 = findViewById(R.id.tvDec2) as TextView
        tvDec3 = findViewById(R.id.tvDec3) as TextView
        tvViewCart = findViewById(R.id.tvViewCart) as TextView
        rlViewCart = findViewById(R.id.rlViewCart) as RelativeLayout


        btnAdd1.setOnClickListener {
            ll1.visibility = View.VISIBLE
            btnAdd1.visibility = View.INVISIBLE
            tvCount1.text = "1"
            addItems(1)
        }

        btnAdd2.setOnClickListener {
            ll2.visibility = View.VISIBLE
            btnAdd2.visibility = View.INVISIBLE
            tvCount2.text = "1"
            addItems(1)
        }

        btnAdd3.setOnClickListener {
            ll3.visibility = View.VISIBLE
            btnAdd3.visibility = View.INVISIBLE
            tvCount3.text = "1"
            addItems(1)
        }

        tvInc1.setOnClickListener {
            if (tvCount1.text.toString().toInt() < 20)
            {
                tvCount1.text = (tvCount1.text.toString().toInt() + 1).toString()
                addItems(1)
            }
        }

        tvInc2.setOnClickListener {
            if (tvCount2.text.toString().toInt() < 20)
            {
                tvCount2.text = (tvCount2.text.toString().toInt() + 1).toString()
                addItems(1)
            }
        }

        tvInc3.setOnClickListener {
            if (tvCount3.text.toString().toInt() < 20)
            {
                tvCount3.text = (tvCount3.text.toString().toInt() + 1).toString()
                addItems(1)
            }
        }

        tvDec1.setOnClickListener {
            tvCount1.text = (tvCount1.text.toString().toInt() - 1).toString()

            if (tvCount1.text.toString().toInt() == 0)
            {
                btnAdd1.visibility = View.VISIBLE;
                ll1.visibility = View.INVISIBLE;
            }
            addItems(-1)
        }

        tvDec2.setOnClickListener {
            tvCount2.text = (tvCount2.text.toString().toInt() - 1).toString()

            if (tvCount2.text.toString().toInt() == 0)
            {
                btnAdd2.visibility = View.VISIBLE;
                ll2.visibility = View.INVISIBLE;
            }
            addItems(-1)
        }

        tvDec3.setOnClickListener {
            tvCount3.text = (tvCount3.text.toString().toInt() - 1).toString()

            if (tvCount3.text.toString().toInt() == 0)
            {
                btnAdd3.visibility = View.VISIBLE;
                ll3.visibility = View.INVISIBLE;
            }
            addItems(-1)
        }

        rlViewCart.setOnClickListener{
            productCount = 0
            if (!tvCount1.text.equals("0"))
            {
                productCount = productCount + 1
            }
            if (!tvCount2.text.equals("0"))
            {
                productCount = productCount + 1
            }
            if (!tvCount3.text.equals("0"))
            {
                productCount = productCount + 1
            }
            if (productCount == 0)
            {
                Toast.makeText(applicationContext,"Kindly add atleast one product !!",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent(this,MyCart::class.java)
                intent.putExtra("count1",tvCount1.text)
                intent.putExtra("count2",tvCount2.text)
                intent.putExtra("count3",tvCount3.text)
                intent.putExtra("productCount",productCount)
                intent.putExtra("totalAmount",((7*tvCount1.text.toString().toInt()) + (7*tvCount2.text.toString().toInt()) + (7*tvCount3.text.toString().toInt())).toString())
                startActivityForResult(intent, 0)
            }
        }
    }

    fun addItems(count1: Int ) {
        count = count + count1
        tvViewCart.text = "VIEW CART (" + count.toString() + " ITEMS)"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                tvCount1.text = data?.getStringExtra("count1")
                tvCount2.text = data?.getStringExtra("count2")
                tvCount3.text = data?.getStringExtra("count3")
                val total = tvCount1.text.toString().toInt()+tvCount2.text.toString().toInt()+tvCount3.text.toString().toInt()
                tvViewCart.text = "VIEW CART (" + total + " ITEMS)"
                if (tvCount1.text.equals("0"))
                {
                    btnAdd1.visibility = View.VISIBLE;
                    ll1.visibility = View.INVISIBLE;
                }
                if (tvCount2.text.equals("0"))
                {
                    btnAdd2.visibility = View.VISIBLE;
                    ll2.visibility = View.INVISIBLE;
                }
                if (tvCount3.text.equals("0"))
                {
                    btnAdd3.visibility = View.VISIBLE;
                    ll3.visibility = View.INVISIBLE;
                }
            }
        }
    }
}