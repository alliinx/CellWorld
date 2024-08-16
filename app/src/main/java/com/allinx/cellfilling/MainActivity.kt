package com.allinx.cellfilling

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.allinx.cellfilling.adapter.CellAdapter
import com.allinx.cellfilling.databinding.ActivityMainBinding
import com.allinx.cellfilling.model.Cell
import com.allinx.cellfilling.model.World
import com.allinx.cellfilling.viewmodel.MainViewModel
import java.security.SecureRandom
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cellAdapter: CellAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.seance)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cellAdapter = CellAdapter(emptyList())

        initRecycler()
        initListeners()
    }

    override fun onPause() {
        super.onPause()
        saveScrollPosition()
    }

    override fun onResume() {
        super.onResume()
        setAdapter()
        restoreScrollPosition()
    }

    private fun initRecycler() {
        binding.cellRv.apply{
            adapter = cellAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initListeners(){
        binding.btnGenerate.setOnClickListener {
            generateCell()
            setAdapter()
        }
    }

    private fun generateCell(){
        mainViewModel.generateCell()
    }

    private fun setAdapter(){
        cellAdapter.updateCocktails(mainViewModel.getListCell())
        scrollToLast()
    }

    private fun scrollToLast(){
        // Получаем LayoutManager
        val layoutManager = binding.cellRv.layoutManager as LinearLayoutManager
        // Определение индекса последнего элемента (учитывая, что нумерация идет с нуля)
        val lastPosition = cellAdapter.itemCount - 1
        // Прокрутка до конца
        layoutManager.scrollToPositionWithOffset(lastPosition, 0);
    }

    private fun saveScrollPosition() {
        val layoutManager = binding.cellRv.layoutManager as LinearLayoutManager
        mainViewModel.setScrollPosition(layoutManager.findFirstVisibleItemPosition())
        val view = layoutManager.findViewByPosition(mainViewModel.getScrollPosition())
        mainViewModel.setScrollOffset(view?.top ?: 0)
    }

    private fun restoreScrollPosition() {
        val layoutManager = binding.cellRv.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(mainViewModel.getScrollPosition(), mainViewModel.getScrollOffset())
    }
}