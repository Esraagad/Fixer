package android.esraagad.com.fixer.ui.activity

import android.content.Context
import android.esraagad.com.fixer.R
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_converter.*


class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val currencyCode: String = intent.getStringExtra("code").toString()
        val currencyRate: Double = intent.getDoubleExtra("rate", 0.0)

        setInitialDate(currencyCode, currencyRate)
        onDoneClickListener(currencyRate)
    }

    private fun setInitialDate(currencyCode: String, currencyRate: Double) {
        selected_currency_text_input.suffixText = currencyCode
        selected_currency_edit_text.setText(currencyRate.toString())
    }

    private fun onDoneClickListener(currencyRate: Double) {
        base_currency_edit_Text.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER ||
                actionId == EditorInfo.IME_ACTION_DONE
            ) {
                if (base_currency_edit_Text.text.toString().isNotEmpty())

                    selected_currency_edit_text.setText(
                        (base_currency_edit_Text.text.toString()
                            .toDouble() * currencyRate).toString()
                    )
            }
            false
        })
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action === MotionEvent.ACTION_DOWN) {
            val v = currentFocus

            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)

                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}