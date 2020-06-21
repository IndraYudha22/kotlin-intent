package com.strivere.intentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnMoveActivity : Button
    private lateinit var btnMoveActivityWithData : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMoveActivity = findViewById(R.id.btn_move_activity)
        btnMoveActivityWithData = findViewById(R.id.btn_move_activity_data)

        btnMoveActivity.setOnClickListener(this)
        btnMoveActivityWithData.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                val moveIntentWithData = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveIntentWithData.putExtra(MoveWithDataActivity.EXTRA_NAME, "Someone")
                moveIntentWithData.putExtra(MoveWithDataActivity.EXTRA_AGE, 5)
                startActivity(moveIntentWithData)
            }
        }
    }
}
