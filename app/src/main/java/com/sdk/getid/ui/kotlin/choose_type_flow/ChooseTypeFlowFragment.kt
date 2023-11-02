package com.sdk.getid.ui.kotlin.choose_type_flow

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.sdk.getid.R
import com.sdk.getid.app.utils.getDrawable
import com.sdk.getid.databinding.FragmentChooseTypeFlowBinding
import com.sdk.getid.model.app.flow.TypeFlow
import com.sdk.getid.presentation.features.config.choose_type_flow.ChooseTypeFlowContract
import com.sdk.getid.ui.global.BaseFragment
import com.sdk.getid.ui.kotlin.recycler.HorizontalMarginItemDecoration
import org.koin.androidx.scope.currentScope
import kotlin.math.abs


/**
 * Created by Pavlo Kuchirka on 01-Nov-19.
 */
class ChooseTypeFlowFragment : BaseFragment<ChooseTypeFlowContract.Presenter>(),
    ChooseTypeFlowContract.View {

    private var _binding: FragmentChooseTypeFlowBinding? = null
    private val binding get() = _binding!!

    override val presenter: ChooseTypeFlowContract.Presenter by currentScope.inject()

    private lateinit var viewPagerAdapter: RecyclerTypeFlowAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setViews()
        presenter.apply {
            view = this@ChooseTypeFlowFragment
            onStart()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseTypeFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setAgreeButtonTitle(title: String) {
        binding.tvAccept.text = title
    }

    override fun showTypeOfFlows(flows: ArrayList<TypeFlow>) {
        viewPagerAdapter.itemList = flows
    }

    private fun setViews() {
        binding.ivAccept.setOnClickListener { presenter.onClickAgree() }
        binding.tvAccept.setOnClickListener { presenter.onClickAgree() }

        viewPagerAdapter = RecyclerTypeFlowAdapter()
        binding.viewPager.apply {
            setPageTransformer(getPageTransformer())
            addItemDecoration(HorizontalMarginItemDecoration(requireContext(), R.dimen.seventy_dp))

            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3

            adapter = viewPagerAdapter

            val drawableStart = getDrawable(R.drawable.ic_arrow_start_white)
            val drawableEnd = getDrawable(R.drawable.ic_arrow_end_white)

            binding.ivPagerClickStart.apply {
                setImageDrawable(drawableStart)
                setOnClickListener { currentItem -= 1 }
            }
            binding.ivPagerClickEnd.apply {
                setImageDrawable(drawableEnd)
                setOnClickListener { currentItem += 1 }
            }

            val viewPager2PageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                    val white = Color.parseColor("#ffffff")
                    val whiteOpacity = Color.parseColor("#1AFFFFFF")

                    val alpha = (1 - abs(positionOffset))
                    val interpolateValue = interpolateColor(alpha, white, whiteOpacity)

                    var colorFirstItem: Int = interpolateValue
                    var colorSecondItem: Int = interpolateValue

                    when (position) {
                        0 -> {
                            colorSecondItem = white
                        }

                        viewPagerAdapter.itemList.size - 1 -> {
                            colorFirstItem = white
                        }

                        else -> {
                            colorFirstItem = white
                            colorSecondItem = white
                        }
                    }

                    drawableStart!!.colorFilter = getColorFilter(colorFirstItem)
                    drawableEnd!!.colorFilter = getColorFilter(colorSecondItem)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    presenter.changePagerTab(position)
                }
            }
            registerOnPageChangeCallback(viewPager2PageChangeCallback)
        }
    }

    private fun interpolateColor(fraction: Float, startValue: Int, endValue: Int): Int {
        val startA = startValue shr 24 and 0xff
        val startR = startValue shr 16 and 0xff
        val startG = startValue shr 8 and 0xff
        val startB = startValue and 0xff
        val endA = endValue shr 24 and 0xff
        val endR = endValue shr 16 and 0xff
        val endG = endValue shr 8 and 0xff
        val endB = endValue and 0xff
        return startA + (fraction * (endA - startA)).toInt() shl 24 or
                (startR + (fraction * (endR - startR)).toInt() shl 16) or
                (startG + (fraction * (endG - startG)).toInt() shl 8) or
                startB + (fraction * (endB - startB)).toInt()
    }

    private fun getPageTransformer() =
        ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -getPageTranslation() * position
            //            page.scaleY = 1 - (0.25f * abs(position))
            page.alpha = 0.5f + (1 - abs(position))
        }

    private fun getPageTranslation(): Float {
        val nextItemVisiblePx = resources.getDimension(R.dimen.fifty_dp)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.seventy_dp)
        return nextItemVisiblePx + currentItemHorizontalMarginPx
    }

    private fun getColorFilter(colorItem: Int) =
        PorterDuffColorFilter(colorItem, PorterDuff.Mode.MULTIPLY)
}
