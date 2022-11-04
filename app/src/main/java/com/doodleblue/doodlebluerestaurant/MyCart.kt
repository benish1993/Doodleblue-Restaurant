package com.doodleblue.doodlebluerestaurant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

class MyCart : Activity() {

    lateinit var backIcon : ImageView
    lateinit var tvTotalAmount : TextView
    lateinit var rl1 : RelativeLayout
    lateinit var rl2 : RelativeLayout
    lateinit var rl3 : RelativeLayout
    lateinit var view1 : View
    lateinit var view2 : View
    lateinit var view3 : View
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
    lateinit var tvShowMore : TextView
    var count : Int = 0
    var productCount : Int = 0
    lateinit var count1 : String
    lateinit var count2 : String
    lateinit var count3 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)

        backIcon = findViewById(R.id.backIcon) as ImageView
        tvTotalAmount = findViewById(R.id.tvTotalAmount) as TextView
        rl1 = findViewById(R.id.rl1) as RelativeLayout
        rl2 = findViewById(R.id.rl2) as RelativeLayout
        rl3 = findViewById(R.id.rl3) as RelativeLayout
        view1 = findViewById(R.id.view1) as View
        view2 = findViewById(R.id.view2) as View
        view3 = findViewById(R.id.view3) as View
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
        tvShowMore = findViewById(R.id.tvShowMore) as TextView

        val totalAmount = intent.getStringExtra("totalAmount")
        tvTotalAmount.text = "€" + totalAmount
        val productCount = intent.getIntExtra("productCount",0)
        count1 = intent.getStringExtra("count1").toString()
        count2 = intent.getStringExtra("count2").toString()
        count3 = intent.getStringExtra("count3").toString()

        tvCount1.text = count1
        tvCount2.text = count2
        tvCount3.text = count3

        if (count1.equals("0"))
        {
            rl1.visibility = View.GONE
            view1.visibility = View.GONE
            tvShowMore.visibility = View.GONE
        }
        if (count2.equals("0"))
        {
            rl2.visibility = View.GONE
            view2.visibility = View.GONE
            tvShowMore.visibility = View.GONE
        }
        if (count3.equals("0"))
        {
            rl3.visibility = View.GONE
            view3.visibility = View.GONE
            tvShowMore.visibility = View.GONE
        }
        if (!count1.equals("0") && !count2.equals("0") && !count3.equals("0"))
        {
            rl1.visibility = View.VISIBLE
            rl2.visibility = View.VISIBLE
            rl3.visibility = View.GONE
            view3.visibility = View.GONE
            tvShowMore.visibility = View.VISIBLE
        }

        backIcon.setOnClickListener{
            val intent = Intent()
            intent.putExtra("count1", tvCount1.text)
            intent.putExtra("count2", tvCount2.text)
            intent.putExtra("count3", tvCount3.text)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }


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

        tvShowMore.setOnClickListener {
            rl3.visibility = View.VISIBLE
            view3.visibility = View.VISIBLE
            tvShowMore.visibility = View.GONE
        }
    }

    fun addItems(count1: Int ) {
        count = count + count1
    }

    override fun onBackPressed()
    {
        val intent = Intent()
        intent.putExtra("count1", tvCount1.text)
        intent.putExtra("count2", tvCount2.text)
        intent.putExtra("count3", tvCount3.text)
        setResult(RESULT_OK, intent)
        finish()
    }
}