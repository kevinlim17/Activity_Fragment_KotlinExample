package com.example.mission3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mission3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val receivedData = it.data?.getStringExtra("FirstToMain")
                binding.IntentDataText.setText(receivedData)
            }
        }

        binding.switchActivity.setOnClickListener{
            val mainToFirstIntent = Intent(this, FirstActivity::class.java).apply {
                putExtra("IntentText", binding.IntentDataText.text.toString())
            }
            //startActivity(mainToFirstIntent)
            getResultText.launch(mainToFirstIntent)
        }

    }
}