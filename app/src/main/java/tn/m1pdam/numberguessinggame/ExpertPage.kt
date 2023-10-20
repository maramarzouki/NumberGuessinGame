package tn.m1pdam.numberguessinggame

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ExpertPage : AppCompatActivity() {
    lateinit var goHomepage : Button;
    lateinit var timer : TextView;
    lateinit var numberInput : EditText;
    lateinit var enterNumber : Button;

    val numberToGuess = (0..999).random();
    var countdownTimer: CountDownTimer? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expert_page);

        timer = findViewById(R.id.timer);
        goHomepage = findViewById(R.id.leave);
        numberInput = findViewById(R.id.numberInput);
        enterNumber = findViewById(R.id.enterNumberBtn);

        enterNumber.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?){
                val suggNumber : Int = Integer.parseInt(numberInput.text.toString())

                if(suggNumber <= 999){
                    if (suggNumber < numberToGuess) {
                        showToast(this@ExpertPage, "$suggNumber is smaller than the hidden number!")
                    }else if (suggNumber > numberToGuess){
                        showToast(this@ExpertPage, "$suggNumber is bigger than the hidden number!")
                    }else{
                        Congrats();
                        //recreate();
                    }
                }else{
                    showToast(this@ExpertPage, "The hidden number is between 0 and 999!");
                }
                numberInput.text.clear();
            }
        })


        val durationInMillis: Long = 30000;

        countdownTimer = object : CountDownTimer(durationInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000).toInt()
                timer.text = String.format("00:%02d", seconds)
            }

            override fun onFinish() {
                TimeUP();
            }

        }

        startTimer();

        goHomepage.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@ExpertPage, MainActivity::class.java);
                startActivity(intent);
            }
        })

    }

    fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

    fun startTimer() {
        countdownTimer?.start()
    }

    private fun stopTimer() {
        countdownTimer?.cancel()
    }

    fun TimeUP() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)

        val dialMsg: TextView = dialog.findViewById(R.id.dialogText)
        val dialBtn: Button = dialog.findViewById(R.id.dialogBTN)
        val dialBtnLeave : Button = dialog.findViewById(R.id.dialogBTNLeave)

        dialMsg.text = "Time is up! ☹\nThe hidden number was: $numberToGuess!"
        dialBtn.text = "Try again"

        dialBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.dismiss()
                val intent = Intent(this@ExpertPage, ExpertStartPage::class.java)
                startActivity(intent)
            }
        })

        dialBtnLeave.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                dialog.dismiss();
                val intent = Intent(this@ExpertPage, MainActivity::class.java);
                startActivity(intent)
            }
        })

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    fun Congrats() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)

        val dialMsg: TextView = dialog.findViewById(R.id.dialogText)
        val dialBtn: Button = dialog.findViewById(R.id.dialogBTN)
        val dialBtnLeave : Button = dialog.findViewById(R.id.dialogBTNLeave)

        dialMsg.text = "Congratulations!🎉\nYou guessed it right!\nThe hidden number was: $numberToGuess!"
        dialBtn.text = "Play again"

        dialBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.dismiss()
                val intent = Intent(this@ExpertPage, ExpertStartPage::class.java)
                startActivity(intent)
            }
        })

        dialBtnLeave.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                dialog.dismiss();
                val intent = Intent(this@ExpertPage, MainActivity::class.java);
                startActivity(intent)
            }
        })

        stopTimer()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
}