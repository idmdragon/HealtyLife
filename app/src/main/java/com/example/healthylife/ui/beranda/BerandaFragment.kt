package com.example.healthylife.ui.beranda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.healthylife.R
import com.example.healthylife.databinding.FragmentBerandaBinding


class BerandaFragment : Fragment() {

    // Views
    private lateinit var infoSlideViewPager: ViewPager2

    // Dummy Info
    private var dummyInfo = listOf(
        InfoSlide(
            "Ketika kamu lelah olahraga dapat membantu untuk memberikan energi (darah dan oksigen akan mengalir ketika olahraga)"
        ),
        InfoSlide(
            "Optimis dapat membantu hidup lebih lama"
        ),
        InfoSlide(
            "Suhu temperatur yang rendah dapat baik untuk kesehatan"
        ),
        InfoSlide(
            "Sarapan penting untuk memberikan energi sepanjang hari"
        ),
        InfoSlide(
            "Minuman panas membantu untuk mendinginkan tubuh"
        )
    )

    // Adapter
    private lateinit var infoSliderAdapter: InfoSliderAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val berandaBinding: FragmentBerandaBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_beranda, container, false
        )



        setupViewBinding(berandaBinding.root)

        return berandaBinding.root
    }

    private fun setupViewBinding(view: View) {
        infoSlideViewPager = view.findViewById(R.id.infoSlideViewPager)
        setupViewPagerAdapter()

    }

    private fun setupViewPagerAdapter() {
        infoSlideViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        infoSliderAdapter = InfoSliderAdapter(requireContext(), dummyInfo)
        infoSlideViewPager.adapter = infoSliderAdapter
        infoSliderAdapter.notifyDataSetChanged()


    }


}