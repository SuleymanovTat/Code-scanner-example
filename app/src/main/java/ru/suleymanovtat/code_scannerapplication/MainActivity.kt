package ru.suleymanovtat.code_scannerapplication

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.CodeScanner
import com.github.florent37.runtimepermission.kotlin.askPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnScanner.setOnClickListener {
            askPermission(
                Manifest.permission.CAMERA
            ) {
                //all of your permissions have been accepted by the user
                startActivityForResult(Intent(this, QrActivity::class.java), 120)
            }.onDeclined { e ->
                Toast.makeText(this, "Oups permission denied", Toast.LENGTH_LONG).show()
                //at least one permission have been declined by the user
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 120 && data != null) {
            val text = data.getStringExtra("result")
            Toast.makeText(this, "Scan result: ${text}", Toast.LENGTH_LONG).show()
        }
    }
}