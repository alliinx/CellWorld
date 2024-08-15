package com.allinx.cellfilling

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allinx.cellfilling.adapter.CellAdapter
import com.allinx.cellfilling.databinding.ActivityMainBinding
import com.allinx.cellfilling.model.Cell
import com.allinx.cellfilling.model.World
import java.security.SecureRandom
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cellAdapter: CellAdapter

    private lateinit var world: World

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

        cellAdapter = CellAdapter()
        world = World()

        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.cellRv.apply{
            adapter = cellAdapter
        }
    }

    private fun initListeners(){
        binding.btnGenerate.setOnClickListener {
            generateCell()
            setAdapter()
        }
    }

    private fun generateCell(){
        world.addCell()
    }

    private fun setAdapter(){
        cellAdapter.submitList(world.getListCell())
    }
}