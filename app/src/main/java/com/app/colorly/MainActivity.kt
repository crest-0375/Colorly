package com.app.colorly

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.app.colorly.databinding.ActivityMainBinding
import com.app.colorly.databinding.ItemDialogBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: ItemDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_color) {
            showDialog()
        }
        else if(item.itemId == R.id.clr_color){
           recreate()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {
        dialogBinding = ItemDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setTitle("Paste HEX color code")
            .create()


        dialogBinding.addBtn.setOnClickListener {
            val colorCode = dialogBinding.addColorText.text.toString().trim()
            if (colorCode.length == 6) {
                val linearLayout = LinearLayout(this)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.weight = 1f
                linearLayout.layoutParams = layoutParams
                layoutParams.setMargins(8)
                linearLayout.setBackgroundColor(Color.parseColor("#$colorCode"))
                binding.llColorBackground.addView(linearLayout)
                builder.dismiss()
            }
        }
        builder.show()

    }
}