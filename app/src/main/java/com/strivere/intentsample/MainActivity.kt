package com.strivere.intentsample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnMoveActivity : Button
    private lateinit var btnMoveActivityWithData : Button
    private lateinit var btnMoveActivityWithObject : Button
    private lateinit var btnDialUp : Button
    private lateinit var btnMoveForResult : Button
    private lateinit var tvResult : TextView

    companion object{
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMoveActivity = findViewById(R.id.btn_move_activity)
        btnMoveActivityWithData = findViewById(R.id.btn_move_activity_data)
        btnMoveActivityWithObject = findViewById(R.id.btn_move_activity_object)
        btnDialUp = findViewById(R.id.btn_dial_number)
        btnMoveForResult = findViewById(R.id.btn_move_for_result)

        tvResult = findViewById(R.id.tv_result)

        btnMoveActivity.setOnClickListener(this)
        btnMoveActivityWithData.setOnClickListener(this)
        btnMoveActivityWithObject.setOnClickListener(this)
        btnDialUp.setOnClickListener(this)
        btnMoveForResult.setOnClickListener(this)
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

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "Indra Yudha",
                    5,
                    "indrayudha.ind@gmail.com",
                    "Bandung"
                )

                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "087713246576"
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialIntent)
            }

            R.id.btn_move_for_result -> {
                val moveToResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                startActivityForResult(moveToResultIntent, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE){
            if (resultCode == MoveForResultActivity.RESULT_CODE){
                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                tvResult.text = "Hasil : $selectedValue"
            }
        }
    }
}
