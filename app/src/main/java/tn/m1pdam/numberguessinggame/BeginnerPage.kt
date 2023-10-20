package tn.m1pdam.numberguessinggame

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BeginnerPage : AppCompatActivity() {

    lateinit var numberInput : EditText;
    lateinit var enterNumber : Button;
    lateinit var history : TextView;
    lateinit var LeaveBtn : Button;

    val numberToGuess = (0..999).random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beginner_page)

        numberInput = findViewById(R.id.numberInput);
        enterNumber = findViewById(R.id.enterNumberBtn);
        history = findViewById(R.id.history);
        LeaveBtn = findViewById(R.id.leave)

        enterNumber.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?){
                val suggNumber : Int = Integer.parseInt(numberInput.text.toString())

                if(suggNumber <= 999){
                    if (suggNumber < numberToGuess) {
                        showToast(this@BeginnerPage, "$suggNumber is smaller than the hidden number!")
                        history.text= history.text.toString() + "\n Hidden number > $suggNumber "
                    }else if (suggNumber > numberToGuess){
                        showToast(this@BeginnerPage, "$suggNumber is bigger than the hidden number!")
                        history.text= history.text.toString() + "\n Hidden number < $suggNumber "
                    }else{
                        Congrats();
                        //recreate();
                    }
                }else{
                    showToast(this@BeginnerPage, "The hidden number is between 0 and 999!");
                }
                numberInput.text.clear();
            }
        })

        LeaveBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@BeginnerPage, MainActivity::class.java);
                startActivity(intent);
            }
        })
    }

    fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
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
                recreate();
            }
        })

        dialBtnLeave.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                dialog.dismiss();
                val intent = Intent(this@BeginnerPage, MainActivity::class.java);
                startActivity(intent)
            }
        })

        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

}