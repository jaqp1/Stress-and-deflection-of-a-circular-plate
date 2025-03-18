 package edu.ppsm.lab04

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editF = findViewById(R.id.editTextInsertF)
        editR = findViewById(R.id.editTextInsertR)
        editH = findViewById(R.id.editTextInsertH)

        val data = this.intent.extras
        editF?.setText(data?.getDouble("F").toString())
        editR?.setText(data?.getDouble("R").toString())
        editH?.setText(data?.getDouble("H").toString())

        findViewById<View>(R.id.buttonInsertOK).setOnClickListener{ view: View? ->
            val intent = Intent(this@EditActivity, MainActivity::class.java)
            try{
                intent.putExtra("F", java.lang.Double.valueOf(editF?.getText().toString()))
                intent.putExtra("R", java.lang.Double.valueOf(editR?.getText().toString()))
                intent.putExtra("H", java.lang.Double.valueOf(editH?.getText().toString()))
                setResult(RESULT_OK, intent)
                finish()
            }catch (E: NumberFormatException){
                Toast.makeText(
                    this@EditActivity, getString(R.string.incorrectData), Toast.LENGTH_LONG
                    ).show()
            }
        }
        findViewById<View>(R.id.buttonInsertCancel).setOnClickListener{ view: View? ->
            val intent = Intent(this@EditActivity, MainActivity::class.java)
            setResult(RESULT_CANCELED, intent)
            finish()
        }

    }

    private var editF: EditText? = null
    private var editR: EditText? = null
    private var editH: EditText? = null


}