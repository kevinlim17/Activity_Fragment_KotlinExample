package com.example.mission3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mission3.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("IntentText")){
            binding.ReceivedText.setTextColor(resources.getColor(R.color.black, theme))
            binding.ReceivedText.setText(intent.getStringExtra("IntentText"))
        }
        else{
            Toast.makeText(this, "There is no value to receive", Toast.LENGTH_SHORT).show()
        }

        binding.switchToMain.setOnClickListener {
            val firstToMainIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("FirstToMain", binding.ReceivedText.text.toString())
            }
            setResult(RESULT_OK, firstToMainIntent)
            if (!isFinishing) finish()
        }

        binding.switchActivity2.setOnClickListener{
            val firstToSecondIntent = Intent(this, SecondActivity::class.java).apply {  }
            startActivity(firstToSecondIntent)
        }


    }
}