package com.nknalanda.falconbrick

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nknalanda.falconbrick.databinding.ActivitySearchBinding
import java.util.*
import kotlin.system.exitProcess


class SearchActivity : AppCompatActivity() {
    private var binding: ActivitySearchBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        initViews()
        highlightText(resources.getString
            (R.string.type_to_start_searching_for), "Units, Activity, Status")
    }

    private fun initViews() {
        supportActionBar?.title = "Search"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun highlightText(fullText: String, highlightText: String) {
        // highlight search text
        // highlight search text
        if (highlightText != null && !highlightText.isEmpty()) {
            val startPos: Int =
                fullText.toLowerCase(Locale.US).indexOf(highlightText.toLowerCase(Locale.US))
            val endPos: Int = startPos + highlightText.length
            if (startPos != -1) {
                val spannable: Spannable = SpannableString(fullText)
                val blueColor = ColorStateList(arrayOf(intArrayOf()), intArrayOf(resources.getColor(R.color.highlight_color, null)))
                val highlightSpan = TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null)
                spannable.setSpan(
                    highlightSpan,
                    startPos,
                    endPos,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding?.tvSearchTitle?.text = spannable
            } else {
                binding?.tvSearchTitle?.text = fullText
            }
        } else {
            binding?.tvSearchTitle?.text = fullText
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {

        AlertDialog.Builder(this, 0)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _: DialogInterface?, _: Int ->
                finish()
                exitProcess(0)
            }.setNegativeButton("No", null).show()
    }
}