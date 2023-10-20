package tn.m1pdam.numberguessinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ExpertStartPage : AppCompatActivity() {
    lateinit var startBtn : Button;
    lateinit var goBackBtn : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expert_start_page);

        startBtn = findViewById(R.id.start);
        goBackBtn = findViewById(R.id.goback);

        startBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@ExpertStartPage,ExpertPage::class.java);
                startActivity(intent);
            }
        })

        goBackBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@ExpertStartPage,MainActivity::class.java);
                startActivity(intent);
            }
        })
    }
}