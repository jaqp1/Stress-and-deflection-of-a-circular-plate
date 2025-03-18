package edu.ppsm.lab04

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvF = findViewById(R.id.textF)
        tvR = findViewById(R.id.textR)
        tvH = findViewById(R.id.textH)
        tvSigma = findViewById(R.id.textSigma)
        tvUmax = findViewById(R.id.textUmax)

        showParams()

        val editActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
            if(result.resultCode == RESULT_OK){
                val data: Bundle? = result.data?.extras
                plate.f = data!!.getDouble("F")
                plate.r = data.getDouble("R")
                plate.h = data.getDouble("H")
                showParams()
            }
        }

        findViewById<View>(R.id.buttonSet).setOnClickListener{ v: View? ->
            val intent = Intent(this@MainActivity,EditActivity::class.java)
            intent.putExtra("F",plate.f)
            intent.putExtra("R",plate.r)
            intent.putExtra("H",plate.h)
            editActivityLauncher.launch(intent)
        }


    }

    private var plate = CPlate(r = 250.0,f = 1500.0, h = 4.5)

    private var tvF: TextView? = null
    private var tvR: TextView? = null
    private var tvH: TextView? = null
    private var tvSigma: TextView? = null
    private var tvUmax: TextView? = null

    private fun showParams(): Unit{
        tvF?.text = getString(R.string.sila).format(plate.f)
        tvR?.text = getString(R.string.promien).format(plate.r)
        tvH?.text = getString(R.string.grubosc).format(plate.h)
        tvSigma?.text = getString(R.string.naprezenie).format(plate.calculateSigma())
        tvUmax?.text = getString(R.string.ugiecie).format(plate.calculateUmax())
    }

}