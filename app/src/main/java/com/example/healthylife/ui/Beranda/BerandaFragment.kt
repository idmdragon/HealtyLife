package com.example.healthylife.ui.Beranda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthylife.R
import kotlinx.android.synthetic.main.fragment_beranda.*


class BerandaFragment : Fragment() {


    private val infoSliderAdapter = InfoSliderAdapter(
        listOf(
            InfoSlide("Ketika kamu lelah olahraga dapat membantu untuk memberikan energi (darah dan oksigen akan mengalir ketika olahraga)"
            ),
            InfoSlide("Optimis dapat membantu hidup lebih lama"
            ),
            InfoSlide("Suhu temperatur yang rendah dapat baik untuk kesehatan"
            ),
            InfoSlide("Sarapan penting untuk memberikan energi sepanjang hari"
            ),
            InfoSlide("Minuman panas membantu untuk mendinginkan tubuh"
            )

        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //dah dari sininya bingung mas harusnya kan kalau di activity biasa masuknya ke oncreate
//        infoSlideViewPager.adapter = infoSliderAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }


}