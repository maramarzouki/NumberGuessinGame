package tn.m1pdam.numberguessinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var beginnerBtn : Button;
    lateinit var expertBtn : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beginnerBtn = findViewById(R.id.beginnerBtn);
        expertBtn = findViewById(R.id.expertBtn);

        beginnerBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, BeginnerPage::class.java);
                startActivity(intent);
            }
        })

        expertBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, ExpertStartPage::class.java);
                startActivity(intent)
            }
        })
    }
}